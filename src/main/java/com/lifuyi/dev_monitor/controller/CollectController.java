package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.service.CollectService;
import com.lifuyi.dev_monitor.service.WorkShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/collect")
@CrossOrigin
@Api(description = "采集管理")
public class CollectController {

    @Resource
    private CollectService collectService;
    @Resource
    private WorkShopService workShopService;

    @PostMapping(value = "/insertOrUpdateWorkShop")
    @ApiOperation(value = "插入或者更新厂房，不传id字段是插入，传是更新", notes = "返回的mesg是id")
    public ResultMessage<Boolean> insertOrUpdateWorkShop(@RequestBody WorkShop workShop){
        return workShopService.insertOrUpdateWorkShop(workShop);
    }

    @PostMapping(value = "/getWorkShopList")
    @ApiOperation(value = "获取下级车间或者下级厂房", notes = "")
    public ResultMessage<List<WorkShop>> getWorkShopList(@RequestBody WorkShopQueryReq req){
        return workShopService.getWorkShopList(req);
    }


}
