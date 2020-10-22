package com.winterchen.service.user.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.*;
import com.winterchen.model.*;
import com.winterchen.service.user.DevService;
import com.winterchen.service.user.EnterpriseService;
import com.winterchen.service.user.LogicService;
import com.winterchen.service.user.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        devFixedFieldValueMapper.updateByOperateEnterpriseId(enterprise);
        devFixedFieldValueMapper.updateByServiceEnterpriseId(enterprise);
        devFixedFieldValueMapper.updateByProductionEnterpriseId(enterprise);
    }

    @Override
    public void add(Enterprise enterprise) throws Exception{
        enterpriseMapper.add(enterprise);
    }

    @Override
    @Transactional
    public ResultMessage<Boolean> deleteById(String id) throws Exception{
//        ResultMessage<Boolean> booleanResultMessage = devService.deleteByEnterpriseId(id);
        ResultMessage<Boolean> result=new ResultMessage<>();
        DevElement devElement = new DevElement();
        devElement.setEnterprise_id(id);
        List<DevElement> devElements = devService.queryByEntity(devElement);
        if(devElements!=null && devElements.size()>0){
            result.setValue(false);
            result.setStatuscode("401");
            result.setMesg("删除失败，此公司有设备未删除，请在采集管理中删除相关设备");
            return result;
        }
        User user = new User();
        user.setEnterprise_id(id);
        List<User> usersByUser = userDao.getUsersByUser(user);
        if(usersByUser!=null && usersByUser.size()>0){
            result.setValue(false);
            result.setStatuscode("401");
            result.setMesg("删除失败，此公司有用户未删除，请在用户管理中删除相关用户");
            return result;
        }
        enterpriseMapper.deleteById(id);
        networkService.updateByEnterpriseIdNull(id);
        measureMapper.updateByEnterpriseIdNull(id);
        logicService.updateByEnterpriseIdNull(id);
        channelMapper.updateByEnterpriseIdNull(id);
        devFixedFieldValueMapper.updateByOperateEnterpriseIdNull(id);
        devFixedFieldValueMapper.updateByProductionEnterpriseIdNull(id);
        devFixedFieldValueMapper.updateByServiceEnterpriseIdNull(id);
        result.setValue(true);
        result.setStatuscode("200");
        result.setMesg("删除成功");
        return result;
    }

    @Override
    public List<Enterprise> getEnterByEntityNoPage(Enterprise queryEnter) {
        return enterpriseMapper.getEnterByEntity(queryEnter);
    }
}
