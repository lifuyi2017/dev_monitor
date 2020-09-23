package com.winterchen.dao;

import com.winterchen.model.Channel;

import java.util.List;

public interface ChannelMapper {
    void updateById(Channel channel);

    void insertEntity(Channel channel);

    List<Channel> queryByEntity(Channel channel);

    void deleteById(String channel_id);
}
