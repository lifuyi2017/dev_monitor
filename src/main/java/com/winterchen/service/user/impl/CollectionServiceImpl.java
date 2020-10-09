package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.LogicMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.model.Channel;
import com.winterchen.model.CollectionManager;
import com.winterchen.model.LogicNode;
import com.winterchen.model.Measure;
import com.winterchen.service.user.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionManagerMapper collectionManagerMapper;
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private LogicMapper logicMapper;
    @Resource
    private MeasureMapper measureMapper;

    @Override
    public List<CollectionManager> queryByEntity(CollectionManager collectionManager) throws Exception {
        List<CollectionManager> collectionManagers=collectionManagerMapper.queryByEntity(collectionManager);
        for(CollectionManager collect:collectionManagers){
            Channel channel = new Channel();
            channel.setChannel_id(collect.getChannel_id());
            collect.setChannel_name(channelMapper.queryByEntity(channel).get(0).getChannel_name());
            LogicNode logicNode = new LogicNode();
            logicNode.setLogic_id(collect.getLogic_id());
            collect.setLogic_name(logicMapper.queryForEntity(logicNode).get(0).getLogic_name());
            Measure measure = new Measure();
            measure.setMeasure_id(collect.getMeasure_id());
            collect.setMeasure_name(measureMapper.queryByEntity(measure).get(0).getMeasure_name());
        }
        return collectionManagers;
    }

    @Override
    public void updateByCollectionId(CollectionManager collectionManager1) throws Exception {
        collectionManagerMapper.updateByCollectionId(collectionManager1);
    }

    @Override
    public void insert(CollectionManager collectionManager) throws Exception {
        collectionManagerMapper.insert(collectionManager);
    }

    @Override
    public void deleteByElementId(String dev_element_id) {
        collectionManagerMapper.deleteByElementId(dev_element_id);
    }

    @Override
    public void deleteById(String collection_id) {
        collectionManagerMapper.deleteById(collection_id);
    }

}
