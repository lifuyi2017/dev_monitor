package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.user.Req.UserReq;
import com.lifuyi.dev_monitor.model.user.Resp.UserResp;
import com.lifuyi.dev_monitor.model.user.User;
import com.lifuyi.dev_monitor.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
@Api(description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/insertOrUpdateUser")
    @ApiOperation(value = "插入或者更新用户，不传id字段是插入，传是更新", notes = "返回的mesg是id")
    @UserLoginToken
    public ResultMessage<Boolean> insertOrUpdateUser(@RequestBody User user){
        return userService.insertOrUpdateUser(user);
    }

    @PostMapping(value = "/getUserByPage")
    @ApiOperation(value = "获取用户列表，带分页", notes = "返回的mesg是id")
    @UserLoginToken
    public ResultMessage<PageInfo<UserResp>> getUserByPage(@RequestBody UserReq req){
        return userService.getUserByPage(req);
    }

    @PostMapping(value = "/deleteUserById")
    @ApiOperation(value = "通过id删除用户", notes = "")
    @UserLoginToken
    public ResultMessage<Boolean> deleteUserById(@RequestParam("id") List<String> id){
        try {
            for(String s:id){
                userService.deleteById(s);
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }


}
