package com.lifuyi.dev_monitor.model.collect.resp;

import io.swagger.annotations.ApiModelProperty;

public class WorkShopDevWithType {

    @ApiModelProperty(value = "设备或者设备组的id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "设备或者设备组的名称" ,position = 2,required = true)
    private String name;
    @ApiModelProperty(value = "车间id" ,position = 3,required = true)
    private String shop_id;
    @ApiModelProperty(value = "父id,车间id或者设备组id" ,position = 4,required = true)
    private String parent_id;
    @ApiModelProperty(value = "类型，3是车间下面的设备，4是车间下面的设备组，5是设备组下面的设备" ,position = 5,required = true)
    private String type;
    @ApiModelProperty(value = "绑定的dev的id，如果是设备组，可不填" ,position = 6)
    private Integer dev_id;
    @ApiModelProperty(value = "绑定的dev的类型id，如果是设备组，可不填" ,position = 7)
    private String dev_type;
    @ApiModelProperty(value = "企业id" ,position = 8,required = true)
    private String enterprise_id;

    public WorkShopDevWithType() {
    }

    public WorkShopDevWithType(String id, String name, String shop_id, String parent_id, String type, Integer dev_id, String dev_type, String enterprise_id) {
        this.id = id;
        this.name = name;
        this.shop_id = shop_id;
        this.parent_id = parent_id;
        this.type = type;
        this.dev_id = dev_id;
        this.dev_type = dev_type;
        this.enterprise_id = enterprise_id;
    }
}
