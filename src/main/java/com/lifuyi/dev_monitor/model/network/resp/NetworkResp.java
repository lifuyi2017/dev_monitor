package com.lifuyi.dev_monitor.model.network.resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "网关查询结果实体")
public class NetworkResp {
    @ApiModelProperty(value = "网关id" ,position = 1)
    private String network_id;
    @ApiModelProperty(value = "网关类型" ,position = 8)
    private String network_type;
    @ApiModelProperty(value = "网关编号" ,position = 2)
    private String network_code;
    @ApiModelProperty(value = "网关名称" ,position = 3)
    private String network_name;
    @ApiModelProperty(value = "网关ip" ,position = 4)
    private String network_ip;
    @ApiModelProperty(value = "网关地址" ,position = 5)
    private String input_address;
    @ApiModelProperty(value = "输出协议" ,position = 6)
    private String output_agreement;
    @ApiModelProperty(value = "所属企业" ,position = 7)
    private String enterprise_name;
    @ApiModelProperty(value = "所属企业编号" ,position = 8)
    private String enterprise_id;

    public NetworkResp() {
    }

    public NetworkResp(String network_id, String network_type, String network_code, String network_name, String network_ip, String input_address, String output_agreement, String enterprise_name, String enterprise_id) {
        this.network_id = network_id;
        this.network_type = network_type;
        this.network_code = network_code;
        this.network_name = network_name;
        this.network_ip = network_ip;
        this.input_address = input_address;
        this.output_agreement = output_agreement;
        this.enterprise_name = enterprise_name;
        this.enterprise_id = enterprise_id;
    }
}
