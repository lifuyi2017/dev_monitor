package com.lifuyi.dev_monitor.service.impl;

import com.lifuyi.dev_monitor.dao.EnterpriseMapper;
import com.lifuyi.dev_monitor.dao.RoleMapper;
import com.lifuyi.dev_monitor.dao.UserMapper;
import com.lifuyi.dev_monitor.dao.WorkShopMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.role.Resp.*;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.model.role.RoleAuthority;
import com.lifuyi.dev_monitor.model.user.Resp.UserResp;
import com.lifuyi.dev_monitor.model.user.User;
import com.lifuyi.dev_monitor.service.RoleService;
import com.lifuyi.dev_monitor.service.UserService;
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
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @Override
    public ResultMessage<Boolean> insertOrUpdateRole(Role role) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        Role nameRole = roleMapper.getRoleByName(role.getRole_name(), role.getEnterprise_id());
        if ((StringUtils.isBlank(role.getId()) && nameRole != null) ||
                (!StringUtils.isBlank(role.getId()) && nameRole != null && !role.getId().equals(nameRole.getId()))) {
            return new ResultMessage<Boolean>("401", "角色名称重复", false);
        }
        if (StringUtils.isBlank(role.getId())) {
            role.setId(id);
        }
        role.setCreate_time(new Date());
        role.setUpdate_time(new Date());
        roleMapper.insertOrUpdateRole(role);
        return new ResultMessage<Boolean>("200", role.getId(), true);
    }

    @Override
    public ResultMessage<List<RoleResp>> getRoleList(Role role) {
        return new ResultMessage<List<RoleResp>>("200", "查询成功", roleMapper.getRoleList(role));
    }

    @Override
    public ResultMessage<Boolean> saveRoleAuthority(List<RoleAuthority> roleAuthorityList) {
        for (RoleAuthority roleAuthority : roleAuthorityList) {
            //先删除掉之前所有的权限
            roleMapper.deleteAuthorityByRoleId(roleAuthority.getRole_id());
            //新增权限
            if (StringUtils.isBlank(roleAuthority.getShop_id())) {
                String id = workShopMapper.getParentIdById(roleAuthority.getFarm_id());
                roleAuthority.setShop_id(id);
            }
            if (StringUtils.isBlank(roleAuthority.getEnterprise_id())) {
                String id = workShopMapper.getParentIdById(roleAuthority.getShop_id());
                roleAuthority.setEnterprise_id(id);
            }

            roleAuthority.setId(roleAuthority.getRole_id() + "=" + roleAuthority.getFarm_id() + "=" +
                    roleAuthority.getShop_id() + "=" + roleAuthority.getEnterprise_id());
            roleMapper.insertOrUpdateRoleAuthority(roleAuthority);
        }
        return new ResultMessage<Boolean>("200", "success", true);
    }

    @Override
    public ResultMessage<List<EnterPriseAuthor>> getRoleAuthority(String roleId) {
        List<EnterPriseAuthor> enterPriseAuthors=new ArrayList<>();
//        Map<EnterPriseAuthor, String> enterPriseAuthorStringHashMap = new HashMap<>();
        List<String> shopIds=roleMapper.getShopIdsByRoleId(roleId);
        Role roleQuery = new Role();
        roleQuery.setId(roleId);
        List<RoleResp> roleList = roleMapper.getRoleList(roleQuery);
        if (roleList != null && roleList.size() > 0) {
            RoleResp roleResp = roleList.get(0);
            List<Enterprise> serviceNames = enterpriseMapper.getServiceNames(roleResp.getEnterprise_id());
            for (Enterprise enterprise : serviceNames) {
                WorkShopQueryReq workShopQueryReq = new WorkShopQueryReq(enterprise.getEnterprise_id(),
                        enterprise.getEnterprise_id(), "1");
                //厂房
                List<WorkShop> farmList = workShopMapper.getWorkShopList(workShopQueryReq);
                List<FarmAuthor> farmRespList=new ArrayList<>();
                String enterPriseFlag="0";
                for (WorkShop farm : farmList) {
                    //车间
                    WorkShopQueryReq workShopQueryReq1 = new WorkShopQueryReq(enterprise.getEnterprise_id(),
                            farm.getId(), "2");
                    List<WorkShop> shopList = workShopMapper.getWorkShopList(workShopQueryReq1);
                    List<ShopAuthor> shopRespList=new ArrayList<>();
                    String farmFlag="0";
                    for (WorkShop shop : shopList) {
                        if(shopIds.contains(shop.getId())){
                            shopRespList.add(new ShopAuthor(shop.getId(),shop.getName(),"1"));
                            farmFlag="1";
                            enterPriseFlag="1";
                        }else {
                            shopRespList.add(new ShopAuthor(shop.getId(),shop.getName(),"0"));
                        }
                    }
                    farmRespList.add(new FarmAuthor(shopRespList,farm.getId(),farm.getName(),farmFlag));
                }
                enterPriseAuthors.add(
                        new EnterPriseAuthor(farmRespList,enterprise.getEnterprise_id(),enterprise.getEnterprise_name(),enterPriseFlag));
            }
        }
        return new ResultMessage<List<EnterPriseAuthor>>("200","success",enterPriseAuthors);
    }

    @Override
    public void deleteById(String id) {
        roleMapper.deleteRoleAuthorByRoleId(id);
        roleMapper.deleteRoleById(id);
        User user = new User();
        user.setRole_id(id);
        List<UserResp> userByEntity = userMapper.getUserByEntity(user);
        for(UserResp resp:userByEntity){
            userService.deleteById(resp.getId());
        }
    }

}
