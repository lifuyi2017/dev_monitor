package com.lifuyi.dev_monitor.model.collect;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "厂房实体类")
public class WorkShop {

    @ApiModelProperty(value = "id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "企业id" ,position = 2,required = true)
    private String enterprise_id;
    @ApiModelProperty(value = "类型，1是厂房，2是车间" ,position = 3,required = true)
    private String type;
    @ApiModelProperty(value = "名称" ,position = 4,required = true)
    private String name;
    @ApiModelProperty(value = "父id，厂房的父id是企业，车间的父id是厂房" ,position = 5,required = true)
    private String parent_id;

    public WorkShop() {
    }

    public WorkShop(String id, String enterprise_id, String type, String name, String parent_id) {
        this.id = id;
        this.enterprise_id = enterprise_id;
        this.type = type;
        this.name = name;
        this.parent_id = parent_id;
    }
}
