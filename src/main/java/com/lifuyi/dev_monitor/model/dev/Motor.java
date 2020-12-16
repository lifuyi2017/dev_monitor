package com.lifuyi.dev_monitor.model.dev;

import lombok.Data;

@Data
public class Motor{

    private String id;

    private String dev_code;


    private String power;
    private String speed;
    private String electric_current;
    private String voltage;
    private String bearing;

    public Motor(String id, String dev_code, String power, String speed, String electric_current, String voltage, String bearing) {
        this.id = id;
        this.dev_code = dev_code;
        this.power = power;
        this.speed = speed;
        this.electric_current = electric_current;
        this.voltage = voltage;
        this.bearing = bearing;
    }

    public Motor() {
    }
}
