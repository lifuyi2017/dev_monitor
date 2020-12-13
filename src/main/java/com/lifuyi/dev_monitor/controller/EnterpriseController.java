package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.dao.EnterpriseMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.enterprise.Enterprise;
import com.lifuyi.dev_monitor.model.enterprise.Req.EnterpriseReq;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;
import com.lifuyi.dev_monitor.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/enterprise")
@CrossOrigin
public class EnterpriseController {

    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private EnterpriseService enterpriseService;


    @ResponseBody
    @PostMapping("/getType")
    @UserLoginToken
    public ResultMessage<List<EnterpriseTypeResp>> getType() {
        return new ResultMessage<List<EnterpriseTypeResp>>("200","成功",enterpriseMapper.getType());
    }

    @ResponseBody
    @PostMapping("/addOrUpdate")
    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdate(@RequestBody EnterpriseReq enterpriseReq) {
        return enterpriseService.addOrUpdate(enterpriseReq);
    }




}
