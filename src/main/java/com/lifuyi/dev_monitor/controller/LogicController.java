package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.logic.req.LogicQueryReq;
import com.lifuyi.dev_monitor.model.logic.req.LogicSaveReq;
import com.lifuyi.dev_monitor.model.logic.resp.LogicResp;
import com.lifuyi.dev_monitor.service.LogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/logic")
@CrossOrigin
@Api(description = "仪器管理-逻辑管理")
public class LogicController {

    @Resource
    private LogicService logicService;

    @PostMapping("/addOrUpdateLogic")
    @ApiOperation(value = "新增或者修改逻辑节点", notes = "")
//    @UserLoginToken
    public ResultMessage<String> addOrUpdateLogic(@RequestBody LogicSaveReq logicSaveReq){
        return logicService.addOrUpdateLogic(logicSaveReq);
    }


    @PostMapping("/getLogicPages")
    @ApiOperation(value = "获取逻辑节点，带分页", notes = "")
    public ResultMessage<PageInfo<LogicResp>> getLogicPages(@RequestBody LogicQueryReq  logicQueryReq){
        return logicService.getLogicPages(logicQueryReq);
    }





}
