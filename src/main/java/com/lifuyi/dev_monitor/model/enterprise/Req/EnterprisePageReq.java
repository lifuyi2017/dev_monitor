package com.lifuyi.dev_monitor.model.enterprise.Req;

import lombok.Data;

@Data
public class EnterprisePageReq {
    private String enterprise_name;


    private Integer pageNum;
    private Integer pageSize;

}
