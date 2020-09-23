package com.winterchen.model;

import java.io.Serializable;

public class NetworkRequest implements Serializable {
    private static final long serialVersionUID = -4166083914831754574L;

    private Network network;
    private Integer pageNum;
    private Integer pageSize;

    public NetworkRequest() {
    }

    public NetworkRequest(Network network, Integer pageNum, Integer pageSize) {
        this.network = network;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
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
