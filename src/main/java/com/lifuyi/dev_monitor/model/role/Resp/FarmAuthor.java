package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Api(value = "角色的厂房权限实体")
public class FarmAuthor {


    private List<ShopAuthor> children;
    private String id;
    private String label;
    private String flag;
    private String type;

    public FarmAuthor(List<ShopAuthor> children, String id, String label, String flag, String type) {
        this.children = children;
        this.id = id;
        this.label = label;
        this.flag = flag;
        this.type = type;
    }

    public FarmAuthor() {
    }
}
