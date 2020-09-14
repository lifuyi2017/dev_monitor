package com.winterchen.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 6414098616140717985L;

    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_real_name;
    private String user_phone;
    private String user_status;
    private String enterprise_id;

    public User() {
    }

    public User(Integer user_id, String user_name, String user_password, String user_real_name, String user_phone, String user_status, String enterprise_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_real_name = user_real_name;
        this.user_phone = user_phone;
        this.user_status = user_status;
        this.enterprise_id = enterprise_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
