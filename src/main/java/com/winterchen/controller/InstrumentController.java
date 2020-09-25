package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.model.*;
import com.winterchen.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/instrument")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private MeasureService measureService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private LogicService logicService;

    /**
     * 新增或者更新网关
     */
    @ResponseBody
    @PostMapping("/addOrUpdateNetWork")
    public ResultMessage<Boolean> addOrUpdateNetWork(@RequestBody Network network) {
        ResultMessage<Boolean> resultMessage = new ResultMessage();
        try {
            if (network.getNetwork_id() != null && !"".equals(network.getNetwork_id().trim())) {
                //修改
                instrumentService.updateById(network);
                resultMessage.setValue(true);
                resultMessage.setMesg("修改成功");
                resultMessage.setStatuscode("200");
            } else {
                //新增
                network.setNetwork_id(UUID.randomUUID().toString().replaceAll("-", ""));
                instrumentService.insertEntity(network);
                resultMessage.setValue(true);
                resultMessage.setMesg("新增成功");
                resultMessage.setStatuscode("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(false);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
        }
        return resultMessage;
    }

    /**
     * 查询网关
     */
    @ResponseBody
    @PostMapping("/queryNetWork")
    public ResultMessage<PageInfo<Network>> queryNetWork(@RequestBody NetworkRequest networkRequest) {
        ResultMessage<PageInfo<Network>> networkPage = new ResultMessage<>();
        try {
            PageHelper.startPage(networkRequest.getPageNum(), networkRequest.getPageSize());
            List<Network> networkList = networkService.queryByEntity(networkRequest.getNetwork());
            PageInfo<Network> result = new PageInfo(networkList);
            networkPage.setValue(result);
            networkPage.setStatuscode("200");
            networkPage.setMesg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            networkPage.setValue(null);
            networkPage.setStatuscode("501");
            networkPage.setMesg("服务端错误：" + e.toString());
        }
        return networkPage;
    }

    /**
     * 删除网关，通过id
     */
    @ResponseBody
    @PostMapping("/deleteNetWorkById")
    public ResultMessage<Boolean> deleteNetWorkById(@RequestBody Network network) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            networkService.deleteNetWorkById(network.getNetwork_id());
            booleanResultMessage.setMesg("删除成功");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
        }
        return booleanResultMessage;
    }

    /**
     * 新增或者更新物理节点
     */
    @ResponseBody
    @PostMapping("/addOrUpdateMeasure")
    public ResultMessage<Boolean> addOrUpdateMeasure(@RequestBody Measure measure){
        ResultMessage<Boolean> resultMessage = new ResultMessage();
        try {
            if (measure.getMeasure_id() != null && !"".equals(measure.getMeasure_id().trim())) {
                //修改
                measureService.updateById(measure);
                resultMessage.setValue(true);
                resultMessage.setMesg("修改成功");
                resultMessage.setStatuscode("200");
            } else {
                //新增
                measure.setMeasure_id(UUID.randomUUID().toString().replaceAll("-", ""));
                measureService.insertEntity(measure);
                resultMessage.setValue(true);
                resultMessage.setMesg("新增成功");
                resultMessage.setStatuscode("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(false);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
        }
        return resultMessage;
    }

    /**
     * 查询物理节点
     */
    @ResponseBody
    @PostMapping("/queryMeasure")
    public ResultMessage<PageInfo<Measure>> queryMeasure(@RequestBody MeasureRequest measureRequest){
        ResultMessage<PageInfo<Measure>> networkPage = new ResultMessage<>();
        try {
            PageHelper.startPage(measureRequest.getPageNum(), measureRequest.getPageSize());
            List<Measure> measureList = measureService.queryByEntity(measureRequest.getMeasure());
            PageInfo<Measure> result = new PageInfo(measureList);
            networkPage.setValue(result);
            networkPage.setStatuscode("200");
            networkPage.setMesg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            networkPage.setValue(null);
            networkPage.setStatuscode("501");
            networkPage.setMesg("服务端错误：" + e.toString());
        }
        return networkPage;
    }

    /**
     * 删除物理节点，通过id
     */
    @ResponseBody
    @PostMapping("/deleteMeasureById")
    public ResultMessage<Boolean> deleteMeasureById(@RequestBody Measure measure) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            measureService.deleteNetWorkById(measure.getMeasure_id());
            booleanResultMessage.setMesg("删除成功");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
        }
        return booleanResultMessage;
    }


    /**
     * 新增或者更新通道
     */
    @ResponseBody
    @PostMapping("/addOrUpdateChannel")
    public ResultMessage<Boolean> addOrUpdateChannel(@RequestBody Channel channel){
        ResultMessage<Boolean> resultMessage = new ResultMessage();
        try {
            if (channel.getChannel_id() != null && !"".equals(channel.getChannel_id().trim())) {
                //修改
                channelService.updateById(channel);
                resultMessage.setValue(true);
                resultMessage.setMesg("修改成功");
                resultMessage.setStatuscode("200");
            } else {
                //新增
                channel.setChannel_id(UUID.randomUUID().toString().replaceAll("-", ""));
                channelService.insertEntity(channel);
                resultMessage.setValue(true);
                resultMessage.setMesg("新增成功");
                resultMessage.setStatuscode("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(false);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
        }
        return resultMessage;
    }

    /**
     * 查询通道
     */
    @ResponseBody
    @PostMapping("/queryChannel")
    public ResultMessage<PageInfo<Channel>> queryChannel(@RequestBody ChannelRequest channelRequest){
        ResultMessage<PageInfo<Channel>> channelPage = new ResultMessage<>();
        try {
            PageHelper.startPage(channelRequest.getPageNum(), channelRequest.getPageSize());
            List<Channel> channelList = channelService.queryByEntity(channelRequest.getChannel());
            PageInfo<Channel> result = new PageInfo(channelList);
            channelPage.setValue(result);
            channelPage.setStatuscode("200");
            channelPage.setMesg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            channelPage.setValue(null);
            channelPage.setStatuscode("501");
            channelPage.setMesg("服务端错误：" + e.toString());
        }
        return channelPage;
    }

    /**
     * 删除通道，通过id
     */
    @ResponseBody
    @PostMapping("/deleteChannelById")
    public ResultMessage<Boolean> deleteChannelById(@RequestBody Channel channel){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage();
        try {
            channelService.deleteNetWorkById(channel.getChannel_id());
            booleanResultMessage.setMesg("删除成功");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
        }
        return booleanResultMessage;
    }

    /**
     * 新增逻辑节点
     */
    @ResponseBody
    @PostMapping("/addOrUpdateLogic")
    public ResultMessage<Boolean> addOrUpdateLogic(@RequestBody LogicNode logicNode){
        ResultMessage<Boolean> resultMessage = new ResultMessage();
        try {
            if (logicNode.getLogic_id()!= null && !"".equals(logicNode.getLogic_id().trim())) {
                //修改
                logicService.updateById(logicNode);
                resultMessage.setValue(true);
                resultMessage.setMesg("修改成功");
                resultMessage.setStatuscode("200");
            } else {
                //新增
                logicNode.setLogic_id(UUID.randomUUID().toString().replaceAll("-", ""));
                logicService.insertEntity(logicNode);
                resultMessage.setValue(true);
                resultMessage.setMesg("新增成功");
                resultMessage.setStatuscode("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(false);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
        }
        return resultMessage;
    }

    /**
     * 查询逻辑节点
     */
    @ResponseBody
    @PostMapping("/queryLogic")
    public ResultMessage<PageInfo<LogicNode>> queryLogic(@RequestBody LogicNodeRequest logicNodeRequest){
        ResultMessage<PageInfo<LogicNode>> logicPage = new ResultMessage<>();
        try {
            PageHelper.startPage(logicNodeRequest.getPageNum(), logicNodeRequest.getPageSize());
            List<LogicNode> channelList = logicService.queryByEntity(logicNodeRequest.getLogicNode());
            PageInfo<LogicNode> result = new PageInfo(channelList);
            logicPage.setValue(result);
            logicPage.setStatuscode("200");
            logicPage.setMesg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            logicPage.setValue(null);
            logicPage.setStatuscode("501");
            logicPage.setMesg("服务端错误：" + e.toString());
        }
        return logicPage;
    }

    /**
     * 通过id删除逻辑节点
     */
    @ResponseBody
    @PostMapping("/deleteLogicById")
    public ResultMessage<Boolean> deleteLogicById(@RequestBody LogicNode logicNode){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage();
        try {
            logicService.deleteById(logicNode.getLogic_id());
            booleanResultMessage.setMesg("删除成功");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
        }
        return booleanResultMessage;
    }


}
