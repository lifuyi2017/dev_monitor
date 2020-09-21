package com.winterchen.model;

import java.io.Serializable;

public class EnterpriseRequest implements Serializable {

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


}
