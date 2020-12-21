package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.network.Network;
import com.lifuyi.dev_monitor.model.network.req.NetworkReq;
import com.lifuyi.dev_monitor.model.network.resp.NetworkResp;
import com.lifuyi.dev_monitor.service.NetWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/network")
@Api(description = "仪器管理-网关管理")
@CrossOrigin
public class NetWorkController {

    @Autowired
    private NetWorkService netWorkService;


    @PostMapping("/addOrUpdateNetWork")
    @ApiOperation(value = "插入或者更新网关",  notes = "返回的mesg是id")
//    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdateNetWork(@RequestBody Network network) {
        return netWorkService.addOrUpdateNetWork(network);
    }

    @PostMapping("/getNetWorkPages")
    @ApiOperation(value = "获取网关分页列表",  notes = "")
    public ResultMessage<PageInfo<NetworkResp>> getNetWorkPages(@RequestBody NetworkReq req){
        return netWorkService.getNetWorkPages(req);
    }


}
