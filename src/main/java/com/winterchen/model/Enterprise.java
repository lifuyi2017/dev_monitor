package com.winterchen.model;

import java.io.Serializable;

public class Enterprise implements Serializable {
    private static final long serialVersionUID = -8382035963349615385L;

    private String enterprise_id;
    private String enterprise_name;
    private String enterprise_code;
    private String enterprise_type;

    public Enterprise() {
    }

    public Enterprise(String enterprise_id, String enterprise_name, String enterprise_code, String enterprise_type) {
        this.enterprise_id = enterprise_id;
        this.enterprise_name = enterprise_name;
        this.enterprise_code = enterprise_code;
        this.enterprise_type = enterprise_type;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getEnterprise_code() {
        return enterprise_code;
    }

    public void setEnterprise_code(String enterprise_code) {
        this.enterprise_code = enterprise_code;
    }

    public String getEnterprise_type() {
        return enterprise_type;
    }

    public void setEnterprise_type(String enterprise_type) {
        this.enterprise_type = enterprise_type;
    }
}
