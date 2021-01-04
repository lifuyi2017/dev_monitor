package com.lifuyi.dev_monitor.model.collect.resp;


import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@Api(value = "企业下面的设备组")
public class ShopDevGroup {
    @ApiModelProperty(value = "设备或者设备组的id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "设备或者设备组的名称" ,position = 2,required = true)
    private String name;
    @ApiModelProperty(value = "车间id" ,position = 3,required = true)
    private String shop_id;
    @ApiModelProperty(value = "设备组下面的设备" ,position = 4,required = true)
    private List<WorkShopDev> workShopDevList;

    public ShopDevGroup() {
    }

    public ShopDevGroup(String id, String name, String shop_id, List<WorkShopDev> workShopDevList) {
        this.id = id;
        this.name = name;
        this.shop_id = shop_id;
        this.workShopDevList = workShopDevList;
    }
}
