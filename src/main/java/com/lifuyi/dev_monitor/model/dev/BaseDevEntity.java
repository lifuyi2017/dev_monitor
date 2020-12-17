package com.lifuyi.dev_monitor.model.dev;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "设备基本实体")
public class BaseDevEntity {


    private String id;

    @ApiModelProperty(value = "编号" ,position = 1)
    private String dev_code;
    @ApiModelProperty(value = "类型id" ,position = 2)
    private Integer dev_type_id;
    @ApiModelProperty(value = "设备名称" ,position = 3)
    private String dev_name;
    @ApiModelProperty(value = "负责人" ,position = 4)
    private String manager;
    @ApiModelProperty(value = "经销商id" ,position = 5)
    private String service_enterprise_id;
    @ApiModelProperty(value = "生产商id" ,position = 6)
    private String produce_enterprise_id;
    @ApiModelProperty(value = "第三方商id" ,position = 7)
    private String third_enterprise_id;
    @ApiModelProperty(value = "安装说明" ,position = 8)
    private String install_instructions;
    @ApiModelProperty(value = "图片id" ,position = 9)
    private String pic_url;

    @ApiModelProperty(value = "根据类型变动的字段组，这版本只有电机，风机，水泵，如果有新增，叫向哥规划到下个版本" ,position = 10)
    private Object object;

    public BaseDevEntity(String id, String dev_code, Integer dev_type_id, String dev_name, String manager, String service_enterprise_id, String produce_enterprise_id, String third_enterprise_id, String install_instructions, String pic_url, Object object) {
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
        this.object = object;
    }

    public BaseDevEntity() {
    }
}
