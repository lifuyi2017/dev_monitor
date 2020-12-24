package com.lifuyi.dev_monitor.model.channel;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(value = "通道参数设置")
public class ChannelParameter {


    private String id;
    private String type;
    private String frequency;
    private String duration;
    private String accuracy;
    private String interval;

    public ChannelParameter(String id, String type, String frequency, String duration, String accuracy, String interval) {
        this.id = id;
        this.type = type;
        this.frequency = frequency;
        this.duration = duration;
        this.accuracy = accuracy;
        this.interval = interval;
    }

    public ChannelParameter() {
    }
}
