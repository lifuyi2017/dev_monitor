package com.winterchen.service.user;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.Enterprise;
import com.winterchen.model.EnterpriseRequest;
import com.winterchen.model.ResultMessage;

import java.util.List;

public interface EnterpriseService {
    List<Enterprise> getEnterByEntity(Enterprise enterpriseRequest) throws Exception;

    void updateById(Enterprise enterprise) throws Exception;

    void add(Enterprise enterprise) throws Exception;

    ResultMessage<Boolean> deleteById(String id) throws Exception;

    List<Enterprise> getEnterByEntityNoPage(Enterprise queryEnter);
}
