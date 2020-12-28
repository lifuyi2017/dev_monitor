package com.lifuyi.dev_monitor.model.channel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "通道参数设置")
public class ChannelParameter {

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
    @ApiModelProperty(value = "企业id" ,position = 8)
    private String enterprise_id;

    public ChannelParameter(String id, String type, String frequency, String duration, String accuracy, String interval, String name, String enterprise_id) {
        this.id = id;
        this.type = type;
        this.frequency = frequency;
        this.duration = duration;
        this.accuracy = accuracy;
        this.interval = interval;
        this.name = name;
        this.enterprise_id = enterprise_id;
    }

    public ChannelParameter() {
    }
}
