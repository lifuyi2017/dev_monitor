package com.lifuyi.dev_monitor.model.logic;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.List;
@Api(value = "逻辑节点基本信息实体")
public class LogicNode implements Serializable {
    private static final long serialVersionUID = -4118684387863013764L;

    @ApiModelProperty(value = "逻辑节点id，新增时可不传" ,position = 1)
    private String logic_id;
    @ApiModelProperty(value = "编号" ,position = 2)
    private String logic_code;
    @ApiModelProperty(value = "名称" ,position =3)
    private String logic_name;
    @ApiModelProperty(value = "企业id" ,position = 4)
    private String enterprise_id;


    public LogicNode() {
    }

    public LogicNode(String logic_id, String logic_code, String logic_name, String enterprise_id) {
        this.logic_id = logic_id;
        this.logic_code = logic_code;
        this.logic_name = logic_name;
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

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
}
