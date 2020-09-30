package com.winterchen.model;

import java.io.Serializable;

public class DevInputRequest implements Serializable {
    private static final long serialVersionUID = 2537535660129743453L;

    private String dev_element_name;

    private String type_element_id;

    private String enterprise_id;

    public DevInputRequest() {
    }

    public DevInputRequest(String dev_element_name, String type_element_id, String enterprise_id) {
        this.dev_element_name = dev_element_name;
        this.type_element_id = type_element_id;
        this.enterprise_id = enterprise_id;
    }

    public String getDev_element_name() {
        return dev_element_name;
    }

    public void setDev_element_name(String dev_element_name) {
        this.dev_element_name = dev_element_name;
    }

    public String getType_element_id() {
        return type_element_id;
    }

    public void setType_element_id(String type_name) {
        this.type_element_id = type_name;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
