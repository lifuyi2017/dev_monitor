package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Api(value = "角色的厂房权限实体")
public class FarmAuthor {


    private List<ShopAuthor> shopAuthors;
    private String farm_id;
    private String farm_name;
    @ApiModelProperty(value = "下层权限,1是有，0是无" ,position = 1)
    private String flag;

    public FarmAuthor(List<ShopAuthor> shopAuthors, String farm_id, String farm_name, String flag) {
        this.shopAuthors = shopAuthors;
        this.farm_id = farm_id;
        this.farm_name = farm_name;
        this.flag = flag;
    }

    public FarmAuthor() {
    }
}
