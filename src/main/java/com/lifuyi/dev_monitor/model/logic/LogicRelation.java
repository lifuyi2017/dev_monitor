package com.lifuyi.dev_monitor.model.logic;

import com.lifuyi.dev_monitor.model.physical.Physical;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
@Api(value = "逻辑节点基本信息实体")
public class LogicRelation implements Serializable {
    private static final long serialVersionUID = -1696975383303691876L;

    @ApiModelProperty(value = "逻辑节点id，新增时可不传" ,position = 1)
    private String logic_id;
    @ApiModelProperty(value = "物理节点id" ,position =2)
    private String physical_id;
    @ApiModelProperty(value = "通道编号" ,position = 3)
    private List<String> codes;

    public LogicRelation() {
    }

    public LogicRelation(String logic_id, String physical_id, List<String> channel_code) {
        this.logic_id = logic_id;
        this.physical_id = physical_id;
        this.codes = channel_code;
    }


    public String getLogic_id() {
        return logic_id;
    }

    public void setLogic_id(String logic_id) {
        this.logic_id = logic_id;
    }

    public String getPhysical_id() {
        return physical_id;
    }

    public void setPhysical_id(String physical_id) {
        this.physical_id = physical_id;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setChannel_code(List<String> channel_code) {
        this.codes = channel_code;
    }
}
