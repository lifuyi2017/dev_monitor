package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.req.ChannelSaveReq;

import java.util.List;

public interface ChannelService {
    List<String> getChannelCode(String physicalId);

    ResultMessage<String> insertOrUpdateChannelParameter(ChannelSaveReq channelSaveReq);
}
