package com.winterchen.model;

import java.io.Serializable;
import java.util.Map;

public class DevFieldValueRequest implements Serializable {
    private static final long serialVersionUID = 8326484447620154012L;


    private DevFixedFieldValue devFixedFieldValue;
    private Map<String,String> customFieldValue;

    public DevFieldValueRequest( DevFixedFieldValue devFixedFieldValue, Map<String, String> customFieldValue) {
        this.devFixedFieldValue = devFixedFieldValue;
        this.customFieldValue = customFieldValue;
    }

    public DevFieldValueRequest() {
    }

    public DevFixedFieldValue getDevFixedFieldValue() {
        return devFixedFieldValue;
    }

    public void setDevFixedFieldValue(DevFixedFieldValue devFixedFieldValue) {
        this.devFixedFieldValue = devFixedFieldValue;
    }

    public Map<String, String> getCustomFieldValue() {
        return customFieldValue;
    }

    public void setCustomFieldValue(Map<String, String> customFieldValue) {
        this.customFieldValue = customFieldValue;
    }


}
