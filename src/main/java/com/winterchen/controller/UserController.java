package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.annotation.PassToken;
import com.winterchen.annotation.UserLoginToken;
import com.winterchen.model.*;
import com.winterchen.service.user.EnterpriseService;
import com.winterchen.service.user.UserService;
import com.winterchen.util.EntityUtil;
import com.winterchen.util.TokenCache;
import com.winterchen.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EnterpriseService enterpriseService;

/*    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        return userService.addUser(user);
    }*/

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    @UserLoginToken
    public ResultMessage<Boolean> loginOut(@RequestBody User user, HttpServletRequest request){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String token=request.getHeader("token");
            TokenCache.cache.put(user.getUser_id(),token);
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

    @PassToken
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    @PostMapping("/addOrUpdateUser")
    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdateUser(@RequestBody User user) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String s = EntityUtil.checkObjectField(user);
            if(!"true".equals(s)){
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg(s);
                booleanResultMessage.setValue(false);
                return booleanResultMessage;
            }
            if (user.getUser_id() != null) {
                User userQuery = new User();
                userQuery.setUser_id(user.getUser_id());
                List<User> userList = userService.getUsersByUserNoPage(userQuery);
                if (userList == null || userList.size() == 0) {
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setMesg("没有这个用户");
                    return booleanResultMessage;
                }
                if(!userList.get(0).getUser_name().equals(user.getUser_name())){
                    User userName = new User();
                    userName.setUser_name(user.getUser_name());
                    List<User> usersByUserNoPage = userService.getUsersByUserNoPage(userName);
                    if(usersByUserNoPage.size()>0){
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("401");
                        booleanResultMessage.setMesg("用户名已经被占用");
                        return booleanResultMessage;
                    }
                }
                if(!userList.get(0).getUser_phone().equals(user.getUser_phone())){
                    User userPhone = new User();
                    userPhone.setUser_phone(user.getUser_phone());
                    List<User> usersByUserNoPage = userService.getUsersByUserNoPage(userPhone);
                    if(usersByUserNoPage.size()>0){
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("401");
                        booleanResultMessage.setMesg("电话号码已经被占用");
                        return booleanResultMessage;
                    }
                }
                userService.updateById(user);
            } else {
                //用户名和手机号
                User userName = new User();
                userName.setUser_name(user.getUser_name());
                List<User> userList = userService.getUsersByUserNoPage(userName);
                if(userList!=null && userList.size()>0){
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setStatuscode("402");
                    booleanResultMessage.setMesg("已经被占用的用户名");
                    return booleanResultMessage;
                }
                if(user.getUser_phone()!=null && !"".equals(user.getUser_phone())){
                    User userPhone = new User();
                    userPhone.setUser_phone(user.getUser_phone());
                    List<User> phoneList = userService.getUsersByUserNoPage(userPhone);
                    if(phoneList!=null && phoneList.size()>0){
                        booleanResultMessage.setValue(false);
                        booleanResultMessage.setStatuscode("403");
                        booleanResultMessage.setMesg("已经被占用的手机号");
                        return booleanResultMessage;
                    }
                }
                user.setUser_status("1");
                userService.addUser(user);
            }
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("操作成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("系统错误:" + e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/queryUser")
    @UserLoginToken
    public ResultMessage<PageInfo<User>> queryUser(@RequestBody UserRequest userRequest) {
        ResultMessage<PageInfo<User>> listResultMessage = new ResultMessage<>();
        try {
            PageInfo result;
            if (userRequest.getPageNum() != null && userRequest.getPageSize() != null) {
                PageHelper.startPage(userRequest.getPageNum(), userRequest.getPageSize());
                List<User> usersByUser = userService.getUsersByUser(userRequest.getUser());
                for (User user : usersByUser) {
                    Enterprise enterprise = new Enterprise();
                    enterprise.setEnterprise_id(user.getEnterprise_id());
                    user.setEnterprise_name(enterpriseService.getEnterByEntity(enterprise).get(0).getEnterprise_name());
                }
                result = new PageInfo(usersByUser);
            } else {
                result = new PageInfo();
                List<User> usersByUser = userService.getUsersByUser(userRequest.getUser());
                for (User user : usersByUser) {
                    Enterprise enterprise = new Enterprise();
                    enterprise.setEnterprise_id(user.getEnterprise_id());
                    user.setEnterprise_name(enterpriseService.getEnterByEntity(enterprise).get(0).getEnterprise_name());
                }
                result.setList(usersByUser);
            }
            listResultMessage.setValue(result);
            listResultMessage.setStatuscode("200");
            listResultMessage.setMesg("查询成功");
            return listResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            listResultMessage.setValue(null);
            listResultMessage.setStatuscode("501");
            listResultMessage.setMesg("服务器错误:" + e.toString());
            return listResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/deleteByIds")
    @UserLoginToken
    public ResultMessage<Boolean> deleteByIds(@RequestBody OptUser optUser) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            userService.deleteByIds(optUser.getIds());
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("删除成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("操作失败：" + e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/editStatus")
    @UserLoginToken
    public ResultMessage<Boolean> editStatus(@RequestBody OptUser optUser) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            userService.editStatus(optUser.getIds(), optUser.getStatus());
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("编辑成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("操作失败：" + e.toString());
            return booleanResultMessage;
        }
    }

}
