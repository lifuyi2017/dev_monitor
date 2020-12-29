package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/collect")
@CrossOrigin
@Api(description = "采集管理")
public class CollectController {

    @Resource
    private CollectService collectService;


//    @PostMapping(value = "/insertOrUpdateWorkShop")
//    @ApiOperation(value = "插入或者更新厂房，不传id字段是插入，传是更新", notes = "返回的mesg是id")
//    public ResultMessage<Boolean> insertOrUpdateWorkShop(@RequestBody WorkShop workShop){
//        return devService.insertOrUpdateDev(baseDevEntity);
//    }


}
