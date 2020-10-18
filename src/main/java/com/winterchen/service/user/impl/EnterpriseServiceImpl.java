package com.winterchen.service.user.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.*;
import com.winterchen.model.Channel;
import com.winterchen.model.Enterprise;
import com.winterchen.model.EnterpriseRequest;
import com.winterchen.service.user.DevService;
import com.winterchen.service.user.EnterpriseService;
import com.winterchen.service.user.LogicService;
import com.winterchen.service.user.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "enterpriseService")
public class EnterpriseServiceImpl  implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private MeasureMapper measureMapper;
    @Autowired
    private LogicService logicService;
    @Autowired
    private DevService devService;
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private DevFixedFieldValueMapper devFixedFieldValueMapper;
    @Autowired
    private UserDao userDao;



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
        networkService.deleteByEnterpriseId(id);
        measureMapper.deleteByEnterpriseId(id);

        logicService.deleteByEnterpriseId(id);
        devService.deleteByEnterpriseId(id);

        channelMapper.deleteByEnterpriseId(id);

        devFixedFieldValueMapper.deleteByEnterpriseId(id);

        userDao.deleteByEnterpriseId(id);
    }

    @Override
    public List<Enterprise> getEnterByEntityNoPage(Enterprise queryEnter) {
        return enterpriseMapper.getEnterByEntity(queryEnter);
    }
}
