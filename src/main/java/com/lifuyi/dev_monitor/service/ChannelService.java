package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.req.ChannelParameterReq;
import com.lifuyi.dev_monitor.model.channel.req.ChannelSaveReq;
import com.lifuyi.dev_monitor.model.channel.resp.ChannelResp;

import java.util.List;

public interface ChannelService {
    List<String> getChannelCode(String physicalId);

    ResultMessage<String> insertOrUpdateChannelParameter(ChannelSaveReq channelSaveReq);

    ResultMessage<PageInfo<ChannelResp>> getChannelParameterPages(ChannelParameterReq req);
}
