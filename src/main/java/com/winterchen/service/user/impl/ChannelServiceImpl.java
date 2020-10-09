package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.model.Channel;
import com.winterchen.model.CollectionManager;
import com.winterchen.service.user.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private LogicRelationMapper logicRelationMapper;
    @Resource
    private CollectionManagerMapper collectionManagerMapper;

    @Override
    public void updateById(Channel channel) throws Exception {
        channelMapper.updateById(channel);
    }

    @Override
    public void insertEntity(Channel channel) throws Exception {
        channelMapper.insertEntity(channel);
    }

    @Override
    public List<Channel> queryByEntity(Channel channel) throws Exception{
        return channelMapper.queryByEntity(channel);
    }

    @Override
    public void deleteNetWorkById(String channel_id) throws Exception{
        channelMapper.deleteById(channel_id);
        logicRelationMapper.deleteByChannelId(channel_id);
        collectionManagerMapper.deleteByChannelId(channel_id);
    }
}
