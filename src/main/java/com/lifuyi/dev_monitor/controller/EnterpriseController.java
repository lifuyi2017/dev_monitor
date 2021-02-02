package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.dao.EnterpriseMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterprisePageReq;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
import com.lifuyi.dev_monitor.model.enterprise.Req.TypeIds;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseResp;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;
import com.lifuyi.dev_monitor.service.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/enterprise")
@Api(description = "企业管理")
@CrossOrigin
public class EnterpriseController {

    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterpriseService enterpriseService;



    @PostMapping("/getType")
    @ApiOperation(value = "获取企业类型", notes = "获取企业类型")
    @UserLoginToken
    public ResultMessage<List<EnterpriseTypeResp>> getType() {
        return new ResultMessage<List<EnterpriseTypeResp>>("200","成功",enterpriseMapper.getType());
    }


    @PostMapping("/addOrUpdate")
    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdate(@RequestBody EnterpriseReq enterpriseReq) {
        return enterpriseService.addOrUpdate(enterpriseReq);
    }



    @PostMapping("/getEnterprisePage")
    @UserLoginToken
    public ResultMessage<PageInfo<EnterpriseResp>> getEnterprisePage(@RequestBody EnterprisePageReq pageReq) {
        return enterpriseService.getEnterprisePage(pageReq);
    }


    @PostMapping("/getEnterprise")
    @ApiOperation(value = "获取企业信息，不分页", notes = "权限那儿获取关联企业可以用这儿")
    @UserLoginToken
    public ResultMessage<List<EnterpriseResp>> getEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseService.getEnterprise(enterprise);
    }


    @PostMapping("/getEnterpriseByTypeIds")
    @UserLoginToken
    public ResultMessage<List<Enterprise>> getEnterpriseByTypeIds(@RequestBody TypeIds ids) {
        return enterpriseService.getEnterpriseByTypeIds(ids.getIds());
    }

    @PostMapping("/deleteEnterprise")
    @ApiOperation(value = "通过企业id删除企业", notes = "")
    public ResultMessage<Boolean> deleteEnterprise(@RequestParam("id") List<String> id){
        try {
            for(String s:id){
                enterpriseService.deleteEnterprise(s);
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }



}
