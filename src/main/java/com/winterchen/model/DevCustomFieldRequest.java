package com.winterchen.model;

import java.io.Serializable;
import java.util.List;

public class DevCustomFieldRequest implements Serializable {
    private static final long serialVersionUID = 3874065396595410388L;

    private String dev_element_id;
    private List<DevCustomField>  devCustomFieldList;

    public DevCustomFieldRequest() {
    }

    public DevCustomFieldRequest(String dev_element_id, List<DevCustomField> devCustomFieldList) {
        this.dev_element_id = dev_element_id;
        this.devCustomFieldList = devCustomFieldList;
    }

    public String getDev_element_id() {
        return dev_element_id;
    }

    public void setDev_element_id(String dev_element_id) {
        this.dev_element_id = dev_element_id;
    }

    public List<DevCustomField> getDevCustomFieldList() {
        return devCustomFieldList;
    }

    public void setDevCustomFieldList(List<DevCustomField> devCustomFieldList) {
        this.devCustomFieldList = devCustomFieldList;
    }
}
