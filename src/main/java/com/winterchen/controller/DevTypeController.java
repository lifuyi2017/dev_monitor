package com.winterchen.controller;

import com.winterchen.model.DevTypeElement;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.DevFieldValueService;
import com.winterchen.service.user.DevTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/devType")
public class DevTypeController {

    @Autowired
    private DevFieldValueService devFieldValueService;
    @Autowired
    private DevTypeService devTypeService;

    @ResponseBody
    @PostMapping("/addDevTypeOrDevElement")
    public ResultMessage<Boolean> addDevTypeOrDevElement(@RequestBody DevTypeElement devTypeElement){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            if("2".equals(devTypeElement.getType())){
                //插入组件
                if(devTypeElement.getDev_parent_element_id()==null || "".equals(devTypeElement.getDev_parent_element_id().trim())){
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setMesg("添加组件时必须设置父组件id");
                    return booleanResultMessage;
                }
                DevTypeElement queryParent = new DevTypeElement();
                queryParent.setDev_element_id(devTypeElement.getDev_parent_element_id());
                List<DevTypeElement> devTypeElementList=devTypeService.queryByEntity(queryParent);
                devTypeElement.setDev_type_id(devTypeElementList.get(0).getDev_type_id());
                devTypeElement.setDev_element_id(id);
                devTypeService.insertEntity(devTypeElement);
            }else {
                devTypeElement.setDev_element_id(id);
                devTypeElement.setDev_type_id(id);
                devTypeService.insertEntity(devTypeElement);
            }
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("添加成功");
            return booleanResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setValue(false);
            booleanResultMessage.setMesg("服务端错误："+e.toString());
            return booleanResultMessage;
        }
    }


}
