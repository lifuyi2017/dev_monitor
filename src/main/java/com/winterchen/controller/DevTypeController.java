package com.winterchen.controller;

import com.winterchen.model.DevCustomFieldRequest;
import com.winterchen.model.DevTypeElement;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.DevCustomFieldValueService;
import com.winterchen.service.user.DevFieldValueService;
import com.winterchen.service.user.DevFixedFieldValueService;
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
    private DevFixedFieldValueService devFixedFieldValueService;
    @Autowired
    private DevCustomFieldValueService devCustomFieldValueService;
    @Autowired
    private DevTypeService devTypeService;

    /**
     * 新增设备或组件：type=1是设备，type=2是组件
     * @param devTypeElement
     * @return
     */
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

    /**
     * 重命名
     * @param devTypeElement
     * @return
     */
    @ResponseBody
    @PostMapping("/editDevTypeOrDevElement")
    public ResultMessage<Boolean> editDevTypeOrDevElement(@RequestBody DevTypeElement devTypeElement){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            devTypeService.editDevTypeOrDevElement(devTypeElement);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("添加成功");
            return booleanResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("服务端错误："+e.toString());
            return booleanResultMessage;
        }
    }

    /**
     * 删除组件或设备：type=1是设备，type=2是组件
     * @param devTypeElement
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteDevType")
    public ResultMessage<Boolean> deleteDevType(@RequestBody DevTypeElement devTypeElement){
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            //删除当前值、删除子节点
            devTypeService.deleteElementAndSubElements(devTypeElement.getDev_element_id());
            //如果是设备类型还需要删除数据：固定字段数据和用户自定义字段数据
            if("1".equals(devTypeElement.getType())){
                devFixedFieldValueService.deleteByElementId(devTypeElement.getDev_element_id());
                devCustomFieldValueService.deleteByElementId(devTypeElement.getDev_element_id());
            }
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("删除成功");
            booleanResultMessage.setValue(true);
            return booleanResultMessage;
        }catch (Exception e){
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("服务端错误："+e.toString());
            booleanResultMessage.setValue(false);
            return booleanResultMessage;
        }
    }

    /**
     * 编辑设备的用户自定义字段
     */
    @ResponseBody
    @PostMapping("/editCustomField")
    public ResultMessage<Boolean> editCustomField(@RequestBody DevCustomFieldRequest devCustomFieldRequest){

        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_parent_element_id(devCustomFieldRequest.getDev_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            devCustomFieldRequest.setDev_element_id(devTypeElements.get(0).getDev_element_id());

        }catch (Exception e){

        }


    }

}