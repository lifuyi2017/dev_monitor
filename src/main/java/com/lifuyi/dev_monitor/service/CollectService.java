package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;

import java.util.List;

public interface CollectService {
    ResultMessage<List<String>> getUnBindingCollectChannelCode(String typeId, String physicalId);
}
