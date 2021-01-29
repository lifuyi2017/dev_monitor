package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.CollectConfigQueryReq;
import com.lifuyi.dev_monitor.model.collect.req.StartOrStopCollect;
import com.lifuyi.dev_monitor.model.collect.resp.CollectConfigResp;

import java.util.List;

public interface CollectService {
    ResultMessage<List<String>> getUnBindingCollectChannelCode(String typeId, String physicalId);

    ResultMessage<Boolean> insertOrUpdateCollectConfig(CollectDevConfig config);

    ResultMessage<List<CollectConfigResp>> getCollectConfigByDevGroup(CollectConfigQueryReq req);

    ResultMessage<Boolean> startOrStopCollect(List<StartOrStopCollect> startOrStopCollect);

    void deleteByDevId(String parent_id);

    void deleteById(String id) throws Exception;

    ResultMessage<List<CollectConfigResp>> getCollectConfigByWorkShopId(String id);

    ResultMessage<List<CollectConfigResp>> getCollectConfigByFactoryId(String id,String enterpriseId);
}
