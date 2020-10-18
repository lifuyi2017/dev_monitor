package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.annotation.UserLoginToken;
import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.dao.NetworkMapper;
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
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private MeasureMapper measureMapper;

    /**
     * 新增或者更新网关
     */
    @ResponseBody
    @PostMapping("/addOrUpdateNetWork")
    @UserLoginToken
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
    @UserLoginToken
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
            List<Enterprise> enterByEntity = enterpriseService.getEnterByEntity(enterprise);
            if(enterByEntity!=null && enterByEntity.size()>0){
                network1.setEnterprise_name(enterByEntity.get(0).getEnterprise_name());
            }else {
                networkService.deleteByEnterpriseId(network1.getEnterprise_id());
            }
        }
        return networkList;
    }

    /**
     * 删除网关，通过id
     */
    @ResponseBody
    @PostMapping("/deleteNetWorkById")
    @UserLoginToken
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
    @UserLoginToken
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
    @UserLoginToken
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
            List<Network> networks = networkService.queryByEntity(network);
            if(networks!=null && networks.size()>0){
                measure1.setNetwork_name(networks.get(0).getNetwork_name());
            }else {
                measureService.deleteByNetworkId(measure1.getNetwork_id());
            }
            Enterprise enterprise = new Enterprise();
            enterprise.setEnterprise_id(measure1.getEnterprise_id());
            List<Enterprise> enterByEntity = enterpriseService.getEnterByEntity(enterprise);
            if(enterByEntity!=null && enterByEntity.size()>0){
                measure1.setEnterprise_name(enterByEntity.get(0).getEnterprise_name());
            }else {
                measureMapper.deleteByEnterpriseId(measure1.getEnterprise_id());
            }
        }
        return measureList;
    }

    /**
     * 删除物理节点，通过id
     */
    @ResponseBody
    @PostMapping("/deleteMeasureById")
    @UserLoginToken
    public ResultMessage<Boolean> deleteMeasureById(@RequestBody Measure measure) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            measureService.deleteById(measure.getMeasure_id());
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
    @UserLoginToken
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
    @UserLoginToken
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
            List<Enterprise> enterByEntity = enterpriseService.getEnterByEntity(enterprise);
            if(enterByEntity!=null && enterByEntity.size()>0){
                ch.setEnterprise_name(enterByEntity.get(0).getEnterprise_name());
            }else {
                channelMapper.deleteByEnterpriseId(ch.getEnterprise_id());
            }
            Measure measure = new Measure();
            measure.setMeasure_id(ch.getMeasure_id());
            List<Measure> measureList = measureService.queryByEntity(measure);
            if(measureList!=null && measureList.size()>0){
                ch.setMeasure_name(measureList.get(0).getMeasure_name());
            }
        }
        return channelList;
    }

    /**
     * 删除通道，通过id
     */
    @ResponseBody
    @PostMapping("/deleteChannelById")
    @UserLoginToken
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
    @UserLoginToken
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
    @UserLoginToken
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
    @UserLoginToken
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
