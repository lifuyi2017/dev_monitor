package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@Api(value = "角色查询结果实体")
public class RoleResp {

    @ApiModelProperty(value = "id,支持此字段查询" ,position = 1)
    private String id;
    @ApiModelProperty(value = "角色名称" ,position = 2)
    private String role_name;
    @ApiModelProperty(value = "状态，1启用，0停用" ,position = 3)
    private String status;
    @ApiModelProperty(value = "创建时间" ,position = 4)
    private Date create_time;
    @ApiModelProperty(value = "更新时间" ,position = 5)
    private Date update_time;
    @ApiModelProperty(value = "企业id" ,position = 6)
    private String  enterprise_name;

    public RoleResp() {
    }

    public RoleResp(String id, String role_name, String status, Date create_time, Date update_time, String enterprise_name) {
        this.id = id;
        this.role_name = role_name;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
        this.enterprise_name = enterprise_name;
    }
}
