package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
@Api(value = "角色的厂房权限实体")
public class FarmAuthor {

    @ApiModelProperty(value = "下层权限,1是有，0是无" ,position = 1)
    private Map<ShopAuthor,String> map;
    private String farm_id;
    private String farm_name;

    public FarmAuthor(Map<ShopAuthor, String> map, String farm_id, String farm_name) {
        this.map = map;
        this.farm_id = farm_id;
        this.farm_name = farm_name;
    }

    public FarmAuthor() {
    }
}
