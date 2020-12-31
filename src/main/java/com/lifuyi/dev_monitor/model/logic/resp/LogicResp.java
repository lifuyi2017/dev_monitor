package com.lifuyi.dev_monitor.model.logic.resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@Api(value = "逻辑查询返回实体")
public class LogicResp {

    @ApiModelProperty(value = "逻辑节点id，新增时可不传" ,position = 1)
    private String logic_id;
    @ApiModelProperty(value = "编号" ,position = 2)
    private String logic_code;
    @ApiModelProperty(value = "名称" ,position =3)
    private String logic_name;
    @ApiModelProperty(value = "企业id" ,position = 5)
    private String enterprise_id;
    @ApiModelProperty(value = "企业名称" ,position = 4)
    private String enterprise_name;
    @ApiModelProperty(value = "逻辑关联信息" ,position = 4)
    private List<RelationResp> relationResp;

    public LogicResp() {
    }

    public LogicResp(String logic_id, String logic_code, String logic_name, String enterprise_id, String enterprise_name, List<RelationResp> relationResp) {
        this.logic_id = logic_id;
        this.logic_code = logic_code;
        this.logic_name = logic_name;
        this.enterprise_id = enterprise_id;
        this.enterprise_name = enterprise_name;
        this.relationResp = relationResp;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getLogic_id() {
        return logic_id;
    }

    public void setLogic_id(String logic_id) {
        this.logic_id = logic_id;
    }

    public String getLogic_code() {
        return logic_code;
    }

    public void setLogic_code(String logic_code) {
        this.logic_code = logic_code;
    }

    public String getLogic_name() {
        return logic_name;
    }

    public void setLogic_name(String logic_name) {
        this.logic_name = logic_name;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public List<RelationResp> getRelationResp() {
        return relationResp;
    }

    public void setRelationResp(List<RelationResp> relationResp) {
        this.relationResp = relationResp;
    }
}
