package com.lifuyi.dev_monitor.model.mongo.statics;

import com.lifuyi.dev_monitor.model.mongo.current_data.DevicePredicData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DevHeath {

    private DevicePredicData devicePredicData;
    @ApiModelProperty(value = "类型，3是车间下面的设备，4是车间下面的设备组" ,position = 2,required = true)
    private String type;

    public DevHeath(DevicePredicData devicePredicData, String type) {
        this.devicePredicData = devicePredicData;
        this.type = type;
    }

    public DevHeath() {
    }
}
