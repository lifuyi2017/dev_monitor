package com.lifuyi.dev_monitor.model.mongo.current_data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * https://blog.csdn.net/yanpenglei/article/details/79261875
 */
@Document(collection = "device_predic_data")
@Data
public class DevicePredicData implements Serializable {
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
    private Integer number_Of_phase;
    @ApiModelProperty(value = "0正常，1警告，2异常" ,position = 2,required = true)
    private String device_warn_result;
    private Integer device_warn_score;
    private List<List<Double>> point_coordinate;

}
