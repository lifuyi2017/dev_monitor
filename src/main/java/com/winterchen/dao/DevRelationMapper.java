package com.winterchen.dao;

import com.winterchen.model.DevRelation;

public interface DevRelationMapper {
    DevRelation getRelationByDevId(Integer devId);
}
