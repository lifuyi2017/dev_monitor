package com.winterchen.model;

import java.io.Serializable;

public class LogicRelation implements Serializable {
    private static final long serialVersionUID = -1696975383303691876L;

    private String logic_id;
    private String measure_id;
    private String channel_id;

    public LogicRelation() {
    }

    public LogicRelation(String logic_id, String measure_id, String channel_id) {
        this.logic_id = logic_id;
        this.measure_id = measure_id;
        this.channel_id = channel_id;
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

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }
}
