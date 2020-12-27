package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterprisePageReq;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseResp;

import java.util.List;

public interface EnterpriseService {
    ResultMessage<Boolean> addOrUpdate(EnterpriseReq enterpriseReq);

    ResultMessage<PageInfo<EnterpriseResp>> getEnterprisePage(EnterprisePageReq pageReq);

    ResultMessage<List<Enterprise>> getEnterprise(Enterprise enterprise);

    ResultMessage<List<Enterprise>> getEnterpriseByTypeIds(List<String> ids);
}
