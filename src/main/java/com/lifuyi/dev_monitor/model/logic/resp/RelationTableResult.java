package com.lifuyi.dev_monitor.model.logic.resp;

public class RelationTableResult {
    private String physical_name;
    private String codes;
    private String physical_id;

    public RelationTableResult(String physical_name, String codes, String physical_id) {
        this.physical_name = physical_name;
        this.codes = codes;
        this.physical_id = physical_id;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getPhysical_id() {
        return physical_id;
    }

    public void setPhysical_id(String physical_id) {
        this.physical_id = physical_id;
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


}
