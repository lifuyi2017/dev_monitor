package com.lifuyi.dev_monitor.dao;

import java.util.List;

public interface ChannelMapper {

    List<String> getChannelCodeByPhysicalId(String physicalId);

}
