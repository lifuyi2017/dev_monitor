package com.winterchen.model;

import org.apache.commons.lang3.StringUtils;

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

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;//地址相等
        }

        if(obj == null){
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if(obj instanceof DevCustomField){
            DevCustomField devCustomField = (DevCustomField) obj;
            //需要比较的字段相等，则这两个对象相等
            if(equalsStr(this.dev_element_id, devCustomField.dev_element_id)
                    && equalsStr(this.dev_type_field_name, devCustomField.dev_type_field_name)
            && equalsStr(this.dev_type_field_type, devCustomField.dev_type_field_type)){
                return true;
            }
        }

        return false;
    }

    private boolean equalsStr(String str1, String str2){
        if(StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)){
            return true;
        }
        if(!StringUtils.isEmpty(str1) && str1.equals(str2)){
            return true;
        }
        return false;
    }

}
