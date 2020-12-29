package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.LogicMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.logic.LogicNode;
import com.lifuyi.dev_monitor.model.logic.LogicRelation;
import com.lifuyi.dev_monitor.model.logic.req.LogicQueryReq;
import com.lifuyi.dev_monitor.model.logic.req.LogicSaveReq;
import com.lifuyi.dev_monitor.model.logic.resp.LogicResp;
import com.lifuyi.dev_monitor.model.logic.resp.RelationResp;
import com.lifuyi.dev_monitor.model.logic.resp.RelationTableResult;
import com.lifuyi.dev_monitor.service.LogicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("logicService")
public class LogicServiceImpl implements LogicService {

    @Resource
    private LogicMapper logicMapper;

    @Override
    public ResultMessage<String> addOrUpdateLogic(LogicSaveReq logicSaveReq) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        LogicNode logicNode = logicSaveReq.getLogicNode();
        List<LogicRelation> relationList = logicSaveReq.getRelationList();
        if(StringUtils.isBlank(logicNode.getLogic_id())){
            logicNode.setLogic_id(id);
        }
        logicMapper.clearByLogicId(logicNode.getLogic_id());
        logicMapper.insertOrUpdateLogicNode(logicNode);
        for(LogicRelation relation:relationList){
            for(String code:relation.getCodes()){
                logicMapper.insertRelation(logicNode.getLogic_id(),relation.getPhysical_id(),code);
            }
        }
        return new ResultMessage<String>("200","操作成功",logicNode.getLogic_id());
    }

    @Override
    public ResultMessage<PageInfo<LogicResp>> getLogicPages(LogicQueryReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<LogicResp> nodeRespList=logicMapper.getLogicNodePages(req.getLogicNode());
        PageInfo<LogicResp> logicRespPageInfo = new PageInfo<>(nodeRespList);
        for(LogicResp logicResp:nodeRespList){
            List<RelationResp> list = new ArrayList<>();
            List<RelationTableResult> resultList=logicMapper.getRelationByLogicId(logicResp.getLogic_id());
            for(RelationTableResult relationTableResult:resultList){
                list.add(new RelationResp(relationTableResult.getPhysical_name(),
                        Arrays.asList(relationTableResult.getCodes().split(","))));
            }
            logicResp.setRelationResp(list);
        }
        logicRespPageInfo.setList(nodeRespList);
        return new ResultMessage<PageInfo<LogicResp>>("200","查询成功",logicRespPageInfo);
    }

    @Override
    public ResultMessage<List<LogicResp>> getLogicList(LogicNode node) {
        List<LogicResp> nodeRespList=logicMapper.getLogicNodePages(node);
        return new ResultMessage<List<LogicResp>>("200","查询成功",nodeRespList);
    }

}
