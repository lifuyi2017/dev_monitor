package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.annotation.PassToken;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.login.LoginResult;
import com.lifuyi.dev_monitor.model.user.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
@Api(description = "登录登出")
public class LoginController {


//    @PassToken
//    @RequestMapping(value = "/loginBackend", method = RequestMethod.POST)
//    public ResultMessage<LoginResult> login(@RequestBody User user) {
//
//    }

}
