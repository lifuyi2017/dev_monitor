package com.winterchen.model;

import com.winterchen.annotation.NotBlank;

import java.io.Serializable;

public class Channel implements Serializable {
    private static final long serialVersionUID = 5111387602389352183L;
    private String channel_id;
    @NotBlank(message = "编码不能为空")
    private String channel_code;
    @NotBlank(message = "名称不能为空")
    private String channel_name;
    @NotBlank(message = "信号类型不能为空")
    private String signal_type;
    private String data_type;
    private String input_type;
    private String input_type_range;
    private String is_output_power;
    private String pin_num;
    @NotBlank(message = "所属测点不能为空")
    private String measure_id;
//    @NotBlank(message = "")
    private String enterprise_id;


    private String measure_name;
    private String enterprise_name;

    public Channel() {
    }

    public Channel(String channel_id, String channel_code, String channel_name, String signal_type, String data_type, String input_type, String input_type_range, String is_output_power, String pin_num, String measure_id, String enterprise_id) {
        this.channel_id = channel_id;
        this.channel_code = channel_code;
        this.channel_name = channel_name;
        this.signal_type = signal_type;
        this.data_type = data_type;
        this.input_type = input_type;
        this.input_type_range = input_type_range;
        this.is_output_power = is_output_power;
        this.pin_num = pin_num;
        this.measure_id = measure_id;
        this.enterprise_id = enterprise_id;
    }

    public String getMeasure_name() {
        return measure_name;
    }

    public void setMeasure_name(String measure_name) {
        this.measure_name = measure_name;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getSignal_type() {
        return signal_type;
    }

    public void setSignal_type(String signal_type) {
        this.signal_type = signal_type;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getInput_type() {
        return input_type;
    }

    public void setInput_type(String input_type) {
        this.input_type = input_type;
    }

    public String getInput_type_range() {
        return input_type_range;
    }

    public void setInput_type_range(String input_type_range) {
        this.input_type_range = input_type_range;
    }

    public String getIs_output_power() {
        return is_output_power;
    }

    public void setIs_output_power(String is_output_power) {
        this.is_output_power = is_output_power;
    }

    public String getPin_num() {
        return pin_num;
    }

    public void setPin_num(String pin_num) {
        this.pin_num = pin_num;
    }

    public String getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(String measure_id) {
        this.measure_id = measure_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
