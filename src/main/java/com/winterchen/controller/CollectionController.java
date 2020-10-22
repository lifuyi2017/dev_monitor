package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.annotation.UserLoginToken;
import com.winterchen.model.*;
import com.winterchen.service.user.CollectionService;
import com.winterchen.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    collectionManagerList = collectionService.queryByEntity(collectionManager);
                    CollectionManager collectionManager1 = collectionManagerList.get(0);
                    if("0".equals(collectionManager1.getStatus())){
                        collectionService.putToMqtt(collectionManager1,"1");
                        collectionManager1.setStatus("1");
                        collectionService.updateByCollectionId(collectionManager1);
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
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    collectionManagerList = collectionService.queryByEntity(collectionManager);
                    CollectionManager collectionManager1 = collectionManagerList.get(0);
                    if("1".equals(collectionManager1.getStatus())){
                        collectionService.putToMqtt(collectionManager1,"0");
                        collectionManager1.setStatus("0");
                        collectionService.updateByCollectionId(collectionManager1);
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
    public ResultMessage<Boolean> addOrUpdateCollection(@RequestBody CollectionManager collectionManager){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String s = EntityUtil.checkObjectField(collectionManager);
            if(!"true".equals(s)){
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg(s);
                booleanResultMessage.setValue(false);
                return booleanResultMessage;
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
                    collectionService.updateByCollectionId(collectionManager);
                    booleanResultMessage.setValue(true);
                    booleanResultMessage.setStatuscode("200");
                    booleanResultMessage.setMesg("操作成功");
                }
            }else {
                collectionManager.setCollection_id(UUID.randomUUID().toString().replaceAll("-", ""));
                collectionManager.setStatus("0");
                collectionService.insert(collectionManager);
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
    public  ResultMessage<PageInfo<CollectionManager>> queryCollection(@RequestBody CollectionManagerRequest collectionManagerRequest){
        ResultMessage<PageInfo<CollectionManager>> logicPage = new ResultMessage<>();
        try {
            PageInfo result;
            if(collectionManagerRequest.getPageNum()!=null && collectionManagerRequest.getPageSize()!=null){
                PageHelper.startPage(collectionManagerRequest.getPageNum(), collectionManagerRequest.getPageSize());
                List<CollectionManager> collectionManagerList = collectionService.queryByEntity(collectionManagerRequest.getCollectionManager());
                result = new PageInfo(collectionManagerList);
            }else {
                result =new PageInfo();
                List<CollectionManager> collectionManagerList = collectionService.queryByEntity(collectionManagerRequest.getCollectionManager());
                result.setList(collectionManagerList);
            }
            logicPage.setValue(result);
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
