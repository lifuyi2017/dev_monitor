package com.winterchen.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.annotation.UserLoginToken;
import com.winterchen.model.Enterprise;
import com.winterchen.model.EnterpriseRequest;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/enterprise")
@CrossOrigin
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @ResponseBody
    @PostMapping("/addOrUpdate")
    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdate(@RequestBody Enterprise enterprise) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        booleanResultMessage.setValue(true);
        try {
            Enterprise queryEnter = new Enterprise();
            queryEnter.setEnterprise_id(enterprise.getEnterprise_id() == null ? "" : enterprise.getEnterprise_id());
            List<Enterprise> list = enterpriseService.getEnterByEntityNoPage(queryEnter);
            if (list != null && list.size() > 0) {
                enterpriseService.updateById(enterprise);
                booleanResultMessage.setMesg("修改成功");
                booleanResultMessage.setStatuscode("200");
                return booleanResultMessage;
            }
            enterprise.setEnterprise_id(UUID.randomUUID().toString().replaceAll("-", ""));
            enterpriseService.add(enterprise);
            booleanResultMessage.setMesg("添加成功");
            booleanResultMessage.setStatuscode("200");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setMesg("操作失败");
            booleanResultMessage.setStatuscode("501：" + e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/getEnterprise")
    @UserLoginToken
    public ResultMessage<PageInfo<Enterprise>> getEnterprise(@RequestBody EnterpriseRequest enterpriseRequest) {
        ResultMessage<PageInfo<Enterprise>> booleanResultMessage = new ResultMessage<>();
        try {
            PageInfo result;
            if (enterpriseRequest.getPageNum() != null && enterpriseRequest.getPageSize() != null) {
                PageHelper.startPage(enterpriseRequest.getPageNum(), enterpriseRequest.getPageSize());
                List<Enterprise> list = enterpriseService.getEnterByEntity(enterpriseRequest.getEnterprise());
                result= new PageInfo(list);
            }else {
                result=new PageInfo();
                List<Enterprise> list = enterpriseService.getEnterByEntity(enterpriseRequest.getEnterprise());
                result.setList(list);
            }
            booleanResultMessage.setValue(result);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("查询成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(null);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("查询失败:" + e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/deleteById")
    @UserLoginToken
    public ResultMessage<Boolean> deleteById(@RequestBody Enterprise enterprise) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            enterpriseService.deleteById(enterprise.getEnterprise_id());
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("删除成功");
            return booleanResultMessage;
        } catch (Exception e) {
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("删除失败");
            return booleanResultMessage;
        }
    }


}
