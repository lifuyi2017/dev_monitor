package com.winterchen.service.user.impl;

import com.winterchen.dao.NetworkMapper;
import com.winterchen.model.Network;
import com.winterchen.service.user.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("networkService")
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkMapper networkMapper;

    @Override
    public List<Network> queryByEntity(Network network) throws Exception{
        return networkMapper.queryByEntity(network);
    }

    @Override
    public void deleteNetWorkById(String network_id) throws Exception {
        networkMapper.deleteNetWorkById(network_id);
    }

}
