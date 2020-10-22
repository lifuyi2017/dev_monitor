package com.winterchen.dao;

import com.winterchen.model.Channel;

import java.util.List;

public interface ChannelMapper {
    void updateById(Channel channel);

    void insertEntity(Channel channel);

    List<Channel> queryByEntity(Channel channel);

    void deleteById(String channel_id);

    void deleteByEnterpriseId(String id);

    List<String> getByMeasureId(String measure_id);

    void deleteByMeasureId(String measure_id);

    void updateByEnterpriseIdNull(String id);
}
