package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.annotation.UserLoginToken;
import com.winterchen.model.CollectionManager;
import com.winterchen.model.CollectionManagerRequest;
import com.winterchen.model.ResultMessage;
import com.winterchen.model.StartStopCollection;
import com.winterchen.service.user.CollectionService;
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
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    List<CollectionManager> collectionManagerList = collectionService.queryByEntity(collectionManager);
                    CollectionManager collectionManager1 = collectionManagerList.get(0);
                    putToMqtt(collectionManager1);
                    collectionManager1.setStatus("1");
                    collectionService.updateByCollectionId(collectionManager1);
                }
            }else {
                for(String id:startStopCollection.getIds()){
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setCollection_id(id);
                    List<CollectionManager> collectionManagerList = collectionService.queryByEntity(collectionManager);
                    CollectionManager collectionManager1 = collectionManagerList.get(0);
                    removeMqtt(collectionManager1);
                    collectionManager1.setStatus("0");
                    collectionService.updateByCollectionId(collectionManager1);
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

    private void putToMqtt(CollectionManager collectionManager1) {
    }

    private void removeMqtt(CollectionManager collectionManager1) {

    }

    /**
     * 新增或者修改
     */
    @PostMapping("/addOrUpdateCollection")
    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdateCollection(@RequestBody CollectionManager collectionManager){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
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
