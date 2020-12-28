package com.lifuyi.dev_monitor.model.logic.req;

import com.lifuyi.dev_monitor.model.logic.LogicNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "分页查询实体")
public class LogicQueryReq {
    @ApiModelProperty(value = "查询参数实体" ,position = 1)
    private LogicNode logicNode;
    @ApiModelProperty(value = "页码" ,position = 2)
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量" ,position = 3)
    private Integer pageSize;

    public LogicQueryReq(LogicNode logicNode, Integer pageNum, Integer pageSize) {
        this.logicNode = logicNode;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public LogicQueryReq() {
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
