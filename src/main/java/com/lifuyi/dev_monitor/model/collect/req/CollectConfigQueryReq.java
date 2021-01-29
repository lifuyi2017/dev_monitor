package com.lifuyi.dev_monitor.model.collect.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "采集配置查询实体")
public class CollectConfigQueryReq {

    @ApiModelProperty(value = "id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "类型，1是厂房，2是车间,4是设备组" ,position = 2,required = true)
    private String type;
    @ApiModelProperty(value = "企业id" ,position = 3,required = true)
    private String enterprise_id;


    public CollectConfigQueryReq() {
    }

    public CollectConfigQueryReq(String id, String type, String enterprise_id) {
        this.id = id;
        this.type = type;
        this.enterprise_id = enterprise_id;
    }
}
