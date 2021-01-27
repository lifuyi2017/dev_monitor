package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.*;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShop;
import com.lifuyi.dev_monitor.model.collect.req.WorkShopQueryReq;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.model.dev.Req.BaseDevEntityReq;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevPagesRsp;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.EnterpriseBinging;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterprisePageReq;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseResp;
import com.lifuyi.dev_monitor.model.logic.LogicNode;
import com.lifuyi.dev_monitor.model.logic.resp.LogicResp;
import com.lifuyi.dev_monitor.model.network.Network;
import com.lifuyi.dev_monitor.model.network.resp.NetworkResp;
import com.lifuyi.dev_monitor.model.physical.Physical;
import com.lifuyi.dev_monitor.model.physical.resp.PhysicalResp;
import com.lifuyi.dev_monitor.model.role.Resp.RoleResp;
import com.lifuyi.dev_monitor.model.role.Role;
import com.lifuyi.dev_monitor.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {

    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private DevService devService;
    @Resource
    private DevMapper devMapper;
    @Resource
    private NetWorkService netWorkService;
    @Resource
    private NetworkMapper networkMapper;
    @Resource
    private PhysicalService physicalService;
    @Resource
    private PhysicalMapper physicalMapper;
    @Resource
    private LogicMapper logicMapper;
    @Resource
    private LogicService logicService;
    @Resource
    private WorkShopMapper workShopMapper;
    @Resource
    private WorkShopService workShopService;


    @Transactional
    @Override
    public ResultMessage<Boolean> addOrUpdate(EnterpriseReq enterpriseReq) {
        Enterprise queryEnter = new Enterprise();
        try {
            //更新info
            Enterprise enterprise = new Enterprise();
            BeanUtils.copyProperties(enterpriseReq, enterprise);
            if (enterpriseReq.getEnterprise_id() == null) {
                //新增的时候企业名称和编号不能重复
                queryEnter.setEnterprise_name(enterpriseReq.getEnterprise_name());
                List<Enterprise> listByEntity = enterpriseMapper.getListByEntity(queryEnter);
                if (listByEntity != null && listByEntity.size() > 0) {
                    return new ResultMessage<Boolean>("401", "企业名称重复", false);
                }
                queryEnter = new Enterprise();
                queryEnter.setEnterprise_code(enterpriseReq.getEnterprise_code());
                listByEntity = enterpriseMapper.getListByEntity(queryEnter);
                if (listByEntity != null && listByEntity.size() > 0) {
                    return new ResultMessage<Boolean>("401", "企业编号重复", false);
                }
                enterprise.setEnterprise_id(UUID.randomUUID().toString().replaceAll("-", ""));
            }else {
                //编辑的时候企业名称和编号不能改来和其他的重复
                Integer num=enterpriseMapper.verificationName(enterpriseReq.getEnterprise_id(),enterpriseReq.getEnterprise_name());
                if(num>0){
                    return new ResultMessage<Boolean>("401", "企业名称重复", false);
                }
                Integer n=enterpriseMapper.verificationCode(enterpriseReq.getEnterprise_id(),enterpriseReq.getEnterprise_code());
                if(n>0){
                    return new ResultMessage<Boolean>("401", "企业编号重复", false);
                }

            }
            enterprise.setEnterprise_type_id(enterpriseReq.getEnterpriseTypeResp().getId());
            enterprise.setUpdate_time(new Date());
            enterpriseMapper.insertOrUpdateById(enterprise);
            //更新关系
            if (enterpriseReq.getBinging_id() != null && enterpriseReq.getBinging_id().size() > 0) {
                if (enterpriseReq.getEnterpriseTypeResp().getId() == 2) {
                    //使用商
                    for (String id : enterpriseReq.getBinging_id()) {
                        EnterpriseBinging enterpriseBinging =
                                new EnterpriseBinging(id + "_" + enterprise.getEnterprise_id(), id, enterprise.getEnterprise_id());
                        enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                    }
                    EnterpriseBinging enterpriseBinging =
                            new EnterpriseBinging(enterprise.getEnterprise_id() + "_" + enterprise.getEnterprise_id(),
                                    enterprise.getEnterprise_id(), enterprise.getEnterprise_id());
                    enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                } else {
                    //非使用商，修改所绑定的使用商
                    //删除
                    enterpriseMapper.deleteBingingByNotServiceId(enterprise.getEnterprise_id());
                    //添加
                    for (String id : enterpriseReq.getBinging_id()) {
                        EnterpriseBinging enterpriseBinging =
                                new EnterpriseBinging(enterprise.getEnterprise_id() + "_" + id, enterprise.getEnterprise_id(), id);
                        enterpriseMapper.insertOrUpdateBinging(enterpriseBinging);
                    }
                }
            }
            return new ResultMessage<Boolean>("200", "操作成功", false);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage<Boolean>("501", "操作失败，服务器异常:" + e.getMessage(), false);
        }
    }

    @Override
    public ResultMessage<PageInfo<EnterpriseResp>> getEnterprisePage(EnterprisePageReq pageReq) {
        PageHelper.startPage(pageReq.getPageNum(), pageReq.getPageSize());
        Enterprise enterprise = new Enterprise();
        enterprise.setEnterprise_name(pageReq.getEnterprise_name());
        List<Enterprise> list = enterpriseMapper.getListByEntity(enterprise);
        PageInfo pageInfo = new PageInfo(list);
        List<EnterpriseResp> respList = new ArrayList<>();
        List<Enterprise> serviceEnterprise;
        for (Enterprise enter : list) {
            EnterpriseResp enterpriseResp = new EnterpriseResp();
            if (enter.getEnterprise_type_id() == 2) {
                serviceEnterprise=new ArrayList<>();
                serviceEnterprise.add(enter);
                enterpriseResp.setEnterpriseList(serviceEnterprise);
            } else {
                serviceEnterprise = enterpriseMapper.getServiceNames(enter.getEnterprise_id());
                enterpriseResp.setEnterpriseList(serviceEnterprise);
            }
            enterpriseResp.setEnterpriseTypeResp(enterpriseMapper.getTypeById(enter.getEnterprise_type_id()));
            BeanUtils.copyProperties(enter, enterpriseResp);
            respList.add(enterpriseResp);
        }
        pageInfo.setList(respList);
        return new ResultMessage<PageInfo<EnterpriseResp>>("200", "查询成功", pageInfo);
    }

    @Override
    public ResultMessage<List<EnterpriseResp>> getEnterprise(Enterprise enterprise) {
        List<Enterprise> list = enterpriseMapper.getListByEntity(enterprise);
        List<EnterpriseResp> respList = new ArrayList<>();
        List<Enterprise> serviceEnterprise;
        for (Enterprise enter : list) {
            EnterpriseResp enterpriseResp = new EnterpriseResp();
            if (enter.getEnterprise_type_id() == 2) {
                serviceEnterprise=new ArrayList<>();
                serviceEnterprise.add(enter);
                enterpriseResp.setEnterpriseList(serviceEnterprise);
            } else {
                serviceEnterprise = enterpriseMapper.getServiceNames(enter.getEnterprise_id());
                enterpriseResp.setEnterpriseList(serviceEnterprise);
            }
            enterpriseResp.setEnterpriseTypeResp(enterpriseMapper.getTypeById(enter.getEnterprise_type_id()));
            BeanUtils.copyProperties(enter, enterpriseResp);
            respList.add(enterpriseResp);
        }
        return new ResultMessage<List<EnterpriseResp>>("200","查询成功",respList);
    }

    @Override
    public ResultMessage<List<Enterprise>> getEnterpriseByTypeIds(List<String> ids) {
        return new ResultMessage<List<Enterprise>>("200","查询成功",enterpriseMapper.getEnterpriseByTypeIds(ids));
    }

    //企业删除
    @Override
    public ResultMessage<Boolean> deleteEnterprise(String id) {
        //删用户：user
        userMapper.deleteByEnterpriseId(id);
        //删角色：role、role_authority
        Role role = new Role();
        role.setEnterprise_id(id);
        List<RoleResp> valueRole = roleService.getRoleList(role).getValue();
        for(RoleResp roleResp:valueRole){
            roleService.deleteById(roleResp.getId());
        }
        //删设备:dev_base_info、fan、motor、water_pump、workshop_dev、collect_dev_config
        BaseDevEntity baseDevEntity = new BaseDevEntity();
        baseDevEntity.setEnterprise_id(id);
        List<BaseDevPagesRsp> baseListByEntity = devMapper.getBaseListByEntity(baseDevEntity);
        for(BaseDevPagesRsp baseDevPagesRsp:baseListByEntity){
            devService.deleteById(baseDevPagesRsp.getId());
        }
        //删网关：
        Network network = new Network();
        network.setEnterprise_id(id);
        List<NetworkResp> listByEntity = networkMapper.getListByEntity(network);
        for(NetworkResp resp:listByEntity){
            netWorkService.deleteById(resp.getNetwork_id());
        }
        //删物理节点
        Physical physical = new Physical();
        physical.setEnterprise_id(id);
        List<PhysicalResp> pageByEntity = physicalMapper.getPageByEntity(physical);
        for(PhysicalResp resp:pageByEntity){
            physicalService.deleteById(resp.getId());
        }
        //删逻辑节点
        LogicNode logicNode = new LogicNode();
        logicNode.setEnterprise_id(id);
        List<LogicResp> logicNodePages = logicMapper.getLogicNodePages(logicNode);
        for(LogicResp resp:logicNodePages){
            logicService.deleteById(resp.getLogic_id());
        }
        //删厂房车间
        WorkShopQueryReq workShopQueryReq = new WorkShopQueryReq();
        workShopQueryReq.setEnterprise_id(id);
        workShopQueryReq.setType("1");
        List<WorkShop> workShopList = workShopMapper.getWorkShopList(workShopQueryReq);
        for(WorkShop workShop:workShopList){
            workShopService.deleteById(workShop.getId());
        }
        //删除企业
        enterpriseMapper.deleteById(id);
        enterpriseMapper.deleteBingById(id);

        return new ResultMessage<Boolean>("200","success",true);
    }

}
