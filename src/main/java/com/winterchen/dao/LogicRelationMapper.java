package com.winterchen.dao;

import com.winterchen.model.LogicRelation;

public interface LogicRelationMapper {
    void deleteByLogicId(String logic_id);

    void insert(LogicRelation logicRelation);
}
