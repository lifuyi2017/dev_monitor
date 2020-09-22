package com.winterchen.model;

import java.io.Serializable;

public class Network implements Serializable {
    private static final long serialVersionUID = 814257327912803972L;

    private String network_id;
    private String network_type;
    private String network_code;
    private String network_name;
    private String network_ip;
    private String input_address;
    private String output_agreement;
    private String enterprise_id;

    public Network() {
    }

    public Network(String network_id, String network_type, String network_code, String network_name, String network_ip, String input_address, String output_agreement, String enterprise_id) {
        this.network_id = network_id;
        this.network_type = network_type;
        this.network_code = network_code;
        this.network_name = network_name;
        this.network_ip = network_ip;
        this.input_address = input_address;
        this.output_agreement = output_agreement;
        this.enterprise_id = enterprise_id;
    }

    public String getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public String getNetwork_code() {
        return network_code;
    }

    public void setNetwork_code(String network_code) {
        this.network_code = network_code;
    }

    public String getNetwork_name() {
        return network_name;
    }

    public void setNetwork_name(String network_name) {
        this.network_name = network_name;
    }

    public String getNetwork_ip() {
        return network_ip;
    }

    public void setNetwork_ip(String network_ip) {
        this.network_ip = network_ip;
    }

    public String getInput_address() {
        return input_address;
    }

    public void setInput_address(String input_address) {
        this.input_address = input_address;
    }

    public String getOutput_agreement() {
        return output_agreement;
    }

    public void setOutput_agreement(String output_agreement) {
        this.output_agreement = output_agreement;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
