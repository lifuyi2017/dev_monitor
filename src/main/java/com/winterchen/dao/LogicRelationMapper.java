package com.winterchen.dao;

import com.winterchen.model.LogicRelation;

import java.util.List;

public interface LogicRelationMapper {
    void deleteByLogicId(String logic_id);

    void insert(String logic_id,String measure_id,String id);

    List<LogicRelation> queryForEntity(LogicRelation logicRelation);

    void deleteById(String logic_id);

    List<String> getChannelIdByEntity(LogicRelation relation);

    void deleteByMeasureId(String measure_id);

    void deleteByChannelId(String channel_id);
}
