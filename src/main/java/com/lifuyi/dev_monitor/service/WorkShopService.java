package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.ShopDevGroup;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;

import java.util.List;

public interface WorkShopService {
    ResultMessage<Boolean> insertOrUpdateWorkShop(WorkShop workShop);

    ResultMessage<List<WorkShop>> getWorkShopList(WorkShopQueryReq req);

    ResultMessage<Boolean> insertOrUpdateWorkShopDev(WorkShopDev workShopDev);

    ResultMessage<List<BaseDevEntity>> getNotBingingDevByEnterpriseId(String enterpriseId);

    ResultMessage<List<WorkShopDev>> getWorkShopDevList(String workshopId);

    ResultMessage<List<ShopDevGroup>> getWorkShopDevGroupList(String workshopId);


}
