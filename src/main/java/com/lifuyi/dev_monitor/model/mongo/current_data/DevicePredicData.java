package com.lifuyi.dev_monitor.model.mongo.current_data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * https://blog.csdn.net/yanpenglei/article/details/79261875
 */
@Document(collection = "device_predic_data")
public class DevicePredicData {
    @Id
    private Long id;
    private String company_id;
    private String company_name;
    private String factory_id;
    private String factory_name;
    private String workshop_id;
    private String workshop_name;
    private String device_id;
    private String device_name;
    private Long time_min;
    private Long time_max;


}
