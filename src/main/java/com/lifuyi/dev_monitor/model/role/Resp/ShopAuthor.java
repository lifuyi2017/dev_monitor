package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(value = "角色的车间权限实体")
public class ShopAuthor {

    private String shop_id;
    private String shop_name;

    public ShopAuthor(String shop_id, String shop_name) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
    }

    public ShopAuthor() {
    }
}
