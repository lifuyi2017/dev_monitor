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

    @Override
    public int hashCode() {
        return logic_id.hashCode()+measure_id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;//地址相等
        }

        if(obj == null){
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if(obj instanceof LogicRelation){
            LogicRelation logicRelation = (LogicRelation) obj;
            //需要比较的字段相等，则这两个对象相等
            if(this.getLogic_id().equals(logicRelation.getLogic_id())
                    && this.getMeasure_id().equals(logicRelation.getMeasure_id())){
                return true;
            }
        }

        return false;
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
