package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.logic.LogicNode;

public interface LogicMapper {
    void clearByLogicId(String logic_id);

    void insertOrUpdateLogicNode(LogicNode logicNode);

    void insertRelation(String logic_id, String physical_id, String code);
}
