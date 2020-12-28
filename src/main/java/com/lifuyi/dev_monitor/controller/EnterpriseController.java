package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.dao.EnterpriseMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterprisePageReq;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
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

@Controller
@RequestMapping(value = "/enterprise")
@Api(description = "企业管理")
@CrossOrigin
public class EnterpriseController {

    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterpriseService enterpriseService;


    @ResponseBody
    @PostMapping("/getType")
    @ApiOperation(value = "获取企业类型", notes = "获取企业类型")
//    @UserLoginToken
    public ResultMessage<List<EnterpriseTypeResp>> getType() {
        return new ResultMessage<List<EnterpriseTypeResp>>("200","成功",enterpriseMapper.getType());
    }

    @ResponseBody
    @PostMapping("/addOrUpdate")
//    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdate(@RequestBody EnterpriseReq enterpriseReq) {
        return enterpriseService.addOrUpdate(enterpriseReq);
    }


    @ResponseBody
    @PostMapping("/getEnterprisePage")
//    @UserLoginToken
    public ResultMessage<PageInfo<EnterpriseResp>> getEnterprisePage(@RequestBody EnterprisePageReq pageReq) {
        return enterpriseService.getEnterprisePage(pageReq);
    }

    @ResponseBody
    @PostMapping("/getEnterprise")
//    @UserLoginToken
    public ResultMessage<List<Enterprise>> getEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseService.getEnterprise(enterprise);
    }

    @ResponseBody
    @PostMapping("/getEnterpriseByTypeIds")
//    @UserLoginToken
    public ResultMessage<List<Enterprise>> getEnterpriseByTypeIds(@RequestBody List<String> ids) {
        return enterpriseService.getEnterpriseByTypeIds(ids);
    }

}
