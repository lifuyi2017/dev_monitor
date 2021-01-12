package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.req.CollectConfigQueryReq;
import com.lifuyi.dev_monitor.model.collect.req.StartOrStopCollect;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.collect.resp.CollectConfigResp;
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

    @PostMapping(value = "/getUnBindingCollectChannelCode")
    @ApiOperation(value = "获取指定通道种类下面的未绑定采集配置的通道", notes = "新增采集时用")
    public ResultMessage<List<String>> getUnBindingCollectChannelCode(@RequestParam("typeId")
                                                                          @ApiParam(value = "通道类型id",required = true) String typeId,
                                                                      @RequestParam("physicalId")
                                                                      @ApiParam(value = "物理节点id",required = true) String physicalId){
        return collectService.getUnBindingCollectChannelCode(typeId,physicalId);
    }


    @PostMapping(value = "/insertOrUpdateCollectConfig")
    @ApiOperation(value = "新增采集节点设置", notes = "新增采集时用")
    public ResultMessage<Boolean> insertOrUpdateCollectConfig(@RequestBody CollectDevConfig config){
        return collectService.insertOrUpdateCollectConfig(config);
    }


    @PostMapping(value = "/getCollectConfigByDevGroup")
    @ApiOperation(value = "通过设备或者设备组获取采集节点设置", notes = "")
    public ResultMessage<List<CollectConfigResp>>  getCollectConfigByDevGroup(@RequestBody CollectConfigQueryReq req){
        return collectService.getCollectConfigByDevGroup(req);
    }


    @PostMapping(value = "/startOrStopCollect")
    @ApiOperation(value = "开始或者结束采集", notes = "")
    public ResultMessage<Boolean> startOrStopCollect(@RequestBody StartOrStopCollect startOrStopCollect){
        return collectService.startOrStopCollect(startOrStopCollect);
    }



}
