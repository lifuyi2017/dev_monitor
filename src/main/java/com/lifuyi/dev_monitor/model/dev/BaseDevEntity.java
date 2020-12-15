package com.lifuyi.dev_monitor.model.dev;

import lombok.Data;

@Data
public class BaseDevEntity {

    private String dev_code;
    private Integer dev_type_id;
    private String dev_name;
    private String manager;
    private String service_enterprise_id;
    private String produce_enterprise_id;
    private String third_enterprise_id;
    private String install_instructions;
    private String pic_url;

    private Object object;


    public BaseDevEntity(String dev_code, Integer dev_type_id, String dev_name, String manager, String service_enterprise_id, String produce_enterprise_id, String third_enterprise_id, String install_instructions, String pic_url, Object object) {
        this.dev_code = dev_code;
        this.dev_type_id = dev_type_id;
        this.dev_name = dev_name;
        this.manager = manager;
        this.service_enterprise_id = service_enterprise_id;
        this.produce_enterprise_id = produce_enterprise_id;
        this.third_enterprise_id = third_enterprise_id;
        this.install_instructions = install_instructions;
        this.pic_url = pic_url;
        this.object = object;
    }
}
