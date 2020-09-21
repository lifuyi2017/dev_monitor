package com.winterchen.controller;


import com.github.pagehelper.PageInfo;
import com.winterchen.model.Enterprise;
import com.winterchen.model.EnterpriseRequest;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @ResponseBody
    @PostMapping("/addOrUpdate")
    public ResultMessage<Boolean> addOrUpdate(@RequestBody Enterprise enterprise){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        booleanResultMessage.setValue(true);
        try {
            Enterprise queryEnter = new Enterprise();
            queryEnter.setEnterprise_id(enterprise.getEnterprise_id());
            List<Enterprise> list=enterpriseService.getEnterByEntityNoPage(queryEnter);
            if(list!=null && list.size()>0){
                enterpriseService.updateById(enterprise);
                booleanResultMessage.setMesg("修改成功");
                booleanResultMessage.setStatuscode("200");
                return booleanResultMessage;
            }
            enterpriseService.add(enterprise);
            booleanResultMessage.setMesg("添加成功");
            booleanResultMessage.setStatuscode("200");
            return booleanResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setMesg("操作失败");
            booleanResultMessage.setStatuscode("501："+e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/getEnterprise")
    public  ResultMessage<PageInfo<Enterprise>> getEnterprise(@RequestBody EnterpriseRequest enterpriseRequest){
        ResultMessage<PageInfo<Enterprise>> booleanResultMessage = new ResultMessage<>();
        try {
            PageInfo<Enterprise> list=enterpriseService.getEnterByEntity(enterpriseRequest);
            booleanResultMessage.setValue(list);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("查询成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(null);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("查询失败:"+e.toString());
            return booleanResultMessage;
        }
    }

    @ResponseBody
    @PostMapping("/deleteById")
    public ResultMessage<Boolean> deleteById(@RequestParam String id){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            enterpriseService.deleteById(id);
            booleanResultMessage.setValue(true);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("删除成功");
            return booleanResultMessage;
        }catch (Exception e){
            booleanResultMessage.setValue(false);
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("删除失败");
            return booleanResultMessage;
        }
    }


}
