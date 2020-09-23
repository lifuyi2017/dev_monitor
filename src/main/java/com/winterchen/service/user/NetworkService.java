package com.winterchen.service.user;

import com.winterchen.model.Network;

import java.util.List;

public interface NetworkService {
    List<Network> queryByEntity(Network network) throws Exception;

    void deleteNetWorkById(String network_id) throws Exception;
}
