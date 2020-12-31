package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.EnterpriseBinging;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;

import java.util.List;

public interface EnterpriseMapper {
    List<EnterpriseTypeResp> getType();

    List<Enterprise> getListByEntity(Enterprise queryEnter);

    void insertOrUpdateById(Enterprise enterprise);

    void insertOrUpdateBinging(EnterpriseBinging enterpriseBinging);

    List<Enterprise> getServiceNames(String enterprise_id);

    EnterpriseTypeResp getTypeById(Integer enterprise_type_id);

    Integer verificationName(String id, String name);

    Integer verificationCode(String id, String code);

    void deleteBingingByNotServiceId(String id);

    List<Enterprise> getEnterpriseByTypeIds(List<String> ids);
}
