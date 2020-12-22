package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.service.PhysicalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/physical")
@Api(description = "仪器管理-物理管理")
@CrossOrigin
public class PhysicalController {

    @Resource
    private PhysicalService physicalService;




}
