package com.lifuyi.dev_monitor.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@CrossOrigin
@Api(description = "用户管理")
public class UserController {
}
