package com.lifuyi.dev_monitor.model.mongo.statics;

import io.swagger.annotations.Api;
import lombok.Data;

import java.util.List;
@Data
@Api(value = "厂房,带健康状态")
public class FarmAuthorAndState {

    private List<ShopAuthorAndStatus> shopAuthors;
    private String id;
    private String name;
    private String type;
    private Integer normalNum;
    private Integer warnNum;
    private Integer errorNum;

    public FarmAuthorAndState(List<ShopAuthorAndStatus> shopAuthors, String farm_id, String farm_name, Integer normalNum, Integer warnNum, Integer errorNum) {
        this.shopAuthors = shopAuthors;
        this.farm_id = farm_id;
        this.farm_name = farm_name;
        this.normalNum = normalNum;
        this.warnNum = warnNum;
        this.errorNum = errorNum;
    }

    public FarmAuthorAndState() {
    }
}
