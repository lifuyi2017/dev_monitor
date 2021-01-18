package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.EnterPriseAuthor;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.model.role.RoleAuthority;
import com.lifuyi.dev_monitor.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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



    @PostMapping("/saveRoleAuthority")
    @ApiOperation(value = "权限设置",  notes = "")
    public ResultMessage<Boolean> saveRoleAuthority(@RequestBody List<RoleAuthority> roleAuthorityList){
        return roleService.saveRoleAuthority(roleAuthorityList);
    }


    @PostMapping("/getRoleAuthority")
    @ApiOperation(value = "获取角色权限",  notes = "后台系统展示用")
    public ResultMessage<Map<EnterPriseAuthor,String>> getRoleAuthority(@RequestParam("roleId")
                                                                            @ApiParam(value = "角色id",required = true) String roleId){
        return roleService.getRoleAuthority(roleId);
    }



}
