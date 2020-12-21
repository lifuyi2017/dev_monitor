package com.lifuyi.dev_monitor.model.network.req;

import com.lifuyi.dev_monitor.model.network.Network;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "网关查询结果实体")
public class NetworkReq {

    @ApiModelProperty(value = "查询条件实体" ,position = 1)
    private Network network;
    private Integer pageNum;
    private Integer pageSize;

    public NetworkReq() {
    }

    public NetworkReq(Network network, Integer pageNum, Integer pageSize) {
        this.network = network;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
