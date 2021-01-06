package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.CollectMapper;
import com.lifuyi.dev_monitor.dao.WorkShopMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.service.CollectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;


    @Override
    public ResultMessage<List<String>> getUnBindingCollectChannelCode(String typeId, String physicalId) {
        return new ResultMessage<List<String>>("200","查询成功",collectMapper.getUnBindingCollectChannelCode(typeId,physicalId));
    }
}
