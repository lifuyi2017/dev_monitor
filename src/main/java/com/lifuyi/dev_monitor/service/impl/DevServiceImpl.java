package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.DevMapper;
import com.lifuyi.dev_monitor.model.dev.DevType;
import com.lifuyi.dev_monitor.service.DevService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("devService")
public class DevServiceImpl implements DevService {

    @Resource
    private DevMapper devMapper;

    @Override
    public List<DevType> getType() {
        DevType devType = new DevType();
        return devMapper.getType(devType);
    }
}
