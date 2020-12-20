package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.RoleMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public ResultMessage<Boolean> insertOrUpdateRole(Role role) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        Role nameRole=roleMapper.getRoleByName(role.getRole_name(),role.getEnterprise_id());
        if((role.getId()==null && nameRole!=null) ||
                (role.getId()!=null && nameRole!=null && !role.getId().equals(nameRole.getId()))) {
            return new ResultMessage<Boolean>("401", "角色名称重复", false);
        }
        if(role.getId()==null){
            role.setId(id);
        }
        role.setCreate_time(new Date());role.setUpdate_time(new Date());
        roleMapper.insertOrUpdateRole(role);
        return new ResultMessage<Boolean>("401", role.getId(), true);
    }

    @Override
    public ResultMessage<List<RoleResp>> getRoleList(Role role) {
        return new ResultMessage<List<RoleResp>>("200","查询成功",roleMapper.getRoleList(role));
    }

}
