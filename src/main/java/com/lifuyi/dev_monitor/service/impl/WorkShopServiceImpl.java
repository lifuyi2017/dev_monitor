package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.WorkShopMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.service.WorkShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("workShopService")
public class WorkShopServiceImpl implements WorkShopService {
    @Resource
    private WorkShopMapper workShopMapper;


    @Override
    public ResultMessage<Boolean> insertOrUpdateWorkShop(WorkShop workShop) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        WorkShop nameShop=workShopMapper.getWorkShopByName(workShop.getName(),workShop.getEnterprise_id(),workShop.getParent_id());
        if((StringUtils.isBlank(workShop.getId()) && nameShop!=null) ||
                (!StringUtils.isBlank(workShop.getId()) && nameShop!=null && !nameShop.getId().equals(workShop.getId()))){
            return new ResultMessage<Boolean>("401","厂房或者车间名称重复",false);
        }
        if(StringUtils.isBlank(workShop.getId())){
            workShop.setId(id);
        }
        workShopMapper.insertOrUpdateWorkShop(workShop);
        return new ResultMessage<Boolean>("200",workShop.getId(),true);
    }

    @Override
    public ResultMessage<List<WorkShop>> getWorkShopList(WorkShopQueryReq req) {
        List<WorkShop> workShopList=workShopMapper.getWorkShopList(req);
        return new ResultMessage<List<WorkShop>>("200","查询成功",workShopList);
    }


}
