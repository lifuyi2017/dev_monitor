package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;

import java.util.List;

public interface WorkShopService {
    ResultMessage<Boolean> insertOrUpdateWorkShop(WorkShop workShop);

    ResultMessage<List<WorkShop>> getWorkShopList(WorkShopQueryReq req);
}
