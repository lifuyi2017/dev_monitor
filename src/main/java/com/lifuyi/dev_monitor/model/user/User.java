package com.lifuyi.dev_monitor.model.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@Api(value = "用户实体类")
public class User {
    @ApiModelProperty(value = "id,支持此字段查询" ,position = 1)
    private String id;
    @ApiModelProperty(value = "用户名称" ,position = 2)
    private String user_name;
    @ApiModelProperty(value = "密码" ,position = 3)
    private String user_password;
    @ApiModelProperty(value = "姓名" ,position = 4)
    private String user_real_name;
    @ApiModelProperty(value = "电话" ,position = 5)
    private String user_phone;
    @ApiModelProperty(value = "状态" ,position = 6)
    private String user_status;
    @ApiModelProperty(value = "企业id" ,position = 7)
    private String enterprise_id;
    @ApiModelProperty(value = "注册时间" ,position = 8)
    private Date register_time;
    @ApiModelProperty(value = "更新时间" ,position = 9)
    private Date update_time;
    @ApiModelProperty(value = "角色id" ,position = 10)
    private String role_id;

    public User() {
    }

    public User(String id, String user_name, String user_password, String user_real_name, String user_phone, String user_status, String enterprise_id, Date register_time, Date update_time, String role_id) {
        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_real_name = user_real_name;
        this.user_phone = user_phone;
        this.user_status = user_status;
        this.enterprise_id = enterprise_id;
        this.register_time = register_time;
        this.update_time = update_time;
        this.role_id = role_id;
    }
}
