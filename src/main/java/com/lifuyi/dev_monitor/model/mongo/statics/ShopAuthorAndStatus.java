package com.lifuyi.dev_monitor.model.mongo.statics;

import com.lifuyi.dev_monitor.model.mongo.current_data.DevicePredicData;
import io.swagger.annotations.Api;
import lombok.Data;

import java.util.List;

@Data
@Api(value = "车间,带健康状态")
public class ShopAuthorAndStatus {

    private String id;
    private String name;
    private String type;
    private Integer normalNum;
    private Integer warnNum;
    private Integer errorNum;
    private List<DevHeath> devHeathList;

    public ShopAuthorAndStatus(String id, String name, String type, Integer normalNum, Integer warnNum, Integer errorNum, List<DevHeath> devHeathList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.normalNum = normalNum;
        this.warnNum = warnNum;
        this.errorNum = errorNum;
        this.devHeathList = devHeathList;
    }

    public ShopAuthorAndStatus() {
    }
}
