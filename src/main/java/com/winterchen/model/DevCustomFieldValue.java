package com.winterchen.model;

import java.io.Serializable;
import java.util.Date;

public class DevCustomFieldValue implements Serializable {
    private static final long serialVersionUID = -6592883464235444369L;

    private String dev_type_field_value_id;
    private String dev_element_id;
    private String dev_type_custom_field_id;
    private String value_string;
    private Date value_date;
    private byte[] value_blob;

    public DevCustomFieldValue() {
    }

    public DevCustomFieldValue(String dev_type_field_value_id, String dev_element_id, String dev_type_custom_field_id, String value_string, Date value_date, byte[] value_blob) {
        this.dev_type_field_value_id = dev_type_field_value_id;
        this.dev_element_id = dev_element_id;
        this.dev_type_custom_field_id = dev_type_custom_field_id;
        this.value_string = value_string;
        this.value_date = value_date;
        this.value_blob = value_blob;
    }

    public String getDev_type_field_value_id() {
        return dev_type_field_value_id;
    }

    public void setDev_type_field_value_id(String dev_type_field_value_id) {
        this.dev_type_field_value_id = dev_type_field_value_id;
    }

    public String getDev_element_id() {
        return dev_element_id;
    }

    public void setDev_element_id(String dev_element_id) {
        this.dev_element_id = dev_element_id;
    }

    public String getDev_type_custom_field_id() {
        return dev_type_custom_field_id;
    }

    public void setDev_type_custom_field_id(String dev_type_custom_field_id) {
        this.dev_type_custom_field_id = dev_type_custom_field_id;
    }

    public String getValue_string() {
        return value_string;
    }

    public void setValue_string(String value_string) {
        this.value_string = value_string;
    }

    public Date getValue_date() {
        return value_date;
    }

    public void setValue_date(Date value_date) {
        this.value_date = value_date;
    }

    public byte[] getValue_blob() {
        return value_blob;
    }

    public void setValue_blob(byte[] value_blob) {
        this.value_blob = value_blob;
    }
}
