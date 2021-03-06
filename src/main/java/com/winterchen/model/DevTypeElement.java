package com.winterchen.model;

import com.winterchen.annotation.NotBlank;

import java.io.Serializable;

public class DevTypeElement implements Serializable {
    private static final long serialVersionUID = 6402097041480780576L;
    /**
     * 组件id(当type=2)或者设备类型id(当type=1)
     */
    private String dev_element_id;
    /**
     * 父组件id
     */
    private String dev_parent_element_id;
    /**
     * 组件名称
     */
    @NotBlank(message = "名称不能为空")
    private String dev_element_name;
    /**
     * 组件类型id：即设备类型id
     */
    private String dev_type_id;
    /**
     * 类型：1是设备类型，2是设备组件
     */
    private String type;

    private Boolean hasSon;

    public Boolean getHasSon() {
        return hasSon;
    }

    public void setHasSon(Boolean hasSon) {
        this.hasSon = hasSon;
    }

    public DevTypeElement() {
    }

    public DevTypeElement(String dev_element_id, String dev_parent_element_id, String dev_element_name, String dev_type_id, String type) {
        this.dev_element_id = dev_element_id;
        this.dev_parent_element_id = dev_parent_element_id;
        this.dev_element_name = dev_element_name;
        this.dev_type_id = dev_type_id;
        this.type = type;
    }

    public String getDev_element_id() {
        return dev_element_id;
    }

    public void setDev_element_id(String dev_element_id) {
        this.dev_element_id = dev_element_id;
    }

    public String getDev_parent_element_id() {
        return dev_parent_element_id;
    }

    public void setDev_parent_element_id(String dev_parent_element_id) {
        this.dev_parent_element_id = dev_parent_element_id;
    }

    public String getDev_element_name() {
        return dev_element_name;
    }

    public void setDev_element_name(String dev_element_name) {
        this.dev_element_name = dev_element_name;
    }

    public String getDev_type_id() {
        return dev_type_id;
    }

    public void setDev_type_id(String dev_type_id) {
        this.dev_type_id = dev_type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
