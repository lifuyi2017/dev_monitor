package com.winterchen.model;

import java.io.Serializable;

public class Measure implements Serializable {
    private static final long serialVersionUID = 198318853362151967L;

    private String measure_id;
    private String measure_type;
    private String measure_code;
    private String measure_name;
    private String measure_ip;
    private String measure_channel_num;
    private String network_id;
    private String enterprise_id;

    private String network_name;
    private String enterprise_name;

    public String getNetwork_name() {
        return network_name;
    }

    public void setNetwork_name(String network_name) {
        this.network_name = network_name;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public Measure() {
    }

    public Measure(String measure_id, String measure_type, String measure_code, String measure_name, String measure_ip, String measure_channel_num, String network_id, String enterprise_id) {
        this.measure_id = measure_id;
        this.measure_type = measure_type;
        this.measure_code = measure_code;
        this.measure_name = measure_name;
        this.measure_ip = measure_ip;
        this.measure_channel_num = measure_channel_num;
        this.network_id = network_id;
        this.enterprise_id = enterprise_id;
    }

    public String getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(String measure_id) {
        this.measure_id = measure_id;
    }

    public String getMeasure_type() {
        return measure_type;
    }

    public void setMeasure_type(String measure_type) {
        this.measure_type = measure_type;
    }

    public String getMeasure_code() {
        return measure_code;
    }

    public void setMeasure_code(String measure_code) {
        this.measure_code = measure_code;
    }

    public String getMeasure_name() {
        return measure_name;
    }

    public void setMeasure_name(String measure_name) {
        this.measure_name = measure_name;
    }

    public String getMeasure_ip() {
        return measure_ip;
    }

    public void setMeasure_ip(String measure_ip) {
        this.measure_ip = measure_ip;
    }

    public String getMeasure_channel_num() {
        return measure_channel_num;
    }

    public void setMeasure_channel_num(String measure_channel_num) {
        this.measure_channel_num = measure_channel_num;
    }

    public String getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
