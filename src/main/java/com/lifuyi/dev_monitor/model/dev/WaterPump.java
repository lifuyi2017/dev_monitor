package com.lifuyi.dev_monitor.model.dev;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "水泵实体")
public class WaterPump {
    @ApiModelProperty(value = "id,与基本实体的id一致" ,position = 1)
    private String id;
    @ApiModelProperty(value = "功率" ,position = 2)
    private String power;
    @ApiModelProperty(value = "转速" ,position = 3)
    private String speed;
    @ApiModelProperty(value = "流量" ,position = 4)
    private String flow;
    @ApiModelProperty(value = "扬程" ,position = 5)
    private String lift;
    @ApiModelProperty(value = "轴承类型" ,position = 6)
    private String bearing;

    public WaterPump(String id, String power, String speed, String flow, String lift, String bearing) {
        this.id = id;
        this.power = power;
        this.speed = speed;
        this.flow = flow;
        this.lift = lift;
        this.bearing = bearing;
    }

    public WaterPump() {
    }
}
