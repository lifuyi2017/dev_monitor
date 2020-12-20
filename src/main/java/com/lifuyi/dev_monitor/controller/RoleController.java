package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/role")
@CrossOrigin
@Api(description = "角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/insertOrUpdateRole")
    @ApiOperation(value = "插入或者更新角色基本信息",  notes = "返回的mesg是id")
    public ResultMessage<Boolean> insertOrUpdateRole(@RequestBody Role role){
        return roleService.insertOrUpdateRole(role);
    }

    @PostMapping("/getRoleList")
    @ApiOperation(value = "获取角色列表",  notes = "")
    public ResultMessage<List<RoleResp>> getRoleList(@RequestBody Role role){
        return roleService.getRoleList(role);
    }


    /**
     * 权限设置
     */





}
