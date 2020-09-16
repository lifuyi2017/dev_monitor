package com.winterchen.model;

import java.io.Serializable;

public class DevCustomField implements Serializable {
    private static final long serialVersionUID = 9121551722174871222L;

    private String dev_type_custom_field_id;
    private String dev_element_id;
    private String dev_type_field_name;
    private String dev_type_field_type;

    public DevCustomField() {
    }

    public DevCustomField(String dev_type_custom_field_id, String dev_element_id, String dev_type_field_name, String dev_type_field_type) {
        this.dev_type_custom_field_id = dev_type_custom_field_id;
        this.dev_element_id = dev_element_id;
        this.dev_type_field_name = dev_type_field_name;
        this.dev_type_field_type = dev_type_field_type;
    }

    public String getDev_type_custom_field_id() {
        return dev_type_custom_field_id;
    }

    public void setDev_type_custom_field_id(String dev_type_custom_field_id) {
        this.dev_type_custom_field_id = dev_type_custom_field_id;
    }

    public String getDev_element_id() {
        return dev_element_id;
    }

    public void setDev_element_id(String dev_element_id) {
        this.dev_element_id = dev_element_id;
    }

    public String getDev_type_field_name() {
        return dev_type_field_name;
    }

    public void setDev_type_field_name(String dev_type_field_name) {
        this.dev_type_field_name = dev_type_field_name;
    }

    public String getDev_type_field_type() {
        return dev_type_field_type;
    }

    public void setDev_type_field_type(String dev_type_field_type) {
        this.dev_type_field_type = dev_type_field_type;
    }
}
