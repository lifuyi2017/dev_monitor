package com.winterchen.service.user.impl;

import com.winterchen.dao.LogicMapper;
import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.model.LogicNode;
import com.winterchen.model.LogicRelation;
import com.winterchen.service.user.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logicService")
public class LogicServiceImpl implements LogicService {

    @Autowired
    private LogicMapper logicMapper;
    @Autowired
    private LogicRelationMapper logicRelationMapper;

    @Override
    public void updateById(LogicNode logicNode) throws Exception {
        logicMapper.updateById(logicNode);
        logicRelationMapper.deleteByLogicId(logicNode.getLogic_id());
        for(LogicRelation logicRelation:logicNode.getLogicRelationList()){
            logicRelation.setLogic_id(logicNode.getLogic_id());
            logicRelationMapper.insert(logicRelation);
        }
    }

    @Override
    public void insertEntity(LogicNode logicNode) throws Exception {
        logicMapper.insertEntity(logicNode);
        for(LogicRelation logicRelation:logicNode.getLogicRelationList()){
            logicRelation.setLogic_id(logicNode.getLogic_id());
            logicRelationMapper.insert(logicRelation);
        }
    }

    @Override
    public List<LogicNode> queryByEntity(LogicNode logicNode) throws Exception {
        List<LogicNode> logicNodeList=logicMapper.queryForEntity(logicNode);
        for(LogicNode logic:logicNodeList){
            LogicRelation logicRelation = new LogicRelation();
            logicRelation.setLogic_id(logic.getLogic_id());
            List<LogicRelation> logicRelationList=logicRelationMapper.queryForEntity(logicRelation);
            logic.setLogicRelationList(logicRelationList);
        }
        return logicNodeList;
    }

    @Override
    public void deleteById(String logic_id) {
        logicMapper.deleteById(logic_id);
        logicRelationMapper.deleteById(logic_id);
    }
}
