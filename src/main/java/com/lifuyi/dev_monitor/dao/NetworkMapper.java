package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.network.Network;
import com.lifuyi.dev_monitor.model.network.resp.NetworkResp;

import java.util.List;

public interface NetworkMapper {
    Network getByIp(String network_ip);

    void addOrUpdateNetWork(Network network);

    List<NetworkResp> getListByEntity(Network network);
}
