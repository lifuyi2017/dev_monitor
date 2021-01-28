package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.annotation.FrontPassToken;
import com.lifuyi.dev_monitor.annotation.FrontUserLoginToken;
import com.lifuyi.dev_monitor.annotation.PassToken;
import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.login.LoginResult;
import com.lifuyi.dev_monitor.model.user.User;
import com.lifuyi.dev_monitor.service.UserService;
import com.lifuyi.dev_monitor.util.TokenCache;
import com.lifuyi.dev_monitor.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin
@Api(description = "登录登出")
public class LoginController {

    @Resource
    private UserService userService;

    @PassToken
    @RequestMapping(value = "/loginBackend", method = RequestMethod.POST)
    @ApiOperation(value = "后台系统登陆",  notes = "")
    public ResultMessage<LoginResult> login(@RequestBody User user) {
        ResultMessage<LoginResult> resultMessage = new ResultMessage();
        try {
            List<User> usersByUser = userService.getUsersByUser(user);
            if (usersByUser == null || usersByUser.size()==0) {
                resultMessage.setValue(null);
                resultMessage.setStatuscode("401");
                resultMessage.setMesg("登录失败，用户不存在或者密码错误");
            } else {
                User user1 = usersByUser.get(0);
                if("0".equals(user1.getUser_status())){
                    resultMessage.setValue(null);
                    resultMessage.setStatuscode("402");
                    resultMessage.setMesg("该用户已被禁用");
                    return resultMessage;
                }
                String token = TokenUtil.getToken(user1);
                LoginResult loginResult = new LoginResult(user1, token);
                resultMessage.setValue(loginResult);
                resultMessage.setMesg("登陆成功");
                resultMessage.setStatuscode("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setStatuscode("501");
            resultMessage.setMesg("系统错误:" + e.toString());
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/loginBackendOut", method = RequestMethod.POST)
    @UserLoginToken
    @ApiOperation(value = "后台系统登出",  notes = "")
    public ResultMessage<Boolean> loginOut(@RequestBody User user, HttpServletRequest request){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String token=request.getHeader("token");
            TokenCache.backendCache.put(user.getId(),token);
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("系统错误:" + e.toString());
        }
        return booleanResultMessage;
    }



    @FrontPassToken
    @RequestMapping(value = "/loginFront", method = RequestMethod.POST)
    @ApiOperation(value = "前台系统登陆",  notes = "")
    public ResultMessage<LoginResult> loginFront(@RequestBody User user) {
        ResultMessage<LoginResult> resultMessage = new ResultMessage();
        try {
            List<User> usersByUser = userService.getUsersByUser(user);
            if (usersByUser == null || usersByUser.size()==0) {
                resultMessage.setValue(null);
                resultMessage.setStatuscode("401");
                resultMessage.setMesg("登录失败，用户不存在或者密码错误");
            } else {
                User user1 = usersByUser.get(0);
                if("0".equals(user1.getUser_status())){
                    resultMessage.setValue(null);
                    resultMessage.setStatuscode("402");
                    resultMessage.setMesg("该用户已被禁用");
                    return resultMessage;
                }
                String token = TokenUtil.getToken(user1);
                LoginResult loginResult = new LoginResult(user1, token);
                resultMessage.setValue(loginResult);
                resultMessage.setMesg("登陆成功");
                resultMessage.setStatuscode("200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setStatuscode("501");
            resultMessage.setMesg("系统错误:" + e.toString());
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/loginFrontOut", method = RequestMethod.POST)
    @FrontUserLoginToken
    @ApiOperation(value = "前台系统登出",  notes = "")
    public ResultMessage<Boolean> loginFrontOut(@RequestBody User user, HttpServletRequest request){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String token=request.getHeader("token");
            TokenCache.frontCache.put(user.getId(),token);
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("系统错误:" + e.toString());
        }
        return booleanResultMessage;
    }


}
