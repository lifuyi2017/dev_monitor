package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.req.ChannelParameterReq;
import com.lifuyi.dev_monitor.model.channel.req.ChannelSaveReq;
import com.lifuyi.dev_monitor.model.channel.resp.ChannelResp;
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

    @PostMapping("/insertOrUpdateChannelParameter")
    @ApiOperation(value = "插入或者更新通道参数", notes = "")
    public ResultMessage<String> insertOrUpdateChannelParameter(@RequestBody ChannelSaveReq channelSaveReq){
        return channelService.insertOrUpdateChannelParameter(channelSaveReq);
    }

    @PostMapping("/getChannelParameterPages")
    @ApiOperation(value = "获取分页列表", notes = "")
    public ResultMessage<PageInfo<ChannelResp>> getChannelParameterPages(@RequestBody ChannelParameterReq  req){
        return channelService.getChannelParameterPages(req);
    }


}
