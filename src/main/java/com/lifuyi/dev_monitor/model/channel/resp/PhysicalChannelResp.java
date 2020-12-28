package com.lifuyi.dev_monitor.model.channel.resp;

import lombok.Data;

import java.util.List;

@Data
public class PhysicalChannelResp {

    private String physical_name;
    private String codes;

    public PhysicalChannelResp(String physical_name, String codes) {
        this.physical_name = physical_name;
        this.codes = codes;
    }

    public PhysicalChannelResp() {
    }
}
