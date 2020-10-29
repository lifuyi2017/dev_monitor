package com.winterchen.model;

import com.winterchen.annotation.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CollectionManagerHttp implements Serializable {
    private static final long serialVersionUID = 8832924100049616206L;

    private String collection_id;
    private String collection_type;
    //频率
    @NotBlank(message = "频率不能为空")
    private String collection_frequency;
    //时长
    @NotBlank(message = "时长不能为空")
    private String collection_cycle;
    //精度
    @NotBlank(message = "精度不能为空")
    private String collection_accuracy;
    //时间间隔
    @NotBlank(message = "时间间隔不能为空")
    private String collection_interval;
    @NotBlank(message = "物理节点不能为空")
    private String measure_id;
    @NotBlank(message = "采集通道不能为空")
    private List<String> channel_id;
    private String logic_id;
    @NotBlank(message = "设备id不能为空")
    private String dev_element_id;
    private String status;
    private Date update_time;

    private String measure_name;
    private List<String> channel_code;
    private String logic_name;


    public CollectionManagerHttp() {
    }

    public CollectionManagerHttp(String collection_id, String collection_type, String collection_frequency, String collection_cycle, String collection_accuracy, String collection_interval, String measure_id, List<String> channel_id, String logic_id, String dev_element_id, String status, Date update_time, String measure_name, List<String> channel_code, String logic_name) {
        this.collection_id = collection_id;
        this.collection_type = collection_type;
        this.collection_frequency = collection_frequency;
        this.collection_cycle = collection_cycle;
        this.collection_accuracy = collection_accuracy;
        this.collection_interval = collection_interval;
        this.measure_id = measure_id;
        this.channel_id = channel_id;
        this.logic_id = logic_id;
        this.dev_element_id = dev_element_id;
        this.status = status;
        this.update_time = update_time;
        this.measure_name = measure_name;
        this.channel_code = channel_code;
        this.logic_name = logic_name;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getCollection_type() {
        return collection_type;
    }

    public void setCollection_type(String collection_type) {
        this.collection_type = collection_type;
    }

    public String getCollection_frequency() {
        return collection_frequency;
    }

    public void setCollection_frequency(String collection_frequency) {
        this.collection_frequency = collection_frequency;
    }

    public String getCollection_cycle() {
        return collection_cycle;
    }

    public void setCollection_cycle(String collection_cycle) {
        this.collection_cycle = collection_cycle;
    }

    public String getCollection_accuracy() {
        return collection_accuracy;
    }

    public void setCollection_accuracy(String collection_accuracy) {
        this.collection_accuracy = collection_accuracy;
    }

    public String getCollection_interval() {
        return collection_interval;
    }

    public void setCollection_interval(String collection_interval) {
        this.collection_interval = collection_interval;
    }

    public String getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(String measure_id) {
        this.measure_id = measure_id;
    }

    public List<String> getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(List<String> channel_id) {
        this.channel_id = channel_id;
    }

    public String getLogic_id() {
        return logic_id;
    }

    public void setLogic_id(String logic_id) {
        this.logic_id = logic_id;
    }

    public String getDev_element_id() {
        return dev_element_id;
    }

    public void setDev_element_id(String dev_element_id) {
        this.dev_element_id = dev_element_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getMeasure_name() {
        return measure_name;
    }

    public void setMeasure_name(String measure_name) {
        this.measure_name = measure_name;
    }


    public List<String> getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(List<String> channel_code) {
        this.channel_code = channel_code;
    }

    public String getLogic_name() {
        return logic_name;
    }

    public void setLogic_name(String logic_name) {
        this.logic_name = logic_name;
    }
}
