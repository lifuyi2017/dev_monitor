package com.lifuyi.dev_monitor.model.dev;

import lombok.Data;

@Data
public class DevType {

    private Integer id;
    private String type_name;

    public DevType(Integer id, String type_name) {
        this.id = id;
        this.type_name = type_name;
    }
}
