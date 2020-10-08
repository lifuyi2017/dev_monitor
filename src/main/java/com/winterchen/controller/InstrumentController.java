package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.model.*;
import com.winterchen.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/instrument")
@CrossOrigin
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
    @Autowired
    private EnterpriseService enterpriseService;

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
            PageInfo result;
            if(networkRequest.getPageNum()!=null && networkRequest.getPageSize()!=null){
                PageHelper.startPage(networkRequest.getPageNum(), networkRequest.getPageSize());
                List<Network> networkList=getNetworkList(networkRequest.getNetwork());
                result = new PageInfo(networkList);
            }else {
                result= new PageInfo();
                List<Network> networkList=getNetworkList(networkRequest.getNetwork());
                result.setList(networkList);
            }
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

    private List<Network> getNetworkList(Network network) throws Exception {
        List<Network> networkList = networkService.queryByEntity(network);
        for(Network network1:networkList){
            Enterprise enterprise = new Enterprise();
            enterprise.setEnterprise_id(network1.getEnterprise_id());
            network1.setEnterprise_name(enterpriseService.getEnterByEntity(enterprise).get(0).getEnterprise_name());
        }
        return networkList;
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
            PageInfo result;
            if(measureRequest.getPageNum()!=null && measureRequest.getPageSize()!=null){
                PageHelper.startPage(measureRequest.getPageNum(), measureRequest.getPageSize());
                List<Measure> measureList = getMeasureList(measureRequest.getMeasure());
                result = new PageInfo(measureList);
            }else {
                result = new PageInfo();
                List<Measure> measureList = getMeasureList(measureRequest.getMeasure());
                result.setList(measureList);
            }
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

    private List<Measure> getMeasureList(Measure measure) throws Exception {
        List<Measure> measureList = measureService.queryByEntity(measure);
        for(Measure measure1:measureList){
            Network network = new Network();
            network.setNetwork_id(measure1.getNetwork_id());
            measure1.setNetwork_name(networkService.queryByEntity(network).get(0).getNetwork_name());
            Enterprise enterprise = new Enterprise();
            enterprise.setEnterprise_id(measure1.getEnterprise_id());
            measure1.setEnterprise_name(enterpriseService.getEnterByEntity(enterprise).get(0).getEnterprise_name());
        }
        return measureList;
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
            PageInfo result;
            if(channelRequest.getPageNum()!=null && channelRequest.getPageSize()!=null){
                PageHelper.startPage(channelRequest.getPageNum(), channelRequest.getPageSize());
                List<Channel> channelList = getChannelList(channelRequest.getChannel());
                result = new PageInfo(channelList);
            }else {
                result= new PageInfo();
                List<Channel> channelList = getChannelList(channelRequest.getChannel());
                result.setList(channelList);
            }
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

    private List<Channel> getChannelList(Channel channel) throws Exception {
        List<Channel> channelList = channelService.queryByEntity(channel);
        for(Channel ch:channelList){
            Enterprise enterprise = new Enterprise();
            enterprise.setEnterprise_id(ch.getEnterprise_id());
            ch.setEnterprise_name(enterpriseService.getEnterByEntity(enterprise).get(0).getEnterprise_name());
            Measure measure = new Measure();
            measure.setMeasure_id(ch.getMeasure_id());
            ch.setMeasure_name(measureService.queryByEntity(measure).get(0).getMeasure_name());
        }
        return channelList;
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
            PageInfo result;
            if(logicNodeRequest.getPageNum()!=null && logicNodeRequest.getPageSize()!=null){
                PageHelper.startPage(logicNodeRequest.getPageNum(), logicNodeRequest.getPageSize());
                List<LogicNode> logicNodeList = logicService.queryByEntity(logicNodeRequest.getLogicNode());
                result = new PageInfo(logicNodeList);
            }else {
                result =new PageInfo();
                List<LogicNode> logicNodeList = logicService.queryByEntity(logicNodeRequest.getLogicNode());
                result.setList(logicNodeList);
            }
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
