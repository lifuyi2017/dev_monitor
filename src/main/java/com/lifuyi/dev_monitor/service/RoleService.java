package com.lifuyi.dev_monitor.service;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.EnterPriseAuthor;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.model.role.RoleAuthority;

import java.util.List;
import java.util.Map;

public interface RoleService {
    ResultMessage<Boolean> insertOrUpdateRole(Role role);

    ResultMessage<List<RoleResp>> getRoleList(Role role);

    ResultMessage<Boolean> saveRoleAuthority(List<RoleAuthority> roleAuthorityList);

//    ResultMessage<Map<EnterPriseAuthor, String>> getRoleAuthority(String roleId);
}
