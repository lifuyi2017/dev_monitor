package com.lifuyi.dev_monitor.model.dev;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "电机实体")
public class Motor{
    @ApiModelProperty(value = "id,与基本实体的id一致" ,position = 1)
    private String id;
    @ApiModelProperty(value = "功率" ,position = 2)
    private String power;
    @ApiModelProperty(value = "转速" ,position = 3)
    private String speed;
    @ApiModelProperty(value = "电流" ,position = 4)
    private String electric_current;
    @ApiModelProperty(value = "电压" ,position = 5)
    private String voltage;
    @ApiModelProperty(value = "轴承类型" ,position = 6)
    private String bearing;

    public Motor(String id, String power, String speed, String electric_current, String voltage, String bearing) {
        this.id = id;
        this.power = power;
        this.speed = speed;
        this.electric_current = electric_current;
        this.voltage = voltage;
        this.bearing = bearing;
    }

    public Motor() {
    }
}
