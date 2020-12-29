package com.lifuyi.dev_monitor.model.dev.Req;

import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Api(value = "设备分页查询实体")
public class BaseDevEntityReq {

    @ApiModelProperty(value = "查询实体" ,position = 1,required = true)
    private BaseDevEntity baseDevEntity;
    @ApiModelProperty(value = "页码" ,position = 2,required = true)
    private Integer pageNum;
    @ApiModelProperty(value = "每页数量" ,position = 3,required = true)
    private Integer pageSize;

    public BaseDevEntityReq(BaseDevEntity baseDevEntity, Integer pageNum, Integer pageSize) {
        this.baseDevEntity = baseDevEntity;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public BaseDevEntityReq() {
    }
}
