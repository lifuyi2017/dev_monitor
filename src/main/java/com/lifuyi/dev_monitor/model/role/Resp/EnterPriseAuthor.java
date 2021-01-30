package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Api(value = "角色的企业权限实体")
public class EnterPriseAuthor {

    private List<FarmAuthor> farmAuthorList;
    private String enterprise_id;
    private String enterprise_name;
    @ApiModelProperty(value = "下层权限,1是有，0是无" ,position = 1)
    private String flag;

    public EnterPriseAuthor(List<FarmAuthor> farmAuthorList, String enterprise_id, String enterprise_name, String flag) {
        this.farmAuthorList = farmAuthorList;
        this.enterprise_id = enterprise_id;
        this.enterprise_name = enterprise_name;
        this.flag = flag;
    }

    public EnterPriseAuthor() {
    }
}
