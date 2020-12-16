package com.lifuyi.dev_monitor.model.enterprise.Resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("xxxx")
public class EnterpriseTypeResp {
    @ApiModelProperty(value = "类型id" ,position = 1)
    private Integer id;
    @ApiModelProperty(value = "类型名称" ,position = 2)
    private String type_name;
}
