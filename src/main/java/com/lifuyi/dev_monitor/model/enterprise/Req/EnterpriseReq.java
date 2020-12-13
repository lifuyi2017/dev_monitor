package com.lifuyi.dev_monitor.model.enterprise.Req;

import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;
import lombok.*;

import java.util.List;

@Data
public class EnterpriseReq {
    private String enterprise_id;
    private String enterprise_code;
    private String enterprise_name;
    private EnterpriseTypeResp enterpriseTypeResp;
    private List<String> binging_id;
}