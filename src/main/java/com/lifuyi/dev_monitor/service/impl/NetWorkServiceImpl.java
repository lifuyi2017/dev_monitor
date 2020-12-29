package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.NetworkMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.network.Network;
import com.lifuyi.dev_monitor.model.network.req.NetworkReq;
import com.lifuyi.dev_monitor.model.network.resp.NetworkResp;
import com.lifuyi.dev_monitor.service.NetWorkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("netWorkService")
public class NetWorkServiceImpl implements NetWorkService {

    @Resource
    private NetworkMapper networkMapper;

    @Override
    public ResultMessage<Boolean> addOrUpdateNetWork(Network network) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        Network ip=networkMapper.getByIp(network.getNetwork_ip());
        if((StringUtils.isBlank(network.getNetwork_id()) && ip!=null) ||
                (!StringUtils.isBlank(network.getNetwork_id()) && ip!=null && !ip.getNetwork_id().equals(network.getNetwork_id()))){
            return new ResultMessage<Boolean>("401","网关ip重复",false);
        }
        if(StringUtils.isBlank(network.getNetwork_id())){
            network.setNetwork_id(id);
        }
        networkMapper.addOrUpdateNetWork(network);
        return new ResultMessage<Boolean>("200",network.getNetwork_id(),true);
    }

    @Override
    public ResultMessage<PageInfo<NetworkResp>> getNetWorkPages(NetworkReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<NetworkResp> list=networkMapper.getListByEntity(req.getNetwork());
        PageInfo<NetworkResp> networkRespPageInfo = new PageInfo<>(list);
        return new ResultMessage<PageInfo<NetworkResp>>("200","操作成功",networkRespPageInfo);
    }

    @Override
    public ResultMessage<List<NetworkResp>> getNetWorkList(Network network) {
        return new ResultMessage<List<NetworkResp>>("200","操作成功",networkMapper.getListByEntity(network));
    }




}
