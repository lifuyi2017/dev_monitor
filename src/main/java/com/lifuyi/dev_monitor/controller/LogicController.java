package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.logic.LogicNode;
import com.lifuyi.dev_monitor.model.logic.req.LogicQueryReq;
import com.lifuyi.dev_monitor.model.logic.req.LogicSaveReq;
import com.lifuyi.dev_monitor.model.logic.resp.LogicResp;
import com.lifuyi.dev_monitor.service.LogicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


    @PostMapping("/getLogicList")
    @ApiOperation(value = "获取逻辑节点，不带分页", notes = "")
    public ResultMessage<List<LogicResp>> getLogicList(@RequestBody LogicNode node){
        return logicService.getLogicList(node);
    }

    @PostMapping(value = "/deleteById")
    @ApiOperation(value = "根据id删除逻辑", notes = "")
    public ResultMessage<Boolean> deleteById(@RequestParam("id") List<String> id) {
        try {
            for(String s:id){
                logicService.deleteById(s);
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }

}
