package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.mongodao.DevicePredicDataDao;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/front/mongo")
@CrossOrigin
@Api(description = "前台系统接口---不能与后台系统打通部分，使用的是mongo数据部分")
public class FrontMongoController {

    @Resource
    private DevicePredicDataDao devicePredicDataDao;

}
