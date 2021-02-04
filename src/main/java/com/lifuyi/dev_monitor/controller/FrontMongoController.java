package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.mongo.current_data.DevicePredicData;
import com.lifuyi.dev_monitor.mongodao.DevicePredicDataDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/front/mongo")
@CrossOrigin
@Api(description = "前台系统接口---不能与后台系统打通部分，使用的是mongo数据部分")
public class FrontMongoController {

    @Resource
    private DevicePredicDataDao devicePredicDataDao;

    @PostMapping("/getDevHealthyByDevId")
    @ApiOperation(value = "通过设备id获取设备健康状态",  notes = "")
    public DevicePredicData getDevHealthyByDevId(@RequestParam("devId") String devId){
        return devicePredicDataDao.getDevHealthyByDevId(devId);
    }


}
