package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.ChannelMapper;
import com.lifuyi.dev_monitor.dao.CollectMapper;
import com.lifuyi.dev_monitor.dao.PhysicalMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.channel.ChannelParameter;
import com.lifuyi.dev_monitor.model.channel.req.ChannelParameterReq;
import com.lifuyi.dev_monitor.model.channel.req.ChannelSaveReq;
import com.lifuyi.dev_monitor.model.channel.resp.ChannelResp;
import com.lifuyi.dev_monitor.model.channel.resp.PhysicalChannelResp;
import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.mqtt.CollectConfig;
import com.lifuyi.dev_monitor.service.ChannelService;
import com.lifuyi.dev_monitor.service.CollectService;
import com.lifuyi.dev_monitor.util.MqttUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CollectService collectService;

    @Override
    public List<String> getChannelCode(String physicalId) {
        return channelMapper.getChannelCodeByPhysicalId(physicalId);
    }

    @Override
    @Transactional
    public ResultMessage<String> insertOrUpdateChannelParameter(List<ChannelSaveReq> reqs) {
        try {
            for (ChannelSaveReq channelSaveReq : reqs) {
//                String channel_type_id = channelMapper.getMaxChannelTypeId(channelSaveReq.getPhysical_id(), channelSaveReq.getCodes());
//                if (!StringUtils.isBlank(channel_type_id)) {
//                    return new ResultMessage<String>("401", "修改通道组时不能修改物理节点和通道", channel_type_id);
//                }
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                ChannelParameter channelParameter = channelSaveReq.getChannelParameter();
                if (!StringUtils.isBlank(channelParameter.getId())) {
                    //修改时不能修改绑定关系
//            channelMapper.clearBindingByTypeId(channelParameter.getId());
//            PhysicalChannelResp resp=channelMapper.getPhysicalChannelResp(channelSaveReq.getChannelParameter().getId());
                    PhysicalChannelResp resp=channelMapper.getPhysicalChannelResp(channelParameter.getId());
                    if( !(resp.getPhysical_id().equals(channelSaveReq.getPhysical_id())
                    && MqttUtil.equalLists(channelSaveReq.getCodes(),Arrays.asList(resp.getCodes().split(","))))){
                        return new ResultMessage<String>("401", "修改通道组时不能修改物理节点和通道", "error");
                    }
                    CollectDevConfig collectDevConfig = new CollectDevConfig();
                    collectDevConfig.setChannel_type_id(channelParameter.getId());
                    collectDevConfig.setState("1");
                    List<CollectDevConfig> collectConfigByConfig = collectMapper.getCollectConfigByConfig(collectDevConfig);
                    if (collectConfigByConfig != null && collectConfigByConfig.size() > 0) {
                        for (CollectDevConfig config : collectConfigByConfig) {
                            CollectConfig con = collectMapper.getConfigById(config.getId());
                            con.setState("0");
                            MqttUtil.putToMqtt(con);
                        }
                    }
                    channelMapper.insertOrUpdateChannelParameter(channelParameter);
                    if (collectConfigByConfig != null && collectConfigByConfig.size() > 0) {
                        for (CollectDevConfig config : collectConfigByConfig) {
                            CollectConfig con = collectMapper.getConfigById(config.getId());
                            con.setState("1");
                            MqttUtil.putToMqtt(con);
                        }
                    }
                } else {
                    channelParameter.setId(id);
                    channelMapper.BindingParameterAndChannel(channelParameter.getId(), channelSaveReq.getPhysical_id(), channelSaveReq.getCodes());
                    channelMapper.insertOrUpdateChannelParameter(channelParameter);
                }
            }
            return new ResultMessage<String>("200", "操作成功", "success");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<String>("500", e.getMessage(), "error");
        }



    }

    //查询分页列表
    @Override
    public ResultMessage<PageInfo<ChannelResp>> getChannelParameterPages(ChannelParameterReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<ChannelResp> parameterList = channelMapper.getChannelParameterPages(req.getParameter());
        PageInfo<ChannelResp> channelRespPageInfo = new PageInfo(parameterList);
        for (ChannelResp channelResp : parameterList) {
            PhysicalChannelResp resp = channelMapper.getPhysicalChannelResp(channelResp.getId());
            channelResp.setPhysical_id(resp.getPhysical_id());
            channelResp.setPhysical_name(resp.getPhysical_name());
            if (!StringUtils.isBlank(resp.getCodes())) {
                channelResp.setCodes(Arrays.asList(resp.getCodes().split(",")));
            }
        }
        channelRespPageInfo.setList(parameterList);
        return new ResultMessage<PageInfo<ChannelResp>>("200", "查询成功", channelRespPageInfo);
    }

    @Override
    public void deleteById(String id) {
        try {
            channelMapper.clearBindingByTypeId(id);
            List<CollectDevConfig> configList = collectMapper.getConfigByChannelTypeId(id);
            if (configList != null && configList.size() > 0) {
                for (CollectDevConfig config : configList) {
                    collectService.deleteById(config.getId());
                }
            }
            channelMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultMessage<List<ChannelParameter>> getChannelParameter(String id) {
        List<ChannelParameter> parameterList = channelMapper.getUnBingdingChannelParameterByPhyId(id);
        return new ResultMessage<List<ChannelParameter>>("200", "success", parameterList);
    }

}
