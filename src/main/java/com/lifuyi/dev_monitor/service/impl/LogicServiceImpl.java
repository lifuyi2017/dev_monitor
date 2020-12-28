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
import com.lifuyi.dev_monitor.service.LogicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        if(logicNode.getLogic_id()==null){
            logicNode.setLogic_id(id);
        }
        logicMapper.clearByLogicId(logicNode.getLogic_id());
        logicMapper.insertOrUpdateLogicNode(logicNode);
        for(LogicRelation relation:relationList){
            for(String code:relation.getChannel_code()){
                logicMapper.insertRelation(logicNode.getLogic_id(),relation.getPhysical_id(),code);
            }
        }
        return new ResultMessage<String>("200","操作成功",logicNode.getLogic_id());
    }

    @Override
    public ResultMessage<PageInfo<LogicResp>> getLogicPages(LogicQueryReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());


    }

}
