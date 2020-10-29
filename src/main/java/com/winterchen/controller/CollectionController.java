package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.annotation.UserLoginToken;
import com.winterchen.model.*;
import com.winterchen.service.user.CollectionService;
import com.winterchen.util.EntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/collectionController")
@CrossOrigin
public class CollectionController {

    @Autowired
    private CollectionService collectionService;


    /**
     * 开始停止采集
     */
    @PostMapping("/startStopCollect")
    @Transactional
    @UserLoginToken
    public ResultMessage<Boolean> startCollect(@RequestBody StartStopCollection startStopCollection){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            if("1".equals(startStopCollection.getStatus())){
                //开始采集
                List<CollectionManager> collectionManagerList;
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    collectionManagerList = collectionService.queryByEntity(collectionManager);
                    if("1".equals(collectionManagerList.get(0).getStatus())){
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("401");
                        booleanResultMessage.setMesg("错误操作，所选的采集列表中已经有采集配置在进行采集任务了");
                        return booleanResultMessage;
                    }
                }
                List<CollectionManager> optList=new ArrayList<>();
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    collectionManagerList = collectionService.queryByEntity(collectionManager);
                    CollectionManager collectionManager1 = collectionManagerList.get(0);
                    if(collectionManager1.getMeasure_id()!=null && collectionManager1.getChannel_id()!=null ){
                        optList.add(collectionManager1);
                    }else {
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("401");
                        booleanResultMessage.setMesg("错误操作，在开始采集前需要补充完物理节点和通道信息");
                        return booleanResultMessage;
                    }
                }
                if(optList.size()>0){
                    for(CollectionManager collect:optList){
                        if("0".equals(collect.getStatus())){
                            collectionService.putToMqtt(collect,"1");
                            collect.setStatus("1");
                            collectionService.updateByCollectionId(collect);
                        }
                    }
                }
            }else {
                List<CollectionManager> collectionManagerList;
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    collectionManagerList = collectionService.queryByEntity(collectionManager);
                    if("0".equals(collectionManagerList.get(0).getStatus())){
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("401");
                        booleanResultMessage.setMesg("错误操作，所选的采集列表中已经有采集配置停止了采集任务了");
                        return booleanResultMessage;
                    }
                }
                List<CollectionManager> optList=new ArrayList<>();
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    collectionManagerList = collectionService.queryByEntity(collectionManager);
                    CollectionManager collectionManager1 = collectionManagerList.get(0);
                    if(collectionManager1.getMeasure_id()!=null && collectionManager1.getChannel_id()!=null ){
                        optList.add(collectionManager1);
                    }else {
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("401");
                        booleanResultMessage.setMesg("错误操作，在结束采集前需要补充完物理节点和通道信息");
                        return booleanResultMessage;
                    }
                }
                if(optList.size()>0){
                    for(CollectionManager collect:optList){
                        if("1".equals(collect.getStatus())){
                            collectionService.putToMqtt(collect,"0");
                            collect.setStatus("0");
                            collectionService.updateByCollectionId(collect);
                        }
                    }
                }
            }
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("操作成功");
        }catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
        }
        return booleanResultMessage;
    }

    /**
     * 新增或者修改
     */
    @PostMapping("/addOrUpdateCollection")
    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdateCollection(@RequestBody CollectionManagerHttp collectionManager){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            //判断字段是否weinull
            String s = EntityUtil.checkObjectField(collectionManager);
            if(!"true".equals(s)){
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg(s);
                booleanResultMessage.setValue(false);
                return booleanResultMessage;
            }
            if(collectionManager.getChannel_id()==null && collectionManager.getChannel_id().size()==0){
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg("请设置通道");
                booleanResultMessage.setValue(false);
                return booleanResultMessage;
            }
            //进一步判断通道是否被占用
            for(String chId:collectionManager.getChannel_id()){
                CollectionManager col = new CollectionManager();
                col.setChannel_id(chId);
                if(collectionManager.getCollection_id()!=null){
                    col.setCollection_id(collectionManager.getCollection_id());
                }
                List<CollectionManager> collectionManagers =collectionService.getByChIdAndId(col);
                if(collectionManagers!=null && collectionManagers.size()>0){
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setMesg("错误，所选通道中有已经被占用的");
                    booleanResultMessage.setValue(false);
                    return booleanResultMessage;
                }
            }
            if(collectionManager.getCollection_id()!=null){
                CollectionManager collect = new CollectionManager();
                collect.setCollection_id(collectionManager.getCollection_id());
                List<CollectionManager> collectionManagerList = collectionService.queryByEntity(collect);
                if(collectionManagerList==null){
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setMesg("不存在的采集配置，请输入正确的采集id");
                    return booleanResultMessage;
                }
                if("1".equals(collectionManagerList.get(0).getStatus())){
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setMesg("请先停止采集任务");
                }else {
                    collectionManager.setStatus("0");
                    CollectionManager collection = new CollectionManager();
                    BeanUtils.copyProperties(collectionManager,collection,"channel_id");
                    collection.setChannel_id(EntityUtil.listToString(collectionManager.getChannel_id()));
                    collectionService.updateByCollectionId(collection);
                    booleanResultMessage.setValue(true);
                    booleanResultMessage.setStatuscode("200");
                    booleanResultMessage.setMesg("操作成功");
                }
            }else {
                collectionManager.setCollection_id(UUID.randomUUID().toString().replaceAll("-", ""));
                collectionManager.setStatus("0");
                CollectionManager collection = new CollectionManager();
                BeanUtils.copyProperties(collectionManager,collection,"channel_id");
                collection.setChannel_id(EntityUtil.listToString(collectionManager.getChannel_id()));
                collectionService.insert(collection);
                booleanResultMessage.setValue(true);
                booleanResultMessage.setStatuscode("200");
                booleanResultMessage.setMesg("操作成功");
            }
        }catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
        }
        return booleanResultMessage;
    }

    /**
     * 查询
     */
    @PostMapping("/queryCollection")
    @UserLoginToken
    public  ResultMessage<PageInfo<CollectionManagerHttp>> queryCollection(@RequestBody CollectionManagerRequest collectionManagerRequest){
        ResultMessage<PageInfo<CollectionManagerHttp>> logicPage = new ResultMessage<>();
        try {
            PageInfo<CollectionManagerHttp> pageInfo=collectionService.queryByEntityBPage(collectionManagerRequest);
            logicPage.setValue(pageInfo);
            logicPage.setStatuscode("200");
            logicPage.setMesg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            logicPage.setValue(null);
            logicPage.setStatuscode("501");
            logicPage.setMesg("服务端错误：" + e.toString());
        }
        return logicPage;
    }


    /**
     *删除
     */
    @PostMapping("/deleteById")
    @UserLoginToken
    public ResultMessage<Boolean> deleteById(@RequestBody CollectionManager collectionManager){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            List<CollectionManager> collectionManagerList = collectionService.queryByEntity(collectionManager);
            if("1".equals(collectionManagerList.get(0).getStatus())){
                booleanResultMessage.setValue(false);
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg("请先停止采集任务");
            }else {
                collectionService.deleteById(collectionManager.getCollection_id());
                booleanResultMessage.setValue(true);
                booleanResultMessage.setStatuscode("200");
                booleanResultMessage.setMesg("操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
        }
        return booleanResultMessage;
    }


}
