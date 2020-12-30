package com.lifuyi.dev_monitor.model.enterprise.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Api(value = "企业查询结果实体")
public class EnterpriseResp {
    @ApiModelProperty(value = "id" ,position = 1)
    private String enterprise_id;
    @ApiModelProperty(value = "企业编码" ,position = 2)
    private String enterprise_code;
    @ApiModelProperty(value = "企业名称" ,position = 3)
    private String enterprise_name;
    @ApiModelProperty(value = "企业类型" ,position = 4)
    private EnterpriseTypeResp enterpriseTypeResp;
    @ApiModelProperty(value = "更新时间" ,position = 5)
    private Date update_time;
    @ApiModelProperty(value = "banding的企业" ,position = 5)
    private Map<String,String> service_names;
}
