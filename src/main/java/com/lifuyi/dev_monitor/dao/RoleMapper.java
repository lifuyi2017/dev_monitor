package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;

import java.util.List;

public interface RoleMapper {
    Role getRoleByName(String role_name, String enterprise_id);

    void insertOrUpdateRole(Role role);


    List<RoleResp> getRoleList(Role role);
}
