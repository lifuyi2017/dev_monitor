package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.DevElementMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.model.*;
import com.winterchen.service.user.CollectionService;
import com.winterchen.service.user.DevService;
import com.winterchen.util.MqttUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void editDevOrDevElementById(DevElement devElement) throws Exception{
        devElementMapper.editDevOrDevElementById(devElement);
    }

    @Override
    public void deleteElementAndSubElements(String dev_element_id) throws Exception {
        devElementMapper.deleteByElementId(dev_element_id);
        devElementMapper.deleteByParentId(dev_element_id);
    }

    @Override
    public void deleteByEnterpriseId(String id) throws Exception {
        List<CollectionManager> collectionManagerList=collectionManagerMapper.getByEnterpriseId(id);
        for(CollectionManager collectionManager:collectionManagerList){
            collectionService.putToMqtt(collectionManager);
        }
        collectionManagerMapper.deleteByEnterpriseId(id);
        devElementMapper.deleteByEnterpriseId(id);
    }

}
