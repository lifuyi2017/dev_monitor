package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.ChannelMapper;
import com.lifuyi.dev_monitor.dao.CollectMapper;
import com.lifuyi.dev_monitor.dao.LogicMapper;
import com.lifuyi.dev_monitor.dao.PhysicalMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.CollectDevConfig;
import com.lifuyi.dev_monitor.model.physical.Physical;
import com.lifuyi.dev_monitor.model.physical.PhysicalChannelBinding;
import com.lifuyi.dev_monitor.model.physical.req.PhysicalReq;
import com.lifuyi.dev_monitor.model.physical.resp.PhysicalResp;
import com.lifuyi.dev_monitor.service.ChannelService;
import com.lifuyi.dev_monitor.service.CollectService;
import com.lifuyi.dev_monitor.service.PhysicalService;
import com.lifuyi.dev_monitor.service.WorkShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;
import java.util.UUID;

@Service("physicalService")
public class PhysicalServiceImpl implements PhysicalService {

    @Resource
    private PhysicalMapper physicalMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CollectService collectService;
    @Resource
    private LogicMapper logicMapper;
    @Resource
    private ChannelService channelService;


    @Override
    public ResultMessage<Boolean> addOrUpdatePhysical(Physical physical) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        Physical codeP = physicalMapper.getByCode(physical.getCode());
        if ((StringUtils.isBlank(physical.getId()) && codeP != null) ||
                (!StringUtils.isBlank(physical.getId()) && codeP != null && !codeP.getId().equals(physical.getId()))) {
            return new ResultMessage<Boolean>("401", "物理节点编码重复", false);
        }
        if (StringUtils.isBlank(physical.getId())) {
            physical.setId(id);
            //新增通道
            Format f1 = new DecimalFormat("00");
            for (int i = 0; i < physical.getNum(); i++) {
                String format = f1.format(i);
                PhysicalChannelBinding physicalChannel =
                        new PhysicalChannelBinding(id + "=" + format, id, format, null, null);
                physicalMapper.insertOrUpdatePhysicalChannelBinding(physicalChannel);
            }
        } else {
            Physical query = new Physical();
            query.setId(physical.getId());
            PhysicalResp physicalResp = physicalMapper.getPageByEntity(query).get(0);
            if (physical.getNum() != null && ((int) physicalResp.getNum()) != ((int) physical.getNum())) {
                return new ResultMessage<Boolean>("401", "通道数不可修改", false);
            }
        }
        physicalMapper.addOrUpdatePhysical(physical);
        return new ResultMessage<Boolean>("200", physical.getId(), true);
    }

    @Override
    public ResultMessage<PageInfo<PhysicalResp>> getPhysicalPages(PhysicalReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<PhysicalResp> respList = physicalMapper.getPageByEntity(req.getPhysical());
        PageInfo<PhysicalResp> physicalRespPageInfo = new PageInfo<>(respList);
        return new ResultMessage<PageInfo<PhysicalResp>>("200", "查询成功", physicalRespPageInfo);
    }

    @Override
    public ResultMessage<List<PhysicalResp>> getPhysicalList(Physical physical) {
        return new ResultMessage<List<PhysicalResp>>("200", "查询成功", physicalMapper.getPageByEntity(physical));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        try {
            //collect_dev_config
            List<CollectDevConfig> configList = collectMapper.getCollectConfigByPhysicalId(id);
            for (CollectDevConfig config : configList) {
                collectService.deleteById(config.getId());
            }
            //logic_relation
            logicMapper.deleteRelationByPhysicalId(id);
            //physical_channel_binding
            List<String> types = physicalMapper.getChannelTypeByPhysicalId(id);
            for (String str : types) {
                channelService.deleteById(str);
            }
            physicalMapper.deletePhysicalBingdingByPhysicalId(id);
            physicalMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
