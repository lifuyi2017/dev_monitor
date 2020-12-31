package com.lifuyi.dev_monitor.model.dev.Resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseDevPagesRsp {

    @ApiModelProperty(value = "id,与object的id一致，可以不传，不传表示新增，传表示修改" ,position = 1)
    private String id;
    @ApiModelProperty(value = "编号" ,position = 2)
    private String dev_code;
    @ApiModelProperty(value = "类型id" ,position = 13)
    private String dev_type_id;
    @ApiModelProperty(value = "类型名称" ,position = 3)
    private String dev_type_name;
    @ApiModelProperty(value = "设备名称" ,position = 4)
    private String dev_name;
    @ApiModelProperty(value = "负责人" ,position = 5)
    private String manager;
    @ApiModelProperty(value = "经销商id" ,position = 14)
    private String service_enterprise_id;
    @ApiModelProperty(value = "生产商id" ,position = 15)
    private String produce_enterprise_id;
    @ApiModelProperty(value = "第三方商id" ,position = 16)
    private String third_enterprise_id;
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
    @ApiModelProperty(value = "设备使用商公司id" ,position = 17)
    private String enterprise_id;
    @ApiModelProperty(value = "设备使用商公司名称" ,position = 11)
    private String enterprise_name;
    @ApiModelProperty(value = "根据类型变动的字段组，这版本只有电机，风机，水泵，如果有新增，叫向哥规划到下个版本" ,position = 12)
    private Object object;


    public BaseDevPagesRsp() {
    }
}
