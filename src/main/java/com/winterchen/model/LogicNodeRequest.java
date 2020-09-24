package com.winterchen.model;

import java.io.Serializable;

public class LogicNodeRequest implements Serializable {
    private static final long serialVersionUID = 8663313745764156001L;

    private LogicNode logicNode;
    private Integer pageNum;
    private Integer pageSize;

    public LogicNodeRequest() {
    }

    public LogicNodeRequest(LogicNode logicNode, Integer pageNum, Integer pageSize) {
        this.logicNode = logicNode;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


    public LogicNode getLogicNode() {
        return logicNode;
    }

    public void setLogicNode(LogicNode logicNode) {
        this.logicNode = logicNode;
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
