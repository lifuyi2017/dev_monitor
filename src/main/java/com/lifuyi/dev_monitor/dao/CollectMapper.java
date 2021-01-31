package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.CollectConfigQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.CollectConfigResp;
import com.lifuyi.dev_monitor.model.mqtt.CollectConfig;

import java.util.List;

public interface CollectMapper {

    List<String> getUnBindingCollectChannelCode(String typeId, String physicalId);

    Integer countNumCollectChannel(String physicalId,List<String> codes);

    void insertOrUpdateCollectConfig(CollectDevConfig config);

    List<CollectConfigResp> getCollectConfigByDevGroup(CollectConfigQueryReq req);

    CollectConfig getConfigById(String id);

    CollectDevConfig getCollectConfigById(String id);

    void deleteById(String id);

    List<CollectDevConfig> getCollectConfigByPhysicalId(String id);

    List<CollectDevConfig> getConfigByChannelTypeId(String id);

    void removeLogicId(String logic_id);

    List<CollectDevConfig> getCollectConfigByConfig(CollectDevConfig config);
}
