package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.RoleMapper;
import com.lifuyi.dev_monitor.dao.WorkShopMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.role.Resp.EnterPriseAuthor;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.model.role.RoleAuthority;
import com.lifuyi.dev_monitor.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private WorkShopMapper workShopMapper;

    @Override
    public ResultMessage<Boolean> insertOrUpdateRole(Role role) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        Role nameRole=roleMapper.getRoleByName(role.getRole_name(),role.getEnterprise_id());
        if((StringUtils.isBlank(role.getId()) && nameRole!=null) ||
                (!StringUtils.isBlank(role.getId()) && nameRole!=null && !role.getId().equals(nameRole.getId()))) {
            return new ResultMessage<Boolean>("401", "角色名称重复", false);
        }
        if(StringUtils.isBlank(role.getId())){
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

    @Override
    public ResultMessage<Boolean> saveRoleAuthority(List<RoleAuthority> roleAuthorityList) {
        for(RoleAuthority roleAuthority:roleAuthorityList){
            //先删除掉之前所有的权限
            roleMapper.deleteAuthorityByRoleId(roleAuthority.getRole_id());
            //新增权限
            if(StringUtils.isBlank(roleAuthority.getShop_id())){
                String id=workShopMapper.getParentIdById(roleAuthority.getFarm_id());
                roleAuthority.setShop_id(id);
            }
            if (StringUtils.isBlank(roleAuthority.getEnterprise_id())){
                String id=workShopMapper.getParentIdById(roleAuthority.getShop_id());
                roleAuthority.setEnterprise_id(id);
            }

            roleAuthority.setId(roleAuthority.getRole_id()+"="+roleAuthority.getFarm_id()+"="+
                    roleAuthority.getShop_id()+"="+roleAuthority.getEnterprise_id());
            roleMapper.insertOrUpdateRoleAuthority(roleAuthority);
        }
        return new ResultMessage<Boolean>("200","success",true);
    }

    @Override
    public ResultMessage<Map<EnterPriseAuthor, String>> getRoleAuthority(String roleId) {
        Map<EnterPriseAuthor, String> enterPriseAuthorStringHashMap = new HashMap<>();
        Role roleQuery = new Role();
        roleQuery.setId(roleId);
        List<RoleResp> roleList = roleMapper.getRoleList(roleQuery);

    }

}
