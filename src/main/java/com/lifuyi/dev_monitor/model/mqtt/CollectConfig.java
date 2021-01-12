package com.lifuyi.dev_monitor.model.mqtt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "发送到mqtt的消息")
public class CollectConfig {

    @ApiModelProperty(value = "信号类型" ,position = 1)
    private String signal;
    @ApiModelProperty(value = "相位，逗号分割" ,position = 2)
    private String phase;
    @ApiModelProperty(value = "物理节点编号" ,position = 3)
    private String physical_code;
    @ApiModelProperty(value = "通道编号，逗号分割，顺序与相位对应" ,position = 4)
    private String channel_code;
    @ApiModelProperty(value = "逻辑节点编号" ,position = 5)
    private String logic_code;
    @ApiModelProperty(value = "设备或者设备组名称" ,position = 6)
    private String collect_dev_name;
    @ApiModelProperty(value = "类型" ,position = 7)
    private String type;
    @ApiModelProperty(value = "频率" ,position = 8)
    private String frequency;
    @ApiModelProperty(value = "时长" ,position = 9)
    private String duration;
    @ApiModelProperty(value = "精度" ,position = 10)
    private String accuracy;
    @ApiModelProperty(value = "时间间隔" ,position = 11)
    private String interval;
    @ApiModelProperty(value = "状态：0是停止采集状态、1是采集状态" ,position = 12)
    private String state;

    public CollectConfig() {
    }

    public CollectConfig(String signal, String phase, String physical_code, String channel_code, String logic_code, String collect_dev_name, String type, String frequency, String duration, String accuracy, String interval, String state) {
        this.signal = signal;
        this.phase = phase;
        this.physical_code = physical_code;
        this.channel_code = channel_code;
        this.logic_code = logic_code;
        this.collect_dev_name = collect_dev_name;
        this.type = type;
        this.frequency = frequency;
        this.duration = duration;
        this.accuracy = accuracy;
        this.interval = interval;
        this.state = state;
    }
}
