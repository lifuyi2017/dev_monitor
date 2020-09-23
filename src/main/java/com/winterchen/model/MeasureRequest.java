package com.winterchen.model;

import java.io.Serializable;

public class MeasureRequest implements Serializable {
    private static final long serialVersionUID = -3264953621050855785L;

    private Measure measure;
    private Integer pageNum;
    private Integer pageSize;

    public MeasureRequest() {
    }

    public MeasureRequest(Measure measure, Integer pageNum, Integer pageSize) {
        this.measure = measure;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
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
