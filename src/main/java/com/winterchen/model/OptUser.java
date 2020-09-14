package com.winterchen.model;

import java.io.Serializable;
import java.util.List;

public class OptUser implements Serializable {
    private static final long serialVersionUID = 4890434666618420422L;

    private List<Integer> ids;
    private String status;

    public OptUser() {
    }

    public OptUser(List<Integer> ids, String status) {
        this.ids = ids;
        this.status = status;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
