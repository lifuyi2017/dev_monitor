package com.lifuyi.dev_monitor.model.collect.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "开始停止采集")
public class StartOrStopCollect {

    @ApiModelProperty(value = "id" ,position = 1,required = true)
    private String id;
    @ApiModelProperty(value = "状态：0是停止采集状态、1是采集状态" ,position = 2,required = true)
    private String state;

    public StartOrStopCollect() {
    }

    public StartOrStopCollect(String id, String state) {
        this.id = id;
        this.state = state;
    }
}
