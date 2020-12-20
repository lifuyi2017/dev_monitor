package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;

import java.util.List;

public interface RoleService {
    ResultMessage<Boolean> insertOrUpdateRole(Role role);

    ResultMessage<List<RoleResp>> getRoleList(Role role);
}
