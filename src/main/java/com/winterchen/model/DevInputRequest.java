package com.winterchen.model;

import java.io.Serializable;

public class DevInputRequest implements Serializable {
    private static final long serialVersionUID = 2537535660129743453L;

    private String dev_element_name;

    private String type_name;

    public DevInputRequest() {
    }

    public DevInputRequest(String dev_element_name, String type_name) {
        this.dev_element_name = dev_element_name;
        this.type_name = type_name;
    }

    public String getDev_element_name() {
        return dev_element_name;
    }

    public void setDev_element_name(String dev_element_name) {
        this.dev_element_name = dev_element_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
