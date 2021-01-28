package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.UserMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.user.Req.UserReq;
import com.lifuyi.dev_monitor.model.user.Resp.UserResp;
import com.lifuyi.dev_monitor.model.user.User;
import com.lifuyi.dev_monitor.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultMessage<Boolean> insertOrUpdateUser(User user) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        User name = userMapper.getByUserName(user.getUser_name());
        if ((StringUtils.isBlank(user.getId()) && name != null) ||
                (!StringUtils.isBlank(user.getId()) && name != null && !user.getId().equals(name.getId()))) {
            return new ResultMessage<Boolean>("401", "账号名重复", false);
        }
        User phone = userMapper.getByUserPhone(user.getUser_name());
        if ((StringUtils.isBlank(user.getId()) && phone != null) ||
                (!StringUtils.isBlank(user.getId()) && phone != null && !user.getId().equals(phone.getId()))) {
            return new ResultMessage<Boolean>("401", "电话号码重复", false);
        }
        if (StringUtils.isBlank(user.getId())) {
            user.setId(id);
        }
        user.setRegister_time(new Date());
        user.setUpdate_time(new Date());
        userMapper.insertOrUpdateUser(user);
        return new ResultMessage<Boolean>("200", "操作成功", true);
    }

    @Override
    public ResultMessage<PageInfo<UserResp>> getUserByPage(UserReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<UserResp> list = userMapper.getUserByEntity(req.getUser());
        PageInfo<UserResp> userRespPageInfo = new PageInfo<>(list);
        return new ResultMessage<PageInfo<UserResp>>("200","查询成功",userRespPageInfo);
    }

    @Override
    public ResultMessage<Boolean> deleteById(String id) {
        userMapper.deleteById(id);
        return new ResultMessage<Boolean>("200","删除成功",true);
    }

    @Override
    public List<User> getUsersByUser(User user) {
        return userMapper.getUsersByUser(user);
    }

}
