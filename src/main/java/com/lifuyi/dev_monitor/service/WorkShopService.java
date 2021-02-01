package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.ShopDevGroup;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevBingding;

import java.util.List;

public interface WorkShopService {
    ResultMessage<Boolean> insertOrUpdateWorkShop(WorkShop workShop);

    ResultMessage<List<WorkShop>> getWorkShopList(WorkShopQueryReq req);

    ResultMessage<Boolean> insertOrUpdateWorkShopDev(WorkShopDev workShopDev);

    ResultMessage<List<BaseDevBingding>> getNotBingingDevByEnterpriseId(String enterpriseId);

    ResultMessage<List<WorkShopDev>> getWorkShopDevList(String workshopId);

    ResultMessage<List<ShopDevGroup>> getWorkShopDevGroupList(String workshopId);


    void deleteById(String id);

    ResultMessage<List<WorkShopDev>> getDevByDevGroupId(String id);

    void deleteWorkShopById(String id, String type) throws Exception;

}
