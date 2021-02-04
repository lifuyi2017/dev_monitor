package com.lifuyi.dev_monitor.mongodao;

import com.lifuyi.dev_monitor.model.mongo.current_data.DevicePredicData;

public interface DevicePredicDataDao {

    DevicePredicData getDevHealthyByDevId(String devId);

}
