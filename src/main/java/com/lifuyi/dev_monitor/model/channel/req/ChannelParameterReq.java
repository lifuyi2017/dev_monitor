package com.lifuyi.dev_monitor.model.channel.req;

import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "分页查询请求实体")
public class ChannelParameterReq {
    @ApiModelProperty(value = "查询实体" ,position = 1)
    private ChannelParameter parameter;
    @ApiModelProperty(value = "页码" ,position = 2)
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量" ,position = 3)
    private Integer pageSize;

    public ChannelParameterReq() {
    }

    public ChannelParameterReq(ChannelParameter parameter, Integer pageNum, Integer pageSize) {
        this.parameter = parameter;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
