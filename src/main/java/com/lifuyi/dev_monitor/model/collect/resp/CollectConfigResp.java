package com.lifuyi.dev_monitor.model.collect.resp;

import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "采集配置查询返回实体")
public class CollectConfigResp {

    @ApiModelProperty(value = "id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "信号类型" ,position = 2)
    private String signal;
    @ApiModelProperty(value = "相位，逗号分割" ,position = 3)
    private String phase;
    @ApiModelProperty(value = "物理节点id" ,position = 4)
    private String physical_id;
    @ApiModelProperty(value = "通道分类id" ,position = 5)
    private String channel_type_id;
    @ApiModelProperty(value = "通道编号，逗号分割，顺序与相位对应" ,position = 6)
    private String channel_code;
    @ApiModelProperty(value = "逻辑id" ,position = 7)
    private String logic_id;
    @ApiModelProperty(value = "设备或者设备组id" ,position = 8)
    private String collect_dev_id;
    @ApiModelProperty(value = "物理节点名称" ,position = 9)
    private String physical_name;
    @ApiModelProperty(value = "通道分类参数" ,position = 10)
    private ChannelParameter channelParameter;
    @ApiModelProperty(value = "逻辑节点名称" ,position = 11)
    private String logic_name;
    @ApiModelProperty(value = "状态：0是停止采集状态、1是采集状态" ,position = 12)
    private String state;

    public CollectConfigResp(String id, String signal, String phase, String physical_id, String channel_type_id, String channel_code, String logic_id, String collect_dev_id, String physical_name, ChannelParameter channelParameter, String logic_name, String state) {
        this.id = id;
        this.signal = signal;
        this.phase = phase;
        this.physical_id = physical_id;
        this.channel_type_id = channel_type_id;
        this.channel_code = channel_code;
        this.logic_id = logic_id;
        this.collect_dev_id = collect_dev_id;
        this.physical_name = physical_name;
        this.channelParameter = channelParameter;
        this.logic_name = logic_name;
        this.state = state;
    }

    public CollectConfigResp() {
    }
}
