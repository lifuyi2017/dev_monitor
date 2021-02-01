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
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevBingding;
import com.lifuyi.dev_monitor.model.mqtt.CollectConfig;
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
    public ResultMessage<List<BaseDevBingding>> getNotBingingDevByEnterpriseId(@RequestParam("enterpriseId") String enterpriseId){
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

    @PostMapping(value = "/getDevByDevGroupId")
    @ApiOperation(value = "获取设备组下的设备", notes = "")
    public ResultMessage<List<WorkShopDev>> getDevByDevGroupId(@RequestParam("id")
                                                               @ApiParam(value = "设备组",required = true) String id){
        return workShopService.getDevByDevGroupId(id);
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
        if("1".equals(req.getType())){
            return collectService.getCollectConfigByFactoryId(req.getId(),req.getEnterprise_id());
        }else if("2".equals(req.getType())){
            return collectService.getCollectConfigByWorkShopId(req.getId());
        }else {
            return collectService.getCollectConfigByDevGroup(req);
        }
    }


/*    @PostMapping(value = "/getCollectConfigByWorkShopId")
    @ApiOperation(value = "通过车间id获取采集节点设置", notes = "")
    public ResultMessage<List<CollectConfigResp>>  getCollectConfigByWorkShopId(@RequestParam("id") String id){
        return collectService.getCollectConfigByWorkShopId(id);
    }

    @PostMapping(value = "/getCollectConfigByFactoryId")
    @ApiOperation(value = "通过厂房id获取采集节点设置", notes = "")
    public ResultMessage<List<CollectConfigResp>>  getCollectConfigByFactoryId(@RequestParam("id") String id,
                                                                               @RequestParam("enterpriseId") String enterpriseId){
        return collectService.getCollectConfigByFactoryId(id,enterpriseId);
    }*/


    @PostMapping(value = "/startOrStopCollect")
    @ApiOperation(value = "开始或者结束采集", notes = "")
    public ResultMessage<Boolean> startOrStopCollect(@RequestBody List<StartOrStopCollect> startOrStopCollect){
        return collectService.startOrStopCollect(startOrStopCollect);
    }

    @PostMapping(value = "/deleteById")
    @ApiOperation(value = "根据id删除采集配置", notes = "")
    public ResultMessage<Boolean> deleteById(@RequestParam("id") List<String> id) {
        try {
            for(String s:id){
                collectService.deleteById(s);
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }


    @PostMapping(value = "/deleteWorkShopById")
    @ApiOperation(value = "根据id删除厂房车间", notes = "")
    public ResultMessage<Boolean> deleteWorkShopById(@RequestParam("id") String id,@RequestParam("type") String type) {
        try {
            workShopService.deleteWorkShopById(id,type);
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }


    @PostMapping(value = "/getCollectConfigByConfig")
    @ApiOperation(value = "根据相应查询条件获取通道配置，如果某些字段不参与查询就设置为null", notes = "")
    public ResultMessage<List<CollectDevConfig>> getCollectConfigByConfig(@RequestBody CollectDevConfig config){
        return new ResultMessage<List<CollectDevConfig>>("200","success",collectService.getCollectConfigByConfig(config));
    }

}
