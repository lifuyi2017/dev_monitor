package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.ShopDevGroup;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;

import java.util.List;

public interface WorkShopMapper {
    WorkShop getWorkShopByName(String name, String enterprise_id,String parent_id);

    void insertOrUpdateWorkShop(WorkShop workShop);

    List<WorkShop> getWorkShopList(WorkShopQueryReq req);

    WorkShopDev getWorkShopDevByName(String shop_id, String type, String name, String parent_id);

    WorkShopDev getWorkShopDevByDevId(String dev_id);

    void insertOrUpdateWorkShopDev(WorkShopDev workShopDev);

    List<BaseDevEntity> getNotBingingDevByEnterpriseId(String enterpriseId);

    List<WorkShopDev> getWorkShopDevList(String workshopId);

    List<ShopDevGroup> getWorkShopDevGroupList(String workshopId);

    List<WorkShopDev> getSonDevList(String id);

    String getParentIdById(String farm_id);
}
