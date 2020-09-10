package com.winterchen.dao;

import com.winterchen.model.DevFieldValue;
import com.winterchen.model.DevOneFieldValue;

import java.util.List;

public interface DevFieldValueMapper {

    void insert(DevOneFieldValue devOneFieldValue);

    List<String> getIdByDevId(Integer devId);

    List<DevOneFieldValue> query(DevOneFieldValue devOneFieldValue);
}
