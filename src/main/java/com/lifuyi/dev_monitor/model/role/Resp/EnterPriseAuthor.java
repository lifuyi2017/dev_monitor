package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
@Api(value = "角色的企业权限实体")
public class EnterPriseAuthor {
    @ApiModelProperty(value = "下层权限,1是有，0是无" ,position = 1)
    private Map<FarmAuthor,String> map;
    private String enterprise_id;
    private String enterprise_name;

    public EnterPriseAuthor(Map<FarmAuthor, String> map, String enterprise_id, String enterprise_name) {
        this.map = map;
        this.enterprise_id = enterprise_id;
        this.enterprise_name = enterprise_name;
    }

    public EnterPriseAuthor() {
    }
}
