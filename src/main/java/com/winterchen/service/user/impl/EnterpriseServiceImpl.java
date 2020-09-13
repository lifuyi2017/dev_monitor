package com.winterchen.service.user.impl;


import com.winterchen.dao.EnterpriseMapper;
import com.winterchen.model.Enterprise;
import com.winterchen.service.user.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "enterpriseServiceImpl")
public class EnterpriseServiceImpl  implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;


    @Override
    public List<Enterprise> getEnterByEntity(Enterprise enterprise) throws Exception{
        return  enterpriseMapper.getEnterByEntity(enterprise);
    }

    @Override
    public void updateById(Enterprise enterprise) throws Exception{
        enterpriseMapper.updateById(enterprise);
    }

    @Override
    public void add(Enterprise enterprise) throws Exception{
        enterpriseMapper.add(enterprise);
    }

    @Override
    public void deleteById(String id) throws Exception{
        enterpriseMapper.deleteById(id);
    }
}
