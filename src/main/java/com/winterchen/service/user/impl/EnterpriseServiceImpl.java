package com.winterchen.service.user.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.EnterpriseMapper;
import com.winterchen.model.Enterprise;
import com.winterchen.model.EnterpriseRequest;
import com.winterchen.service.user.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "enterpriseService")
public class EnterpriseServiceImpl  implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;


    @Override
    public List<Enterprise> getEnterByEntity(Enterprise enterprise) throws Exception{

        List<Enterprise> enterByEntity = enterpriseMapper.getEnterByEntity(enterprise);

        return  enterByEntity;
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

    @Override
    public List<Enterprise> getEnterByEntityNoPage(Enterprise queryEnter) {
        return enterpriseMapper.getEnterByEntity(queryEnter);
    }
}
