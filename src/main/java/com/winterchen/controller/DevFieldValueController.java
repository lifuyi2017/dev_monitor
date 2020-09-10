package com.winterchen.controller;

import com.winterchen.model.DevFieldValue;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.DevFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/devFieldValue")
public class DevFieldValueController {

    @Autowired
    private DevFieldValueService devFieldValueService;

    @ResponseBody
    @PostMapping("/add")
    public ResultMessage add(@RequestBody DevFieldValue devFieldValue){
        return devFieldValueService.addFieldValue(devFieldValue);
    }

    @ResponseBody
    @PostMapping("/getAllValueByDevId")
    public ResultMessage<List<DevFieldValue>> getAllValueByDevId(@RequestParam Integer devId){
        return devFieldValueService.getFieldValueByDevId(devId);
    }


}
