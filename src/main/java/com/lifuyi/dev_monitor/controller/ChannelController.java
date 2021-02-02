package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
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
    @ApiOperation(value = "根据物理节点id获取未被分组、且未被绑定采集配置的通道编号", notes = "")
    @UserLoginToken
    public ResultMessage<List<String>> getChannelCode(@RequestParam("physicalId") String physicalId){
        return new ResultMessage<List<String>>("200","查询成功",channelService.getChannelCode(physicalId));
    }


    @PostMapping("/insertOrUpdateChannelParameter")
    @ApiOperation(value = "插入或者更新通道组参数,更新时不能更新codes字段", notes = "")
    @UserLoginToken
    public ResultMessage<String> insertOrUpdateChannelParameter(@RequestBody List<ChannelSaveReq> reqs){
        return channelService.insertOrUpdateChannelParameter(reqs);
    }

    @PostMapping("/getChannelParameterPages")
    @ApiOperation(value = "获取通道参数类型分页列表", notes = "")
    @UserLoginToken
    public ResultMessage<PageInfo<ChannelResp>> getChannelParameterPages(@RequestBody ChannelParameterReq  req){
        return channelService.getChannelParameterPages(req);
    }

    @PostMapping("/getChannelParameter")
    @ApiOperation(value = "根据物理节点id获取通道参数", notes = "")
    @UserLoginToken
    public ResultMessage<List<ChannelParameter>> getChannelParameter(@RequestParam("id") String id){
        return channelService.getChannelParameter(id);
    }

    @PostMapping("/deleteById")
    @ApiOperation(value = "根据id删除通道组", notes = "")
    @UserLoginToken
    public ResultMessage<Boolean> deleteById(@RequestParam("id") List<String> id){
        try {
            for(String s:id){
                channelService.deleteById(s);
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }






}
