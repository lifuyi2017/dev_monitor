package com.winterchen.model;

import java.io.Serializable;
import java.util.List;

public class LogicRelation implements Serializable {
    private static final long serialVersionUID = -1696975383303691876L;

    private String logic_id;
    private String measure_id;
    private List<String> channel_id_list;

    private String measure_name;
    private List<String> channel_name_list;

    public String getMeasure_name() {
        return measure_name;
    }

    public void setMeasure_name(String measure_name) {
        this.measure_name = measure_name;
    }


    public LogicRelation() {
    }

    public LogicRelation(String logic_id, String measure_id, List<String> channel_id_list) {
        this.logic_id = logic_id;
        this.measure_id = measure_id;
        this.channel_id_list = channel_id_list;
    }

    public String getLogic_id() {
        return logic_id;
    }

    public void setLogic_id(String logic_id) {
        this.logic_id = logic_id;
    }

    public String getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(String measure_id) {
        this.measure_id = measure_id;
    }

    public List<String> getChannel_id_list() {
        return channel_id_list;
    }

    public void setChannel_id_list(List<String> channel_id_list) {
        this.channel_id_list = channel_id_list;
    }

    public List<String> getChannel_name_list() {
        return channel_name_list;
    }

    public void setChannel_name_list(List<String> channel_name_list) {
        this.channel_name_list = channel_name_list;
    }
}
