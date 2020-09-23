package com.winterchen.model;

import java.io.Serializable;

public class ChannelRequest implements Serializable {
    private static final long serialVersionUID = 8159242796887429376L;

    private Channel channel;
    private Integer pageNum;
    private Integer pageSize;

    public ChannelRequest() {
    }

    public ChannelRequest(Channel channel, Integer pageNum, Integer pageSize) {
        this.channel = channel;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
