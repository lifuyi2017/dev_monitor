package com.winterchen.dao;

import com.winterchen.model.Network;

import java.util.List;

public interface NetworkMapper {
    List<Network> queryByEntity(Network network);

    void deleteNetWorkById(String network_id);

    void deleteByEnterpriseId(String id);

    List<String> getIdByEnterpriseId(String id);

    void updateByEnterpriseIdNull(String id);
}
