package com.winterchen.service.user;

import com.winterchen.model.LogicNode;

import java.util.List;

public interface LogicService {
    void updateById(LogicNode logicNode) throws Exception;

    void insertEntity(LogicNode logicNode) throws Exception;

    List<LogicNode> queryByEntity(LogicNode logicNode) throws Exception;

    void deleteById(String logic_id);
}
