package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.dev.DevType;

import java.util.List;

public interface DevMapper {
    List<DevType> getType(DevType devType);
}
