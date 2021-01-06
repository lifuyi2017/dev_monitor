package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.collect.WorkShop;

import java.util.List;

public interface CollectMapper {

    List<String> getUnBindingCollectChannelCode(String typeId, String physicalId);
}
