package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.network.Network;
import com.lifuyi.dev_monitor.model.network.req.NetworkReq;
import com.lifuyi.dev_monitor.model.network.resp.NetworkResp;

public interface NetWorkService {
    ResultMessage<Boolean> addOrUpdateNetWork(Network network);

    ResultMessage<PageInfo<NetworkResp>> getNetWorkPages(NetworkReq req);
}
