package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.LogicMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.model.*;
import com.winterchen.service.user.CollectionService;
import com.winterchen.util.DateUtil;
import com.winterchen.util.EntityUtil;
import com.winterchen.util.MqttUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public void putToMqtt(CollectionManager collectionManager1, String flag) throws Exception {
        CollectionMqtt collectionMqtt=getMessage(collectionManager1,flag);
        MqttUtil.putToMqtt(collectionMqtt);
    }

    @Override
    public List<CollectionManager> getByChId(String collectQ) {
        return collectionManagerMapper.getByChId(collectQ);
    }

    @Override
    public PageInfo<CollectionManagerHttp> queryByEntityBPage(CollectionManagerRequest collectionManagerRequest) {
        List<CollectionManagerHttp> collectionManagerHttpList = new ArrayList<>();
        PageInfo<CollectionManagerHttp> result=new PageInfo();
        if(collectionManagerRequest.getPageNum()!=null && collectionManagerRequest.getPageSize()!=null){
            PageHelper.startPage(collectionManagerRequest.getPageNum(), collectionManagerRequest.getPageSize());
        }
        List<CollectionManager> collectionManagers=collectionManagerMapper.queryByEntity(collectionManagerRequest.getCollectionManager());
        result=new PageInfo(collectionManagers);
        if(collectionManagers.size()>0){
            for(CollectionManager collect:collectionManagers){
                CollectionManagerHttp collectionHttp=getCollectManagerByCollect(collect);
                collectionManagerHttpList.add(collectionHttp);
            }
        }
        result.setList(collectionManagerHttpList);
        return result;
    }

    @Override
    public List<CollectionManager> getByChIdAndId(CollectionManager col) {
        return collectionManagerMapper.getByChIdAndId(col);
    }

    private CollectionManagerHttp getCollectManagerByCollect(CollectionManager collect) {
        CollectionManagerHttp collectionHttp = new CollectionManagerHttp();
        BeanUtils.copyProperties(collect,collectionHttp,"channel_id");
        LogicNode logicNode = new LogicNode();
        logicNode.setLogic_id(collect.getLogic_id());
        List<LogicNode> logicNodes = logicMapper.queryForEntity(logicNode);
        if(logicNodes !=null && logicNodes.size()>0){
            collectionHttp.setLogic_name(logicNodes.get(0).getLogic_name());
        }
        Measure measure = new Measure();
        measure.setMeasure_id(collect.getMeasure_id());
        collectionHttp.setMeasure_name(measureMapper.queryByEntity(measure).get(0).getMeasure_name());
        if(collect.getChannel_id()!=null){
            List<String> ids = EntityUtil.stringToList(collect.getChannel_id());
            List<String> codes = new ArrayList<>();
            Channel channel = new Channel();
            collectionHttp.setChannel_id(ids);
            for (String chId: ids){
                channel.setChannel_id(chId);
                List<Channel> channels = channelMapper.queryByEntity(channel);
                if(channels!=null && channels.size()>0){
                    codes.add(channels.get(0).getChannel_code());
                }
            }
            collectionHttp.setChannel_code(codes);
        }
        return collectionHttp;
    }


    private CollectionMqtt getMessage(CollectionManager collectionManager, String flag) {
        Measure queryMeasure = new Measure();
        queryMeasure.setMeasure_id(collectionManager.getMeasure_id());
        Measure measure = measureMapper.queryByEntity(queryMeasure).get(0);
        Channel queryChannel = new Channel();
        queryChannel.setChannel_id(collectionManager.getChannel_id());
        Channel channel = channelMapper.queryByEntity(queryChannel).get(0);
        return new CollectionMqtt(measure.getMeasure_code(), EntityUtil.stringToList(channel.getChannel_code()),
                collectionManager.getCollection_frequency(),collectionManager.getCollection_cycle(),
                collectionManager.getCollection_accuracy(),collectionManager.getCollection_interval(),flag,
                DateUtil.getDateTime());
    }

}
