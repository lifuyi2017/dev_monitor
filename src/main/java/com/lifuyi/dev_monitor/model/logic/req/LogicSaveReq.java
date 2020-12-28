package com.lifuyi.dev_monitor.model.logic.req;

import com.lifuyi.dev_monitor.model.logic.LogicNode;
import com.lifuyi.dev_monitor.model.logic.LogicRelation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api(value = "逻辑节点新增或者修改实体")
public class LogicSaveReq {
    @ApiModelProperty(value = "逻辑节点基本信息" ,position = 1)
    private LogicNode logicNode;
    @ApiModelProperty(value = "逻辑节点与通道的对应信息" ,position = 1)
    private List<LogicRelation> relationList;

    public LogicSaveReq(LogicNode logicNode, List<LogicRelation> relationList) {
        this.logicNode = logicNode;
        this.relationList = relationList;
    }

    public LogicSaveReq() {
    }

    public LogicNode getLogicNode() {
        return logicNode;
    }

    public void setLogicNode(LogicNode logicNode) {
        this.logicNode = logicNode;
    }

    public List<LogicRelation> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<LogicRelation> relationList) {
        this.relationList = relationList;
    }


}
