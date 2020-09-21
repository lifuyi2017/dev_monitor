package com.winterchen.model;

import java.io.Serializable;

public class DevFieldValueRequestPage implements Serializable {

    private static final long serialVersionUID = 4351844199130104151L;
    private DevFieldValueRequest devFieldValueRequest;
    private Integer pageNum;
    private Integer pageSize;

    public DevFieldValueRequestPage() {
    }

    public DevFieldValueRequestPage(DevFieldValueRequest devFieldValueRequest, Integer pageNum, Integer pageSize) {
        this.devFieldValueRequest = devFieldValueRequest;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public DevFieldValueRequest getDevFieldValueRequest() {
        return devFieldValueRequest;
    }

    public void setDevFieldValueRequest(DevFieldValueRequest devFieldValueRequest) {
        this.devFieldValueRequest = devFieldValueRequest;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
