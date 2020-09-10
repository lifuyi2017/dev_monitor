package com.winterchen.model;

import java.util.Map;

public class DevFieldValue {

    private Integer topId;

    private Integer devId;

    /**
     * key是fieldName
     */
    private Map<String,FieldValue> valueMap;

    public DevFieldValue() {
    }

    public DevFieldValue(Integer topId, Integer devId, Map<String, FieldValue> valueMap) {
        this.topId = topId;
        this.devId = devId;
        this.valueMap = valueMap;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }


    public Map<String, FieldValue> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, FieldValue> valueMap) {
        this.valueMap = valueMap;
    }
}
