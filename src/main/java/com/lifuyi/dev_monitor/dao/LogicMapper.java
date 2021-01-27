package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.logic.LogicNode;
import com.lifuyi.dev_monitor.model.logic.resp.LogicResp;
import com.lifuyi.dev_monitor.model.logic.resp.RelationTableResult;

import java.util.List;

public interface LogicMapper {
    void clearByLogicId(String logic_id);

    void insertOrUpdateLogicNode(LogicNode logicNode);

    void insertRelation(String logic_id, String physical_id, String code);

    List<LogicResp> getLogicNodePages(LogicNode logicNode);

    List<RelationTableResult> getRelationByLogicId(String logic_id);

    void deleteRelationByPhysicalId(String id);

    void deleteNodeById(String logic_id);

    void deleteRelationById(String logic_id);
}
