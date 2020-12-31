package com.lifuyi.dev_monitor.model.channel.resp;

import lombok.Data;

import java.util.List;

@Data
public class PhysicalChannelResp {

    private String physical_name;
    private String codes;
    private String physical_id;

    public PhysicalChannelResp(String physical_name, String codes, String physical_id) {
        this.physical_name = physical_name;
        this.codes = codes;
        this.physical_id = physical_id;
    }

    public PhysicalChannelResp() {
    }
}
