package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.CollectMapper;
import com.lifuyi.dev_monitor.dao.WorkShopMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.req.CollectConfigQueryReq;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.CollectConfigResp;
import com.lifuyi.dev_monitor.model.collect.resp.ShopDevGroup;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.service.CollectService;
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
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CollectService collectService;


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

    @Override
    public ResultMessage<Boolean> insertOrUpdateWorkShopDev(WorkShopDev workShopDev) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        //判断车间下有没有同名的设备或者设备组
        WorkShopDev nameDev=workShopMapper.getWorkShopDevByName(workShopDev.getShop_id(),workShopDev.getType(),
                workShopDev.getName(),workShopDev.getParent_id());
        if((StringUtils.isBlank(workShopDev.getId()) && nameDev!=null) ||
                (!StringUtils.isBlank(workShopDev.getId()) && nameDev!=null && !nameDev.getId().equals(workShopDev.getId()))){
            return new ResultMessage<Boolean>("401","名称重复",false);
        }
        //判断此设备是否已经被绑定
        if(!StringUtils.isBlank(workShopDev.getDev_id())){
            WorkShopDev devIdDev=workShopMapper.getWorkShopDevByDevId(workShopDev.getDev_id());
            if((StringUtils.isBlank(workShopDev.getId()) && devIdDev!=null) ||
                    (!StringUtils.isBlank(workShopDev.getId()) && devIdDev!=null && !devIdDev.getId().equals(workShopDev.getId()))){
                return new ResultMessage<Boolean>("401","该设备已经被绑定",false);
            }
        }
        if(("3".equals(workShopDev.getType()) && workShopDev.getDev_id()==null) ||
                ("5".equals(workShopDev.getType()) && workShopDev.getDev_id()==null)){
            return new ResultMessage<Boolean>("401","添加设备时必须绑定设备",false);
        }
        if(StringUtils.isBlank(workShopDev.getId())){
            workShopDev.setId(id);
        }
        workShopMapper.insertOrUpdateWorkShopDev(workShopDev);
        return new ResultMessage<Boolean>("200",workShopDev.getId(),true);
    }


    @Override
    public ResultMessage<List<BaseDevEntity>> getNotBingingDevByEnterpriseId(String enterpriseId) {
        List<BaseDevEntity> baseDevEntities=workShopMapper.getNotBingingDevByEnterpriseId(enterpriseId);
        return new ResultMessage<List<BaseDevEntity>>("200","查询成功",baseDevEntities);
    }

    @Override
    public ResultMessage<List<WorkShopDev>> getWorkShopDevList(String workshopId) {
        List<WorkShopDev> devList=workShopMapper.getWorkShopDevList(workshopId);
        return new ResultMessage<List<WorkShopDev>>("200","查询成功",devList);
    }

    @Override
    public ResultMessage<List<ShopDevGroup>> getWorkShopDevGroupList(String workshopId) {
        List<ShopDevGroup> shopDevGroupList=workShopMapper.getWorkShopDevGroupList(workshopId);
        for(ShopDevGroup shopDevGroup:shopDevGroupList){
            List<WorkShopDev> devList=workShopMapper.getSonDevList(shopDevGroup.getId());
            shopDevGroup.setWorkShopDevList(devList);
        }
        return new ResultMessage<List<ShopDevGroup>>("200","查询成功",shopDevGroupList);
    }

    @Override
    public void deleteById(String id) {
        try {
            WorkShopQueryReq req = new WorkShopQueryReq();
            req.setType("2");
            req.setParent_id(id);
            List<WorkShop> workShopList = workShopMapper.getWorkShopList(req);
            for(WorkShop workShop:workShopList){
                List<WorkShopDev> workShopDevList = workShopMapper.getWorkShopDevList(workShop.getId());
                for(WorkShopDev dev:workShopDevList){
                    CollectConfigQueryReq collectConfigQueryReq = new CollectConfigQueryReq();
                    collectConfigQueryReq.setId(dev.getId());
                    List<CollectConfigResp> collectConfigByDevGroup = collectMapper.getCollectConfigByDevGroup(collectConfigQueryReq);
                    for(CollectConfigResp resp:collectConfigByDevGroup){
                        collectService.deleteById(resp.getId());
                    }
                    workShopMapper.deleteDevById(dev.getId());
                }
                List<ShopDevGroup> workShopDevGroupList = workShopMapper.getWorkShopDevGroupList(workShop.getId());
                for(ShopDevGroup dev:workShopDevGroupList){
                    CollectConfigQueryReq collectConfigQueryReq = new CollectConfigQueryReq();
                    collectConfigQueryReq.setId(dev.getId());
                    List<CollectConfigResp> collectConfigByDevGroup = collectMapper.getCollectConfigByDevGroup(collectConfigQueryReq);
                    for(CollectConfigResp resp:collectConfigByDevGroup){
                        collectService.deleteById(resp.getId());
                    }
                    workShopMapper.deleteDevById(dev.getId());
                }
                workShopMapper.deleteShopById(workShop.getId());
            }
            workShopMapper.deleteShopById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ResultMessage<List<WorkShopDev>> getDevByDevGroupId(String id) {
        return new ResultMessage<List<WorkShopDev>>("200","查询成功",workShopMapper.getDevByDevGroupId(id));
    }


}
