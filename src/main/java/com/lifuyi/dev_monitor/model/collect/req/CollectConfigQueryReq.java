package com.lifuyi.dev_monitor.model.collect.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "采集配置查询实体")
public class CollectConfigQueryReq {

    @ApiModelProperty(value = "id" ,position = 1)
    private String id;
//    @ApiModelProperty(value = "类型，1是厂房，2是车间" ,position = 2,required = true)
//    private String type;

    public CollectConfigQueryReq() {
    }

    public CollectConfigQueryReq(String id) {
        this.id = id;
    }
}
