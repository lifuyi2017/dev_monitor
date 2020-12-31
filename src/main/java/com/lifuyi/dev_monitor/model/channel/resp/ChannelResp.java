package com.lifuyi.dev_monitor.model.channel.resp;

import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
@Api(value = "通道参数实体")
public class ChannelResp {

    @ApiModelProperty(value = "id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "类型" ,position = 2)
    private String type;
    @ApiModelProperty(value = "频率" ,position = 3)
    private String frequency;
    @ApiModelProperty(value = "时长" ,position = 4)
    private String duration;
    @ApiModelProperty(value = "精度" ,position = 5)
    private String accuracy;
    @ApiModelProperty(value = "时间间隔" ,position = 6)
    private String interval;
    @ApiModelProperty(value = "采集名称" ,position = 7)
    private String name;
    @ApiModelProperty(value = "企业id" ,position = 11)
    private String enterprise_id;
    @ApiModelProperty(value = "企业名称" ,position = 8)
    private String enterprise_name;
    @ApiModelProperty(value = "物理节点名称" ,position = 9)
    private String physical_name;
    @ApiModelProperty(value = "物理节点id" ,position = 12)
    private String physical_id;
    @ApiModelProperty(value = "通道名称" ,position = 10)
    private List<String> codes;

    public ChannelResp(String id, String type, String frequency, String duration, String accuracy, String interval, String name, String enterprise_name, String physical_name, List<String> codes) {
        this.id = id;
        this.type = type;
        this.frequency = frequency;
        this.duration = duration;
        this.accuracy = accuracy;
        this.interval = interval;
        this.name = name;
        this.enterprise_name = enterprise_name;
        this.physical_name = physical_name;
        this.codes = codes;
    }

    public ChannelResp() {
    }
}
