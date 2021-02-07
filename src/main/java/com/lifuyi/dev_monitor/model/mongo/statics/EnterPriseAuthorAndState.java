package com.lifuyi.dev_monitor.model.mongo.statics;

import com.lifuyi.dev_monitor.model.role.Resp.FarmAuthor;
import com.lifuyi.dev_monitor.model.role.Resp.ShopAuthor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@Api(value = "企业,带健康状态")
public class EnterPriseAuthorAndState {


    private List<FarmAuthorAndState> farmAuthorList;
    private String id;
    private String name;
    private String type;
    private Integer normalNum;
    private Integer warnNum;
    private Integer errorNum;

    public EnterPriseAuthorAndState(List<FarmAuthorAndState> farmAuthorList, String id, String name, String type, Integer normalNum, Integer warnNum, Integer errorNum) {
        this.farmAuthorList = farmAuthorList;
        this.id = id;
        this.name = name;
        this.type = type;
        this.normalNum = normalNum;
        this.warnNum = warnNum;
        this.errorNum = errorNum;
    }

    public EnterPriseAuthorAndState() {
    }
}
