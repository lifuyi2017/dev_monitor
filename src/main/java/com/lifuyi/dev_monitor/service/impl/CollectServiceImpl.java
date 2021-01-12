package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.ChannelMapper;
import com.lifuyi.dev_monitor.dao.CollectMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.collect.req.CollectConfigQueryReq;
import com.lifuyi.dev_monitor.model.collect.req.StartOrStopCollect;
import com.lifuyi.dev_monitor.model.collect.resp.CollectConfigResp;
import com.lifuyi.dev_monitor.model.mqtt.CollectConfig;
import com.lifuyi.dev_monitor.service.CollectService;
import com.lifuyi.dev_monitor.util.MqttUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;
    @Resource
    private ChannelMapper channelMapper;


    @Override
    public ResultMessage<List<String>> getUnBindingCollectChannelCode(String typeId, String physicalId) {
        return new ResultMessage<List<String>>("200","查询成功",collectMapper.getUnBindingCollectChannelCode(typeId,physicalId));
    }

    @Override
    public ResultMessage<Boolean> insertOrUpdateCollectConfig(CollectDevConfig config) {
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            //判断通道是否被占用
            String channel_code = config.getChannel_code();
            List<String> codes=null;
            if(!StringUtils.isBlank(channel_code)){
                codes= Arrays.asList(channel_code.split(","));
                Integer num=collectMapper.countNumCollectChannel(config.getPhysical_id(),codes);
                if(num>0){
                    return new ResultMessage<Boolean>("401","所选择通道已被占用",false);
                }
            }
            if(StringUtils.isBlank(config.getId())){
                config.setId(id);
                config.setState("0");
            }else {
                //停止原有采集
                CollectConfig con=collectMapper.getConfigById(config.getId());
                con.setState("0");
                MqttUtil.putToMqtt(con);
            }
            //更新采集参数配置表
            collectMapper.insertOrUpdateCollectConfig(config);
            //更新通道绑定表
            if(codes!=null){
                channelMapper.BindingCollectAndChannel(config.getPhysical_id(),codes,config.getId());
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("501","error:"+e.getMessage(),false);
        }
    }

    @Override
    public ResultMessage<List<CollectConfigResp>> getCollectConfigByDevGroup(CollectConfigQueryReq req) {
        List<CollectConfigResp> configList=collectMapper.getCollectConfigByDevGroup(req);
        for(CollectConfigResp config:configList){
            ChannelParameter parameter=channelMapper.getParameterById(config.getChannel_type_id());
            config.setChannelParameter(parameter);
        }
        return new ResultMessage<List<CollectConfigResp>>("200","查询成功",configList);
    }

    @Override
    public ResultMessage<Boolean> startOrStopCollect(StartOrStopCollect startOrStopCollect) {
        try {
            CollectConfig con=collectMapper.getConfigById(startOrStopCollect.getId());
            con.setState(startOrStopCollect.getState());
            MqttUtil.putToMqtt(con);
            CollectDevConfig collectDevConfig=collectMapper.getCollectConfigById(startOrStopCollect.getId());
            collectDevConfig.setState(startOrStopCollect.getState());
            collectMapper.insertOrUpdateCollectConfig(collectDevConfig);
            return new ResultMessage<Boolean>("200","操作成功",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("501","error:"+e.getMessage(),true);
        }


    }
}
