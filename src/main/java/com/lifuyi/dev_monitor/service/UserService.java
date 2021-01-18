package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.user.Req.UserReq;
import com.lifuyi.dev_monitor.model.user.Resp.UserResp;
import com.lifuyi.dev_monitor.model.user.User;

import java.util.List;

public interface UserService {
    ResultMessage<Boolean> insertOrUpdateUser(User user);

    ResultMessage<PageInfo<UserResp>> getUserByPage(UserReq req);

}
