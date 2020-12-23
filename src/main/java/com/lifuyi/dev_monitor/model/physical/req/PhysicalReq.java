package com.lifuyi.dev_monitor.model.physical.req;

import com.lifuyi.dev_monitor.model.physical.Physical;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "物理节点查询实体")
public class PhysicalReq {

    @ApiModelProperty(value = "查询条件实体" ,position = 1)
    private Physical physical;
    private Integer pageNum;
    private Integer pageSize;

    public PhysicalReq() {
    }

    public PhysicalReq(Physical physical, Integer pageNum, Integer pageSize) {
        this.physical = physical;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
