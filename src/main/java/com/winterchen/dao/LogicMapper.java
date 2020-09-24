package com.winterchen.dao;

import com.winterchen.model.LogicNode;

import java.util.List;

public interface LogicMapper {
    void updateById(LogicNode logicNode);

    void insertEntity(LogicNode logicNode);

    List<LogicNode> queryForEntity(LogicNode logicNode);
}
