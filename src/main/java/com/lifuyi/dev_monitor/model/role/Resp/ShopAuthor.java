package com.lifuyi.dev_monitor.model.role.Resp;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(value = "角色的车间权限实体")
public class ShopAuthor {

    private String shop_id;
    private String shop_name;
    private String flag;

    public ShopAuthor(String shop_id, String shop_name, String flag) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.flag = flag;
    }

    public ShopAuthor() {
    }
}
