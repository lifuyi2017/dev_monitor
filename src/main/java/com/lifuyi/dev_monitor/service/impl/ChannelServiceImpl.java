package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.ChannelMapper;
import com.lifuyi.dev_monitor.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public List<String> getChannelCode(String physicalId) {
        return channelMapper.getChannelCodeByPhysicalId(physicalId);
    }

}
