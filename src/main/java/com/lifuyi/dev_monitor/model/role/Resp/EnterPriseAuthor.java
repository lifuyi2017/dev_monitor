package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Api(value = "角色的企业权限实体")
public class EnterPriseAuthor {

    private List<FarmAuthor> children;
    private String id;
    private String label;
    @ApiModelProperty(value = "权限，1有、0无" ,position = 1)
    private String flag;

    private String type;

    public EnterPriseAuthor(List<FarmAuthor> children, String id, String label, String flag, String type) {
        this.children = children;
        this.id = id;
        this.label = label;
        this.flag = flag;
        this.type = type;
    }

    public EnterPriseAuthor() {
    }
}
