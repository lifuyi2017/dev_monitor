package com.winterchen.model;

import java.io.Serializable;
import java.util.List;

public class CollectionMqtt implements Serializable {
    private static final long serialVersionUID = 7088006461954820220L;

    private String measure_code;
    private List<String> channel_code;
    //频率
    private String collection_frequency;
    //时长
    private String collection_cycle;
    //精度
    private String collection_accuracy;
    //时间间隔
    private String collection_interval;
    private String flag;
    private String date_time;

    public CollectionMqtt(String measure_code, List<String> channel_code, String collection_frequency, String collection_cycle, String collection_accuracy, String collection_interval, String flag, String date_time) {
        this.measure_code = measure_code;
        this.channel_code = channel_code;
        this.collection_frequency = collection_frequency;
        this.collection_cycle = collection_cycle;
        this.collection_accuracy = collection_accuracy;
        this.collection_interval = collection_interval;
        this.flag = flag;
        this.date_time = date_time;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public CollectionMqtt() {
    }

    public String getMeasure_code() {
        return measure_code;
    }

    public void setMeasure_code(String measure_code) {
        this.measure_code = measure_code;
    }

    public List<String> getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(List<String> channel_code) {
        this.channel_code = channel_code;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
