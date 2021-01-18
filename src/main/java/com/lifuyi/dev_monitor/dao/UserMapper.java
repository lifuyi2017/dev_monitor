package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.user.Resp.UserResp;
import com.lifuyi.dev_monitor.model.user.User;

import java.util.List;

public interface UserMapper {
    User getByUserName(String user_name);

    User getByUserPhone(String user_name);

    void insertOrUpdateUser(User user);

    List<UserResp> getUserByEntity(User user);

}
