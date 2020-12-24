package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/channel")
@CrossOrigin
@Api(description = "仪器管理-通道管理")
public class ChannelController {

    @Resource
    private ChannelService channelService;

    @PostMapping("/getChannelCode")
    @ApiOperation(value = "根据物理节点id获取通道编号", notes = "")
    public ResultMessage<List<String>> getChannelCode(@RequestParam("physicalId") String physicalId){
        return new ResultMessage<List<String>>("200","查询成功",channelService.getChannelCode(physicalId));
    }



}
