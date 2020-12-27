package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.EnterpriseMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.EnterpriseBinging;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterprisePageReq;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseResp;
import com.lifuyi.dev_monitor.service.EnterpriseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {

    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Transactional
    @Override
    public ResultMessage<Boolean> addOrUpdate(EnterpriseReq enterpriseReq) {
        Enterprise queryEnter = new Enterprise();

        try {
            //更新info
            Enterprise enterprise = new Enterprise();
            BeanUtils.copyProperties(enterpriseReq, enterprise);
            if (enterpriseReq.getEnterprise_id() == null) {
                //新增的时候企业名称和编号不能重复
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
                enterprise.setEnterprise_id(UUID.randomUUID().toString().replaceAll("-", ""));
            }else {
                //编辑的时候企业名称和编号不能改来和其他的重复
                Integer num=enterpriseMapper.verificationName(enterpriseReq.getEnterprise_id(),enterpriseReq.getEnterprise_name());
                if(num>0){
                    return new ResultMessage<Boolean>("401", "企业名称重复", false);
                }
                Integer n=enterpriseMapper.verificationCode(enterpriseReq.getEnterprise_id(),enterpriseReq.getEnterprise_code());
                if(n>0){
                    return new ResultMessage<Boolean>("401", "企业编号重复", false);
                }

            }
            enterprise.setEnterprise_type_id(enterpriseReq.getEnterpriseTypeResp().getId());
            enterprise.setUpdate_time(new Date());
            enterpriseMapper.insertOrUpdateById(enterprise);
            //更新关系
            if (enterpriseReq.getBinging_id() != null && enterpriseReq.getBinging_id().size() > 0) {
                if (enterpriseReq.getEnterpriseTypeResp().getId() == 2) {
                    //使用商
                    for (String id : enterpriseReq.getBinging_id()) {
                        EnterpriseBinging enterpriseBinging =
                                new EnterpriseBinging(id + "_" + enterprise.getEnterprise_id(), id, enterprise.getEnterprise_id());
                        enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                    }
                    EnterpriseBinging enterpriseBinging =
                            new EnterpriseBinging(enterprise.getEnterprise_id() + "_" + enterprise.getEnterprise_id(),
                                    enterprise.getEnterprise_id(), enterprise.getEnterprise_id());
                    enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                } else {
                    //非使用商，修改所绑定的使用商
                    //删除
                    enterpriseMapper.deleteBingingByNotServiceId(enterprise.getEnterprise_id());
                    //添加
                    for (String id : enterpriseReq.getBinging_id()) {
                        EnterpriseBinging enterpriseBinging =
                                new EnterpriseBinging(enterprise.getEnterprise_id() + "_" + id, enterprise.getEnterprise_id(), id);
                        enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                    }
                }
            }
            return new ResultMessage<Boolean>("200", "操作成功", false);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage<Boolean>("501", "操作失败，服务器异常:" + e.getMessage(), false);
        }
    }

    @Override
    public ResultMessage<PageInfo<EnterpriseResp>> getEnterprisePage(EnterprisePageReq pageReq) {
        PageHelper.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        Enterprise enterprise = new Enterprise();
        enterprise.setEnterprise_name(pageReq.getEnterprise_name());
        List<Enterprise> list = enterpriseMapper.getListByEntity(enterprise);
        PageInfo pageInfo = new PageInfo(list);
        List<EnterpriseResp> respList = new ArrayList<>();
        List<String> names;
        for (Enterprise enter : list) {
            EnterpriseResp enterpriseResp = new EnterpriseResp();
            if (enter.getEnterprise_type_id() == 2) {
                names = new ArrayList<>();
                names.add(enter.getEnterprise_name());
                enterpriseResp.setService_names(names);
            } else {
                names = enterpriseMapper.getServiceNames(enter.getEnterprise_id());
                enterpriseResp.setService_names(names);
            }
            enterpriseResp.setEnterpriseTypeResp(enterpriseMapper.getTypeById(enter.getEnterprise_type_id()));
            BeanUtils.copyProperties(enter, enterpriseResp);
            respList.add(enterpriseResp);
        }
        pageInfo.setList(respList);
        return new ResultMessage<PageInfo<EnterpriseResp>>("200", "查询成功", pageInfo);
    }

    @Override
    public ResultMessage<List<Enterprise>> getEnterprise(Enterprise enterprise) {
        return new ResultMessage<List<Enterprise>>("200","查询成功",enterpriseMapper.getListByEntity(enterprise));
    }

    @Override
    public ResultMessage<List<Enterprise>> getEnterpriseByTypeIds(List<String> ids) {
        return new ResultMessage<List<Enterprise>>("200","查询成功",enterpriseMapper.getEnterpriseByTypeIds(ids));
    }

}
