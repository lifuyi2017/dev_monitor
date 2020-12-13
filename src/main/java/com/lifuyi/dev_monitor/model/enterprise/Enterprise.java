package com.lifuyi.dev_monitor.model.enterprise;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Enterprise {
    private String enterprise_id;
    private String enterprise_code;
    private String enterprise_name;
    private Integer enterprise_type_id;
    private Date update_time;
}
