package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(value = "角色的车间权限实体")
public class ShopAuthor {

    private String id;
    private String label;
    private String flag;
    private String type;

    public ShopAuthor(String id, String label, String flag, String type) {
        this.id = id;
        this.label = label;
        this.flag = flag;
        this.type = type;
    }

    public ShopAuthor() {
    }
}
