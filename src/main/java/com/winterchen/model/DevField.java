package com.winterchen.model;

public class DevField {

    private String field_id;
    private String field_name;
    private String field_type;
    private Integer dev_id;

    public DevField() {
    }

    public DevField(String field_id, String field_name, String field_type, Integer dev_id) {
        this.field_id = field_id;
        this.field_name = field_name;
        this.field_type = field_type;
        this.dev_id = dev_id;
    }

    public String getField_id() {
        return field_id;
    }

    public void setField_id(String field_id) {
        this.field_id = field_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    public Integer getDev_id() {
        return dev_id;
    }

    public void setDev_id(Integer dev_id) {
        this.dev_id = dev_id;
    }
}
