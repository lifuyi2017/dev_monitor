package com.winterchen.model;

import java.io.Serializable;
import java.util.List;

public class StartStopCollection implements Serializable {

    private static final long serialVersionUID = -3724273611024545899L;

    private  List<String> ids;
    private String status;

    public StartStopCollection() {
    }

    public StartStopCollection(List<String> ids, String status) {
        this.ids = ids;
        this.status = status;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
