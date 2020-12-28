package com.lifuyi.dev_monitor.model.logic.resp;

import io.swagger.annotations.Api;

@Api(value = "逻辑查询返回实体")
public class LogicResp {

    private NodeResp nodeResp;
    private RelationResp relationResp;

    public LogicResp() {
    }

    public LogicResp(NodeResp nodeResp, RelationResp relationResp) {
        this.nodeResp = nodeResp;
        this.relationResp = relationResp;
    }

    public NodeResp getNodeResp() {
        return nodeResp;
    }

    public void setNodeResp(NodeResp nodeResp) {
        this.nodeResp = nodeResp;
    }

    public RelationResp getRelationResp() {
        return relationResp;
    }

    public void setRelationResp(RelationResp relationResp) {
        this.relationResp = relationResp;
    }
}
