package com.lifuyi.dev_monitor.model.dev;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 风机
 */
@Data
@Api(value = "风机实体")
public class Fan {

    @ApiModelProperty(value = "id,与基本实体的id一致" ,position = 1)
    private String id;

    @ApiModelProperty(value = "功率" ,position = 2)
    private String power;
    @ApiModelProperty(value = "转速" ,position = 3)
    private String speed;
    @ApiModelProperty(value = "流量" ,position = 4)
    private String flow;
    @ApiModelProperty(value = "风压" ,position = 5)
    private String wind_pressure;
    @ApiModelProperty(value = "轴承类型" ,position = 6)
    private String bearing;

    public Fan(String id, String power, String speed, String flow, String wind_pressure, String bearing) {
        this.id = id;
        this.power = power;
        this.speed = speed;
        this.flow = flow;
        this.wind_pressure = wind_pressure;
        this.bearing = bearing;
    }

    public Fan() {
    }
}
