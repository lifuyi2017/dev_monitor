package com.lifuyi.dev_monitor.model.enterprise.Resp;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EnterpriseResp {
    private String enterprise_id;
    private String enterprise_code;
    private String enterprise_name;
    private EnterpriseTypeResp enterpriseTypeResp;
    private Date update_time;
    private List<String> service_names;
}
