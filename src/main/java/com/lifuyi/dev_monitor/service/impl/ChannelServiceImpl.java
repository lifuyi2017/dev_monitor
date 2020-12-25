package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.ChannelMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import com.lifuyi.dev_monitor.model.channel.req.ChannelSaveReq;
import com.lifuyi.dev_monitor.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public List<String> getChannelCode(String physicalId) {
        return channelMapper.getChannelCodeByPhysicalId(physicalId);
    }

    @Override
    public ResultMessage<String> insertOrUpdateChannelParameter(ChannelSaveReq channelSaveReq) {
        String channel_type_id=channelMapper.getMaxChannelTypeId(channelSaveReq.getPhysical_id(),channelSaveReq.getCodes());
        if(channel_type_id!=null){
            return new ResultMessage<String>("401","通道已经被设置为其他参数",channel_type_id);
        }
        String id= UUID.randomUUID().toString().replaceAll("-","");
        ChannelParameter channelParameter = channelSaveReq.getChannelParameter();
        if(channelParameter.getId()!=null){
            //需要先删除此参数配置绑定的通道
            channelMapper.clearBindingByTypeId(channelParameter.getId());
        }else {
            channelParameter.setId(id);
        }
        channelMapper.insertOrUpdateChannelParameter(channelParameter);
        channelMapper.BindingParameterAndChannel(channelParameter.getId(),channelSaveReq.getPhysical_id(),channelSaveReq.getCodes());
        return new ResultMessage<String>("200","操作成功",channelParameter.getId());
    }

}
