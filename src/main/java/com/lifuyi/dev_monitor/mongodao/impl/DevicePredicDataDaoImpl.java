package com.lifuyi.dev_monitor.mongodao.impl;

import com.lifuyi.dev_monitor.model.mongo.current_data.DevicePredicData;
import com.lifuyi.dev_monitor.mongodao.DevicePredicDataDao;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DevicePredicDataDaoImpl implements DevicePredicDataDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public DevicePredicData getDevHealthyByDevId(String devId) {
        Query query = new Query(Criteria.where("device_id").is(devId));
//        query.with(new Sort(Sort.Direction.DESC,"time_max"));
        DevicePredicData demoEntity = mongoTemplate.findOne(query, DevicePredicData.class);
        return demoEntity;
    }

}
