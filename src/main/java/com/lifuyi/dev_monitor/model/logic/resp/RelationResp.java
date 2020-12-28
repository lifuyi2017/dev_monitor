package com.lifuyi.dev_monitor.model.logic.resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@Api(value = "逻辑节点关系返回实体")
public class RelationResp {

    @ApiModelProperty(value = "物理节点名称" ,position =1)
    private String physical_name;
    @ApiModelProperty(value = "通道编号" ,position = 2)
    private List<String> channel_code;

    public RelationResp(String physical_name, List<String> channel_code) {
        this.physical_name = physical_name;
        this.channel_code = channel_code;
    }

    public RelationResp() {
    }

    public String getPhysical_name() {
        return physical_name;
    }

    public void setPhysical_name(String physical_name) {
        this.physical_name = physical_name;
    }

    public List<String> getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(List<String> channel_code) {
        this.channel_code = channel_code;
    }
}
