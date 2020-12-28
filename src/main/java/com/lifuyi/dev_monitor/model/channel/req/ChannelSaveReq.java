package com.lifuyi.dev_monitor.model.channel.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class ChannelSaveReq {
    @ApiModelProperty(value = "参数" ,position = 1)
    private ChannelParameter channelParameter;
    @ApiModelProperty(value = "物理节点id" ,position = 2)
    private String physical_id;
    @ApiModelProperty(value = "通道id" ,position = 3)
    private List<String> codes;

    public ChannelSaveReq() {
    }

    public ChannelSaveReq(ChannelParameter channelParameter, String physical_id, List<String> codes) {
        this.channelParameter = channelParameter;
        this.physical_id = physical_id;
        this.codes = codes;
    }
}
