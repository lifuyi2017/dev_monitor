package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.Enterprise;
import com.winterchen.model.EnterpriseRequest;

import java.util.List;

public interface EnterpriseService {
    PageInfo<Enterprise> getEnterByEntity(EnterpriseRequest enterpriseRequest) throws Exception;

    void updateById(Enterprise enterprise) throws Exception;

    void add(Enterprise enterprise) throws Exception;

    void deleteById(String id) throws Exception;

    List<Enterprise> getEnterByEntityNoPage(Enterprise queryEnter);
}
