package com.lifuyi.dev_monitor.model.dev;

import lombok.Data;

@Data
public class Fan {

    private String id;

    private String dev_code;

    private String power;
    private String speed;
    private String flow;
    private String wind_pressure;
    private String bearing;

    public Fan(String id, String dev_code, String power, String speed, String flow, String wind_pressure, String bearing) {
        this.id = id;
        this.dev_code = dev_code;
        this.power = power;
        this.speed = speed;
        this.flow = flow;
        this.wind_pressure = wind_pressure;
        this.bearing = bearing;
    }

    public Fan() {
    }
}
