package com.lifuyi.dev_monitor.model.collect.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "厂房车间查询实体")
public class WorkShopQueryReq {

    @ApiModelProperty(value = "企业id" ,position = 1,required = true)
    private String enterprise_id;
    @ApiModelProperty(value = "父id，厂房的父id是企业，车间的父id是厂房" ,position = 2,required = true)
    private String parent_id;
    @ApiModelProperty(value = "类型，1是厂房，2是车间" ,position = 3,required = true)
    private String type;

    public WorkShopQueryReq(String enterprise_id, String parent_id, String type) {
        this.enterprise_id = enterprise_id;
        this.parent_id = parent_id;
        this.type = type;
    }

    public WorkShopQueryReq() {
    }
}
