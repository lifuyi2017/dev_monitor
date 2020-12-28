package com.lifuyi.dev_monitor.model.logic.resp;

public class RelationTableResult {
    private String physical_name;
    private String codes;

    public RelationTableResult(String physical_name, String channel_code) {
        this.physical_name = physical_name;
        this.codes = channel_code;
    }

    public RelationTableResult() {
    }

    public String getPhysical_name() {
        return physical_name;
    }

    public void setPhysical_name(String physical_name) {
        this.physical_name = physical_name;
    }

    public String getCodes() {
        return codes;
    }

    public void setChannel_code(String channel_code) {
        this.codes = channel_code;
    }
}
