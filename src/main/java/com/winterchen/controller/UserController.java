package com.winterchen.controller;

import com.github.pagehelper.PageHelper;
import com.winterchen.model.ResultMessage;
import com.winterchen.model.User;
import com.winterchen.model.UserDomain;
import com.winterchen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

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
    @PostMapping("/addOrUpdateUser")
    public ResultMessage<Boolean> addOrUpdateUser(@RequestBody User user) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            if(user.getEnterprise_id()==null || ("").equals(user.getEnterprise_id().trim())){
                booleanResultMessage.setValue(false);
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg("请设置企业id");
                return booleanResultMessage;
            }
            if (user.getUser_id() != null) {
                User userQuery = new User();
                userQuery.setUser_id(user.getUser_id());
                List<User> userList = userService.getUsersByUser(userQuery);
                if (userList == null || userList.size() == 0) {
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setMesg("没有这个用户");
                    return booleanResultMessage;
                }
                userService.updateById(user);
            } else {
                userService.addUser(user);
            }
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("操作成功");
            return booleanResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("系统错误:"+e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/queryUser")
    public ResultMessage<List<User>> queryUser(@RequestBody User user) {
        ResultMessage<List<User>> listResultMessage = new ResultMessage<>();
        try {
            List<User> usersByUser = userService.getUsersByUser(user);
            listResultMessage.setValue(usersByUser);
            listResultMessage.setStatuscode("200");
            listResultMessage.setMesg("查询成功");
            return listResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            listResultMessage.setValue(null);
            listResultMessage.setStatuscode("501");
            listResultMessage.setMesg("服务器错误:"+e.toString());
            return listResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/deleteByIds")
    public ResultMessage<Boolean> deleteByIds(@RequestParam List<Integer> ids){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            userService.deleteByIds(ids);
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("删除成功");
            return booleanResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("操作失败："+e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/editStatus")
    public ResultMessage<Boolean> editStatus(@RequestParam List<Integer> ids,@RequestParam String status){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            userService.editStatus(ids,status);
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("编辑成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("操作失败："+e.toString());
            return booleanResultMessage;
        }
    }

}
