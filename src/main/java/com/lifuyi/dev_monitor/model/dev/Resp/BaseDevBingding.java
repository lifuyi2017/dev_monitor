package com.lifuyi.dev_monitor.model.dev.Resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseDevBingding {
    @ApiModelProperty(value = "id,与object的id一致，可以不传，不传表示新增，传表示修改,支持此字段查询" ,position = 1)
    private String id;
    @ApiModelProperty(value = "编号，支持此字段查询" ,position = 2)
    private String dev_code;
    @ApiModelProperty(value = "类型id，支持此字段查询" ,position = 3)
    private Integer dev_type_id;
    @ApiModelProperty(value = "设备名称，支持此字段模糊查询" ,position = 4)
    private String dev_name;
    @ApiModelProperty(value = "负责人" ,position = 5)
    private String manager;
    @ApiModelProperty(value = "经销商id" ,position = 6)
    private String service_enterprise_id;
    @ApiModelProperty(value = "生产商id" ,position = 7)
    private String produce_enterprise_id;
    @ApiModelProperty(value = "第三方商id" ,position = 8)
    private String third_enterprise_id;
    @ApiModelProperty(value = "安装说明" ,position = 9)
    private String install_instructions;
    @ApiModelProperty(value = "图片id" ,position = 10)
    private String pic_url;
    @ApiModelProperty(value = "设备使用商公司id，支持此字段查询" ,position = 11)
    private String enterprise_id;
    @ApiModelProperty(value = "是否可以用于绑定，0不能，1能" ,position = 11)
    private String disabled;

    public BaseDevBingding() {
    }

    public BaseDevBingding(String id, String dev_code, Integer dev_type_id, String dev_name, String manager, String service_enterprise_id, String produce_enterprise_id, String third_enterprise_id, String install_instructions, String pic_url, String enterprise_id, String disabled) {
        this.id = id;
        this.dev_code = dev_code;
        this.dev_type_id = dev_type_id;
        this.dev_name = dev_name;
        this.manager = manager;
        this.service_enterprise_id = service_enterprise_id;
        this.produce_enterprise_id = produce_enterprise_id;
        this.third_enterprise_id = third_enterprise_id;
        this.install_instructions = install_instructions;
        this.pic_url = pic_url;
        this.enterprise_id = enterprise_id;
        this.disabled = disabled;
    }
}
