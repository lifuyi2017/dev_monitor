package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import com.lifuyi.dev_monitor.model.channel.resp.ChannelResp;
import com.lifuyi.dev_monitor.model.channel.resp.PhysicalChannelResp;

import java.util.List;
import java.lang.String;

public interface ChannelMapper {

    List<String> getChannelCodeByPhysicalId(String physicalId);

    String getMaxChannelTypeId(String physical_id, List<String> codes);

    void clearBindingByTypeId(String id);

    void insertOrUpdateChannelParameter(ChannelParameter channelParameter);

    void BindingParameterAndChannel(String id, String physical_id, List<String> codes);

    List<ChannelResp> getChannelParameterPages(ChannelParameter parameter);

    PhysicalChannelResp getPhysicalChannelResp(String id);

    void BindingCollectAndChannel(String physical_id, List<String> codes, String collect_id);

    ChannelParameter getParameterById(String channel_type_id);

    void deleteById(String id);

    List<ChannelParameter> getUnBingdingChannelParameterByPhyId(String id);
}
