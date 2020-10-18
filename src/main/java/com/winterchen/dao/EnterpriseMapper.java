package com.winterchen.dao;

import com.winterchen.model.Enterprise;
import com.winterchen.service.user.EnterpriseService;

import java.util.List;

public interface EnterpriseMapper{
    List<Enterprise> getEnterByEntity(Enterprise enterprise);

    void add(Enterprise enterprise);

    void updateById(Enterprise enterprise);

    void deleteById(String id);

    String getNameById(String enterprise_id);
}
