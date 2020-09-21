package com.winterchen.model;

import java.io.Serializable;

public class UserRequest implements Serializable {
    private static final long serialVersionUID = 324529476919894582L;

    private User user;
    private Integer pageNum;
    private Integer pageSize;

    public UserRequest() {
    }

    public UserRequest(User user, Integer pageNum, Integer pageSize) {
        this.user = user;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
