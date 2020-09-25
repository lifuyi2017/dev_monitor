package com.winterchen.dao;

import com.winterchen.model.LogicRelation;

import java.util.List;

public interface LogicRelationMapper {
    void deleteByLogicId(String logic_id);

    void insert(LogicRelation logicRelation);

    List<LogicRelation> queryForEntity(LogicRelation logicRelation);

    void deleteById(String logic_id);
}
