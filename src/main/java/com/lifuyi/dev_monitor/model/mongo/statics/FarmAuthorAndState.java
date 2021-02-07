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

    public FarmAuthorAndState(List<ShopAuthorAndStatus> shopAuthors, String id, String name, String type, Integer normalNum, Integer warnNum, Integer errorNum) {
        this.shopAuthors = shopAuthors;
        this.id = id;
        this.name = name;
        this.type = type;
        this.normalNum = normalNum;
        this.warnNum = warnNum;
        this.errorNum = errorNum;
    }

    public FarmAuthorAndState() {
    }
}
