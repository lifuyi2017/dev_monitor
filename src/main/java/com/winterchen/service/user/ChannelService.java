package com.winterchen.service.user;

import com.winterchen.model.Channel;

import java.util.List;

public interface ChannelService {
    void updateById(Channel channel) throws Exception;

    void insertEntity(Channel channel) throws Exception;

    List<Channel> queryByEntity(Channel channel) throws Exception;

    void deleteNetWorkById(String channel_id) throws Exception;
}
