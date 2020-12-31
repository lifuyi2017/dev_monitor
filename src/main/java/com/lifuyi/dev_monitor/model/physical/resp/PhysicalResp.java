package com.lifuyi.dev_monitor.model.physical.resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "物理节点查询结果实体")
public class PhysicalResp {

    @ApiModelProperty(value = "物理节点id" ,position = 1)
    private String id;
    @ApiModelProperty(value = "物理节点类型" ,position = 2)
    private String type;
    @ApiModelProperty(value = "物理节点编号" ,position = 3)
    private String code;
    @ApiModelProperty(value = "通道数" ,position = 4)
    private Integer num;
    @ApiModelProperty(value = "所属网关节点" ,position = 5)
    private String network_name;
    @ApiModelProperty(value = "所属网关节点id" ,position = 8)
    private String network_id;
    @ApiModelProperty(value = "名称" ,position = 6)
    private String name;
    @ApiModelProperty(value = "所属企业名称" ,position = 7)
    private String enterprise_name;
    @ApiModelProperty(value = "所属企业id" ,position = 9)
    private String enterprise_id;

    public PhysicalResp() {
    }

    public PhysicalResp(String id, String type, String code, Integer num, String network_name, String network_id, String name, String enterprise_name, String enterprise_id) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.num = num;
        this.network_name = network_name;
        this.network_id = network_id;
        this.name = name;
        this.enterprise_name = enterprise_name;
        this.enterprise_id = enterprise_id;
    }
}
