package com.winterchen.controller;

import com.winterchen.model.Network;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping(value = "/instrument")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @ResponseBody
    @PostMapping("/addOrUpdateNetWork")
    public ResultMessage<Boolean> addOrUpdateNetWork(@RequestBody Network network){
        if(network.getNetwork_id()!=null && !"".equals(network.getNetwork_id().trim())){
            //修改
            instrumentService.updateById(network);
        }else {
            //新增
            network.setNetwork_id(UUID.randomUUID().toString().replaceAll("-",""));
            instrumentService.insertEntity(network);
        }

    }

}
