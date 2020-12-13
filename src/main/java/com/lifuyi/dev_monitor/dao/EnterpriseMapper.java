package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.EnterpriseBinging;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;

import java.util.List;

public interface EnterpriseMapper {
    List<EnterpriseTypeResp> getType();

    List<Enterprise> getListByEntity(Enterprise queryEnter);

    void insertOrUpdateById(Enterprise enterprise);

    void insertOrUpdateBinging(EnterpriseBinging enterpriseBinging);
}
