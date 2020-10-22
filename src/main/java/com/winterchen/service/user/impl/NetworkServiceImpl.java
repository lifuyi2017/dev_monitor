package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.dao.NetworkMapper;
import com.winterchen.model.Measure;
import com.winterchen.model.Network;
import com.winterchen.service.user.ChannelService;
import com.winterchen.service.user.MeasureService;
import com.winterchen.service.user.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("networkService")
public class NetworkServiceImpl implements NetworkService {

    @Autowired
    private NetworkMapper networkMapper;
    @Autowired
    private MeasureService measureService;


    @Override
    public List<Network> queryByEntity(Network network) throws Exception{
        return networkMapper.queryByEntity(network);
    }

    @Override
    public void deleteNetWorkById(String network_id) throws Exception {
        networkMapper.deleteNetWorkById(network_id);
        measureService.updateNetWorkIdNull(network_id);
//        measureService.deleteByNetworkId(network_id);
    }

//    @Override
//    public void deleteByEnterpriseId(String id) {
//        List<String> ids=networkMapper.getIdByEnterpriseId(id);
//        for(String net_id:ids){
//            measureService.deleteByNetworkId(net_id);
//        }
//        networkMapper.deleteByEnterpriseId(id);
//    }

    @Override
    public void updateByEnterpriseIdNull(String id) {
        networkMapper.updateByEnterpriseIdNull(id);
    }

}
