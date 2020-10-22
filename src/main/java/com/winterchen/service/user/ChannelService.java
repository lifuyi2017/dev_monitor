package com.winterchen.service.user;

import com.winterchen.model.Channel;
import com.winterchen.model.ResultMessage;

import java.util.List;

public interface ChannelService {
    void updateById(Channel channel) throws Exception;

    void insertEntity(Channel channel) throws Exception;

    List<Channel> queryByEntity(Channel channel) throws Exception;

    ResultMessage<Boolean> deleteById(String channel_id) throws Exception;

    ResultMessage<Boolean> deleteByMeasureId(String m_id);
}
