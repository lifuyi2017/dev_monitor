package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import com.lifuyi.dev_monitor.model.channel.resp.ChannelResp;

import java.util.List;
import java.lang.String;

public interface ChannelMapper {

    List<String> getChannelCodeByPhysicalId(String physicalId);

    String getMaxChannelTypeId(String physical_id, List<String> codes);

    void clearBindingByTypeId(String id);

    void insertOrUpdateChannelParameter(ChannelParameter channelParameter);

    void BindingParameterAndChannel(String id, String physical_id, List<String> codes);

    List<ChannelResp > getChannelParameterPages(ChannelParameter parameter);
}
