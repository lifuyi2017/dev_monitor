package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;

import java.util.List;

public interface WorkShopMapper {
    WorkShop getWorkShopByName(String name, String enterprise_id,String parent_id);

    void insertOrUpdateWorkShop(WorkShop workShop);

    List<WorkShop> getWorkShopList(WorkShopQueryReq req);
}
