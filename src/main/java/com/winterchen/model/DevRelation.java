package com.winterchen.model;

public class DevRelation {

    private Integer dev_id;
    private String collect_name;
    private String dev_name;
    private Integer parent_id;
    private Integer top_id;

    public DevRelation() {
    }

    public DevRelation(Integer dev_id, String collect_name, String dev_name, Integer parent_id, Integer top_id) {
        this.dev_id = dev_id;
        this.collect_name = collect_name;
        this.dev_name = dev_name;
        this.parent_id = parent_id;
        this.top_id = top_id;
    }

    public Integer getDev_id() {
        return dev_id;
    }

    public void setDev_id(Integer dev_id) {
        this.dev_id = dev_id;
    }

    public String getCollect_name() {
        return collect_name;
    }

    public void setCollect_name(String collect_name) {
        this.collect_name = collect_name;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getTop_id() {
        return top_id;
    }

    public void setTop_id(Integer top_id) {
        this.top_id = top_id;
    }
}
