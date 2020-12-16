package com.lifuyi.dev_monitor.model.dev;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DevType {
    @ApiModelProperty(value = "类型id" ,position = 1)
    private Integer id;
    @ApiModelProperty(value = "类型名称" ,position = 2)
    private String type_name;

    public DevType(Integer id, String type_name) {
        this.id = id;
        this.type_name = type_name;
    }

    public DevType() {
    }
}
