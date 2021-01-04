package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.ShopDevGroup;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.service.CollectService;
import com.lifuyi.dev_monitor.service.WorkShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @PostMapping(value = "/insertOrUpdateWorkShopDev")
    @ApiOperation(value = "插入或者更新设备或者设备组", notes = "")
    public ResultMessage<Boolean> insertOrUpdateWorkShopDev(@RequestBody WorkShopDev workShopDev){
        return workShopService.insertOrUpdateWorkShopDev(workShopDev);
    }

    @PostMapping(value = "/getNotBingingDevByEnterpriseId")
    @ApiOperation(value = "获取企业下面未被绑定的设备列表", notes = "采集管理里面绑定设备id时下拉所用")
    public ResultMessage<List<BaseDevEntity>> getNotBingingDevByEnterpriseId(@RequestParam("enterpriseId") String enterpriseId){
        return workShopService.getNotBingingDevByEnterpriseId(enterpriseId);
    }


    @PostMapping(value = "/getWorkShopDevList")
    @ApiOperation(value = "获取车间下面的设备", notes = "")
    public ResultMessage<List<WorkShopDev>> getWorkShopDevList(@RequestParam("workshopId")
                                                                   @ApiParam(value = "车间id",required = true) String workshopId){
        return workShopService.getWorkShopDevList(workshopId);
    }

    @PostMapping(value = "/getWorkShopDevGroupList")
    @ApiOperation(value = "获取车间下面的设备组", notes = "")
    public ResultMessage<List<ShopDevGroup>> getWorkShopDevGroupList(@RequestParam("workshopId")
                                                                         @ApiParam(value = "车间id",required = true) String workshopId){
        return workShopService.getWorkShopDevGroupList(workshopId);
    }






}
