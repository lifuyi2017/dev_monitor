package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;

public interface EnterpriseService {
    ResultMessage<Boolean> addOrUpdate(EnterpriseReq enterpriseReq);
}
