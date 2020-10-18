package com.winterchen.model;

import java.io.Serializable;

public class DevFixedFieldValue implements Serializable {
    private static final long serialVersionUID = 8739185363008595755L;

    private String dev_type_field_value_id;
    private String dev_type_operate_enterprise_id;
    private String dev_type_service_enterprise_id;
    private String dev_type_production_enterprise_id;

    private String dev_type_operate_enterprise_name;
    private String dev_type_service_enterprise_name;
    private String dev_type_production_enterprise_name;

    private String dev_element_id;
    private String dev_type;
    private String dev_type_code;
    private String dev_type_name;

    private String dev_type_charge_user_name;

    private byte[] dev_type_pic;
    private String dev_type_pic_str;

    public DevFixedFieldValue() {
    }

    public DevFixedFieldValue(String dev_type_field_value_id, String dev_type_operate_enterprise_id, String dev_type_service_enterprise_id, String dev_type_production_enterprise_id, String dev_type_operate_enterprise_name, String dev_type_service_enterprise_name, String dev_type_production_enterprise_name, String dev_element_id, String dev_type, String dev_type_code, String dev_type_name, String dev_type_charge_user_name, byte[] dev_type_pic) {
        this.dev_type_field_value_id = dev_type_field_value_id;
        this.dev_type_operate_enterprise_id = dev_type_operate_enterprise_id;
        this.dev_type_service_enterprise_id = dev_type_service_enterprise_id;
        this.dev_type_production_enterprise_id = dev_type_production_enterprise_id;
        this.dev_type_operate_enterprise_name = dev_type_operate_enterprise_name;
        this.dev_type_service_enterprise_name = dev_type_service_enterprise_name;
        this.dev_type_production_enterprise_name = dev_type_production_enterprise_name;
        this.dev_element_id = dev_element_id;
        this.dev_type = dev_type;
        this.dev_type_code = dev_type_code;
        this.dev_type_name = dev_type_name;
        this.dev_type_charge_user_name = dev_type_charge_user_name;
        this.dev_type_pic = dev_type_pic;
    }

    public DevFixedFieldValue(String dev_type_field_value_id, String dev_type_operate_enterprise_id, String dev_type_service_enterprise_id, String dev_type_production_enterprise_id, String dev_element_id, String dev_type, String dev_type_code, String dev_type_name, String dev_type_charge_user_name, String dev_type_pic_str) {
        this.dev_type_field_value_id = dev_type_field_value_id;
        this.dev_type_operate_enterprise_id = dev_type_operate_enterprise_id;
        this.dev_type_service_enterprise_id = dev_type_service_enterprise_id;
        this.dev_type_production_enterprise_id = dev_type_production_enterprise_id;
        this.dev_element_id = dev_element_id;
        this.dev_type = dev_type;
        this.dev_type_code = dev_type_code;
        this.dev_type_name = dev_type_name;
        this.dev_type_charge_user_name = dev_type_charge_user_name;
        this.dev_type_pic_str = dev_type_pic_str;
    }

    public DevFixedFieldValue(String dev_type_field_value_id, String dev_type_operate_enterprise_id, String dev_type_service_enterprise_id, String dev_type_production_enterprise_id, String dev_element_id, String dev_type, String dev_type_code, String dev_type_name, String dev_type_charge_user_name, byte[] dev_type_pic) {
        this.dev_type_field_value_id = dev_type_field_value_id;
        this.dev_type_operate_enterprise_id = dev_type_operate_enterprise_id;
        this.dev_type_service_enterprise_id = dev_type_service_enterprise_id;
        this.dev_type_production_enterprise_id = dev_type_production_enterprise_id;
        this.dev_element_id = dev_element_id;
        this.dev_type = dev_type;
        this.dev_type_code = dev_type_code;
        this.dev_type_name = dev_type_name;
        this.dev_type_charge_user_name = dev_type_charge_user_name;
        this.dev_type_pic = dev_type_pic;
    }

    public String getDev_type_operate_enterprise_name() {
        return dev_type_operate_enterprise_name;
    }

    public void setDev_type_operate_enterprise_name(String dev_type_operate_enterprise_name) {
        this.dev_type_operate_enterprise_name = dev_type_operate_enterprise_name;
    }

    public String getDev_type_service_enterprise_name() {
        return dev_type_service_enterprise_name;
    }

    public void setDev_type_service_enterprise_name(String dev_type_service_enterprise_name) {
        this.dev_type_service_enterprise_name = dev_type_service_enterprise_name;
    }

    public String getDev_type_production_enterprise_name() {
        return dev_type_production_enterprise_name;
    }

    public void setDev_type_production_enterprise_name(String dev_type_production_enterprise_name) {
        this.dev_type_production_enterprise_name = dev_type_production_enterprise_name;
    }

    public String getDev_type_charge_user_name() {
        return dev_type_charge_user_name;
    }

    public void setDev_type_charge_user_name(String dev_type_charge_user_name) {
        this.dev_type_charge_user_name = dev_type_charge_user_name;
    }

    public String getDev_type_field_value_id() {
        return dev_type_field_value_id;
    }

    public void setDev_type_field_value_id(String dev_type_field_value_id) {
        this.dev_type_field_value_id = dev_type_field_value_id;
    }

    public String getDev_type_operate_enterprise_id() {
        return dev_type_operate_enterprise_id;
    }

    public void setDev_type_operate_enterprise_id(String dev_type_operate_enterprise_id) {
        this.dev_type_operate_enterprise_id = dev_type_operate_enterprise_id;
    }

    public String getDev_type_service_enterprise_id() {
        return dev_type_service_enterprise_id;
    }

    public void setDev_type_service_enterprise_id(String dev_type_service_enterprise_id) {
        this.dev_type_service_enterprise_id = dev_type_service_enterprise_id;
    }

    public String getDev_type_production_enterprise_id() {
        return dev_type_production_enterprise_id;
    }

    public void setDev_type_production_enterprise_id(String dev_type_production_enterprise_id) {
        this.dev_type_production_enterprise_id = dev_type_production_enterprise_id;
    }

    public String getDev_element_id() {
        return dev_element_id;
    }

    public void setDev_element_id(String dev_element_id) {
        this.dev_element_id = dev_element_id;
    }

    public String getDev_type() {
        return dev_type;
    }

    public void setDev_type(String dev_type) {
        this.dev_type = dev_type;
    }

    public String getDev_type_code() {
        return dev_type_code;
    }

    public void setDev_type_code(String dev_type_code) {
        this.dev_type_code = dev_type_code;
    }

    public String getDev_type_name() {
        return dev_type_name;
    }

    public void setDev_type_name(String dev_type_name) {
        this.dev_type_name = dev_type_name;
    }


    public byte[] getDev_type_pic() {
        return dev_type_pic;
    }

    public void setDev_type_pic(byte[] dev_type_pic) {
        this.dev_type_pic = dev_type_pic;
    }

    public String getDev_type_pic_str() {
        return dev_type_pic_str;
    }

    public void setDev_type_pic_str(String dev_type_pic_str) {
        this.dev_type_pic_str = dev_type_pic_str;
    }
}
