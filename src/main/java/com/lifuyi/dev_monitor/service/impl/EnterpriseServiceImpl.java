package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.EnterpriseMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.EnterpriseBinging;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;
import com.lifuyi.dev_monitor.service.EnterpriseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {

    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public ResultMessage<Boolean> addOrUpdate(EnterpriseReq enterpriseReq) {
        Enterprise queryEnter = new Enterprise();
        queryEnter.setEnterprise_name(enterpriseReq.getEnterprise_name());
        List<Enterprise> listByEntity = enterpriseMapper.getListByEntity(queryEnter);
        if (listByEntity != null && listByEntity.size() > 0) {
            return new ResultMessage<Boolean>("401", "企业名称重复", false);
        }
        queryEnter = new Enterprise();
        queryEnter.setEnterprise_code(enterpriseReq.getEnterprise_code());
        listByEntity = enterpriseMapper.getListByEntity(queryEnter);
        if (listByEntity != null && listByEntity.size() > 0) {
            return new ResultMessage<Boolean>("401", "企业编号重复", false);
        }
        try {
            //更新info
            Enterprise enterprise = new Enterprise();
            BeanUtils.copyProperties(enterpriseReq,enterprise);
            if (enterpriseReq.getEnterprise_id() == null) {
                enterprise.setEnterprise_id(UUID.randomUUID().toString().replaceAll("-", ""));
            }
            enterprise.setEnterprise_type_id(enterpriseReq.getEnterpriseTypeResp().getId());
            enterpriseMapper.insertOrUpdateById(enterprise);
            //更新关系
            if(enterpriseReq.getEnterpriseTypeResp().getId()==2){
                //使用商
                for(String id:enterpriseReq.getBinging_id()){
                    EnterpriseBinging enterpriseBinging =
                            new EnterpriseBinging(id + "_" + enterprise.getEnterprise_id(), id, enterprise.getEnterprise_id());
                    enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                }
                EnterpriseBinging enterpriseBinging =
                        new EnterpriseBinging(enterprise.getEnterprise_id() + "_" + enterprise.getEnterprise_id(),
                                enterprise.getEnterprise_id(), enterprise.getEnterprise_id());
                enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
            }else {
                //非使用商，修改所绑定的使用商
                for(String id:enterpriseReq.getBinging_id()){
                    EnterpriseBinging enterpriseBinging =
                            new EnterpriseBinging(enterprise.getEnterprise_id() + "_" + id,  enterprise.getEnterprise_id(),id);
                    enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                }
            }
            return new ResultMessage<Boolean>("200", "操作成功", false);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage<Boolean>("501", "操作失败，服务器异常:"+e.getMessage(), false);
        }
    }
}
