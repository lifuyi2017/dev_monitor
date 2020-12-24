package com.lifuyi.dev_monitor.model.physical;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "物理节点通道对应实体")
public class PhysicalChannelBinding {

    @ApiModelProperty(value = "关联id，'physical_id+\"=\"+channel_code'" ,position = 1)
    private String id;
    @ApiModelProperty(value = "物理节点id" ,position = 1)
    private String physical_id;
    @ApiModelProperty(value = "通道编码" ,position = 1)
    private String channel_code;
    @ApiModelProperty(value = "通道分类id，通道管理中的" ,position = 1)
    private String channel_type_id;
    @ApiModelProperty(value = "采集配置id" ,position = 1)
    private String collect_id;

    public PhysicalChannelBinding() {
    }

    public PhysicalChannelBinding(String id, String physical_id, String channel_code, String channel_type_id, String collect_id) {
        this.id = id;
        this.physical_id = physical_id;
        this.channel_code = channel_code;
        this.channel_type_id = channel_type_id;
        this.collect_id = collect_id;
    }
}
