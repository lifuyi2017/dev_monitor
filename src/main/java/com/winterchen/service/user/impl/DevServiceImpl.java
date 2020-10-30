package com.winterchen.service.user.impl;

import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.DevElementMapper;
import com.winterchen.model.*;
import com.winterchen.service.user.CollectionService;
import com.winterchen.service.user.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "devService")
public class DevServiceImpl implements DevService {

    @Autowired
    private DevElementMapper devElementMapper;
    @Autowired
    private CollectionManagerMapper collectionManagerMapper;
    @Autowired
    private CollectionService collectionService;

    @Override
    public List<DevElement> queryByEntity(DevElement queryParent) throws Exception {
        return devElementMapper.queryByEntity(queryParent);
    }

    @Override
    public void insertEntity(DevElement devElement) throws Exception {
        devElementMapper.insertEntity(devElement);
    }

    @Override
    public void editDevOrDevElementById(DevElement devElement) throws Exception {
        devElementMapper.editDevOrDevElementById(devElement);
    }

    @Override
    @Transactional
    public ResultMessage<Boolean> deleteElementAndSubElements(String dev_element_id) throws Exception {
        //校验节点与子节点是否有采集任务
        ResultMessage<Boolean> result = isHasOnCollect(dev_element_id);
        if (result.getValue()) {
            return deleteByParentId(dev_element_id);
        } else {
            return result;
        }
    }

    private ResultMessage<Boolean> isHasOnCollect(String dev_element_id) {
        ResultMessage<Boolean> resultMessage = new ResultMessage();
        DevElement devElement = new DevElement();
        devElement.setDev_parent_element_id(dev_element_id);
        List<DevElement> devElements = devElementMapper.queryByEntity(devElement);
        if (devElements != null && devElements.size() > 0) {
            ResultMessage<Boolean> result = new ResultMessage();
            for (DevElement dev : devElements) {
                result = isHasOnCollect(dev.getDev_element_id());
                if (!result.getValue()) {
                    return result;
                }
            }
            return result;
        } else {
            //校验是否有采集任务
            CollectionManager collectionManager = new CollectionManager();
            collectionManager.setDev_element_id(dev_element_id);
            Integer num = collectionManagerMapper.queryOnCollectCountByEntity(collectionManager);
            if (num > 0) {
                resultMessage.setValue(false);
                resultMessage.setStatuscode("401");
                resultMessage.setMesg("删除错误，删除的节点或子节点中有未停止的采集任务，请先停止采集任务");
                return resultMessage;
            } else {
                resultMessage.setValue(true);
                resultMessage.setStatuscode("200");
                resultMessage.setMesg("无未停止的采集任务");
                return resultMessage;
            }
        }
    }

    private ResultMessage<Boolean> deleteByParentId(String dev_element_id) {
        ResultMessage<Boolean> resultMessage = new ResultMessage();
        DevElement devElement = new DevElement();
        devElement.setDev_parent_element_id(dev_element_id);
        List<DevElement> devElements = devElementMapper.queryByEntity(devElement);
        if (devElements != null && devElements.size() > 0) {
            ResultMessage<Boolean> result = new ResultMessage();
            for (DevElement dev : devElements) {
                result = deleteByParentId(dev.getDev_element_id());
//                devElementMapper.deleteByElementId(dev.getDev_element_id());
            }
            collectionManagerMapper.deleteByElementId(dev_element_id);
            devElementMapper.deleteByElementId(dev_element_id);
            return result;
        } else {
            collectionManagerMapper.deleteByElementId(dev_element_id);
            devElementMapper.deleteByElementId(dev_element_id);
            resultMessage.setValue(true);
            resultMessage.setStatuscode("200");
            resultMessage.setMesg("删除成功");
            return resultMessage;
        }
    }


    @Override
    @Transactional
    public ResultMessage<Boolean> deleteByEnterpriseId(String id) throws Exception {
        List<String> ids=devElementMapper.getTypeIdByEnterpriseId(id);
        ResultMessage<Boolean> result=new ResultMessage<>();
        if (ids.size()>0){
            for(String type_id:ids){
                result = deleteElementAndSubElements(type_id);
                if(!result.getValue()){
                    return result;
                }
            }
        }else {
            result.setValue(true);
            result.setMesg("删除成功");
            result.setStatuscode("200");
        }
        return result;


      /*  List<CollectionManager> collectionManagerList = collectionManagerMapper.getByEnterpriseId(id);
        for (CollectionManager collectionManager : collectionManagerList) {
            collectionService.putToMqtt(collectionManager, "0");
        }
        collectionManagerMapper.deleteByEnterpriseId(id);
        devElementMapper.deleteByEnterpriseId(id);*/
    }

}
