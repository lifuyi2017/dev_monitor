package com.lifuyi.dev_monitor.model.dev.Resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseDevPagesRsp {

    @ApiModelProperty(value = "id,与object的id一致，可以不传，不传表示新增，传表示修改" ,position = 1)
    private String id;
    @ApiModelProperty(value = "编号" ,position = 2)
    private String dev_code;
    @ApiModelProperty(value = "类型名称" ,position = 3)
    private String dev_type_name;
    @ApiModelProperty(value = "设备名称" ,position = 4)
    private String dev_name;
    @ApiModelProperty(value = "负责人" ,position = 5)
    private String manager;
    @ApiModelProperty(value = "经销商名称" ,position = 6)
    private String service_enterprise_name;
    @ApiModelProperty(value = "生产商名称" ,position = 7)
    private String produce_enterprise_name;
    @ApiModelProperty(value = "第三方商名称" ,position = 8)
    private String third_enterprise_name;
    @ApiModelProperty(value = "安装说明" ,position = 9)
    private String install_instructions;
    @ApiModelProperty(value = "图片id" ,position = 10)
    private String pic_url;
    @ApiModelProperty(value = "设备使用商公司名称" ,position = 11)
    private String enterprise_name;
    @ApiModelProperty(value = "根据类型变动的字段组，这版本只有电机，风机，水泵，如果有新增，叫向哥规划到下个版本" ,position = 12)
    private Object object;

    public BaseDevPagesRsp(String id, String dev_code, String dev_type_name, String dev_name, String manager, String service_enterprise_name, String produce_enterprise_name, String third_enterprise_name, String install_instructions, String pic_url, String enterprise_name, Object object) {
        this.id = id;
        this.dev_code = dev_code;
        this.dev_type_name = dev_type_name;
        this.dev_name = dev_name;
        this.manager = manager;
        this.service_enterprise_name = service_enterprise_name;
        this.produce_enterprise_name = produce_enterprise_name;
        this.third_enterprise_name = third_enterprise_name;
        this.install_instructions = install_instructions;
        this.pic_url = pic_url;
        this.enterprise_name = enterprise_name;
        this.object = object;
    }

    public BaseDevPagesRsp() {
    }
}
