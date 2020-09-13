package com.winterchen.service.user;

import com.winterchen.model.Enterprise;

import java.util.List;

public interface EnterpriseService {
    List<Enterprise> getEnterByEntity(Enterprise enterprise) throws Exception;

    void updateById(Enterprise enterprise) throws Exception;

    void add(Enterprise enterprise) throws Exception;

    void deleteById(String id) throws Exception;
}
