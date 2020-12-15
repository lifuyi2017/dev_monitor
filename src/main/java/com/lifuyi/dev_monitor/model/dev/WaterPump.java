package com.lifuyi.dev_monitor.model.dev;

import lombok.Data;

@Data
public class WaterPump {

    private String dev_code;

    private String power;
    private String speed;
    private String flow;
    private String lift;
    private String bearing;


    public WaterPump(String dev_code, String power, String speed, String flow, String lift, String bearing) {
        this.dev_code = dev_code;
        this.power = power;
        this.speed = speed;
        this.flow = flow;
        this.lift = lift;
        this.bearing = bearing;
    }
}
