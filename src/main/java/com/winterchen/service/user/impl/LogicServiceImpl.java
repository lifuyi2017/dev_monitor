package com.winterchen.service.user.impl;

import com.winterchen.dao.*;
import com.winterchen.model.*;
import com.winterchen.service.user.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("logicService")
public class LogicServiceImpl implements LogicService {

    @Autowired
    private LogicMapper logicMapper;
    @Autowired
    private LogicRelationMapper logicRelationMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private MeasureMapper measureMapper;
    @Autowired
    private ChannelMapper channelMapper;
    @Resource
    private CollectionManagerMapper collectionManagerMapper;

    @Override
    public void updateById(LogicNode logicNode) throws Exception {
        logicMapper.updateById(logicNode);
        logicRelationMapper.deleteByLogicId(logicNode.getLogic_id());
        for(LogicRelation logicRelation:logicNode.getLogicRelationList()){
            logicRelation.setLogic_id(logicNode.getLogic_id());
            for(String id:logicRelation.getChannel_id_list()){
                logicRelationMapper.insert(logicRelation.getLogic_id(),logicRelation.getMeasure_id(),id);
            }
        }
    }

    @Override
    public void insertEntity(LogicNode logicNode) throws Exception {
        logicMapper.insertEntity(logicNode);
        for(LogicRelation logicRelation:logicNode.getLogicRelationList()){
            logicRelation.setLogic_id(logicNode.getLogic_id());
            for(String id:logicRelation.getChannel_id_list()){
                logicRelationMapper.insert(logicRelation.getLogic_id(),logicRelation.getMeasure_id(),id);
            }
        }
    }

    @Override
    public List<LogicNode> queryByEntity(LogicNode logicNode) throws Exception {
        List<LogicNode> logicNodeList=logicMapper.queryForEntity(logicNode);
        for(LogicNode logic:logicNodeList){
            Enterprise enterprise = new Enterprise();
            enterprise.setEnterprise_id(logic.getEnterprise_id());
            List<Enterprise> enterByEntity = enterpriseMapper.getEnterByEntity(enterprise);
            if(enterByEntity!=null && enterByEntity.size()>0){
                logic.setEnterprise_name(enterByEntity.get(0).getEnterprise_name());
            }else {
                logicMapper.deleteByEnterpriseId(logic.getEnterprise_id());
            }
            LogicRelation logicRelation = new LogicRelation();
            logicRelation.setLogic_id(logic.getLogic_id());
            List<LogicRelation> logicRelationList=logicRelationMapper.queryForEntity(logicRelation);
            HashSet<LogicRelation> relations = new HashSet<>();
            for(LogicRelation relation:logicRelationList){
                List<String> ids=logicRelationMapper.getChannelIdByEntity(relation);
                List<String> channelNameList=new ArrayList<>();
                for(String id:ids){
                    Channel channel = new Channel();
                    channel.setChannel_id(id);
                    List<Channel> channelList = channelMapper.queryByEntity(channel);
                    if(channelList!=null && channelList.size()>0){
                        channelNameList.add(channelList.get(0).getChannel_name());
                    }else {
                        logicRelationMapper.deleteByChannelId(id);
                    }
                }
                relation.setChannel_name_list(channelNameList);
                Measure measure = new Measure();
                measure.setMeasure_id(relation.getMeasure_id());
                List<Measure> measureList = measureMapper.queryByEntity(measure);
                if(measureList!=null && measureList.size()>0){
                    relation.setMeasure_name(measureList.get(0).getMeasure_name());
                }else {
                    logicRelationMapper.deleteByMeasureId(relation.getMeasure_id());
                }
                relation.setChannel_id_list(ids);
                if(!relations.contains(relation)){
                    relations.add(relation);
                }
            }
            logic.setLogicRelationList(new ArrayList<>(relations));
        }
        return logicNodeList;
    }

    @Override
    public void deleteById(String logic_id) {
        logicMapper.deleteById(logic_id);
        logicRelationMapper.deleteById(logic_id);
        collectionManagerMapper.deleteByLogicId(logic_id);
    }

    @Override
    public void deleteByEnterpriseId(String id) {
        logicRelationMapper.deleteByEnterpriseId(id);
        logicMapper.deleteByEnterpriseId(id);
    }
}
