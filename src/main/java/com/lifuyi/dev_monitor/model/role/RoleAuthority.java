package com.lifuyi.dev_monitor.model.role;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "角色权限实体")
public class RoleAuthority {

    @ApiModelProperty(value = "id,支持此字段查询" ,position = 1)
    private String id;
    @ApiModelProperty(value = "角色id" ,position = 2,required = true)
    private String role_id;
    @ApiModelProperty(value = "厂房id" ,position = 3,required = true)
    private String farm_id;
    @ApiModelProperty(value = "车间id" ,position = 4)
    private String shop_id;
    @ApiModelProperty(value = "企业id" ,position = 5)
    private String enterprise_id;

    public RoleAuthority() {
    }

    public RoleAuthority(String id, String role_id, String farm_id, String shop_id, String enterprise_id) {
        this.id = id;
        this.role_id = role_id;
        this.farm_id = farm_id;
        this.shop_id = shop_id;
        this.enterprise_id = enterprise_id;
    }
}
