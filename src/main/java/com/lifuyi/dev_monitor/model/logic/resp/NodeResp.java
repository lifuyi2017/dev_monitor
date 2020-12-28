package com.lifuyi.dev_monitor.model.logic.resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "逻辑参数返回实体")
public class NodeResp {

    @ApiModelProperty(value = "逻辑节点id，新增时可不传" ,position = 1)
    private String logic_id;
    @ApiModelProperty(value = "编号" ,position = 2)
    private String logic_code;
    @ApiModelProperty(value = "名称" ,position =3)
    private String logic_name;
    @ApiModelProperty(value = "企业名称" ,position = 4)
    private String enterprise_name;

    public NodeResp() {
    }

    public NodeResp(String logic_id, String logic_code, String logic_name, String enterprise_name) {
        this.logic_id = logic_id;
        this.logic_code = logic_code;
        this.logic_name = logic_name;
        this.enterprise_name = enterprise_name;
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
}
