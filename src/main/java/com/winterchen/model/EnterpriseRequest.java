package com.winterchen.model;

import java.io.Serializable;

public class EnterpriseRequest implements Serializable {

    private static final long serialVersionUID = 4420396788962019337L;
    private Enterprise enterprise;
    private Integer pageNum;
    private Integer pageSize;

    public EnterpriseRequest() {
    }

    public EnterpriseRequest(Enterprise enterprise, Integer pageNum, Integer pageSize) {
        this.enterprise = enterprise;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
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
