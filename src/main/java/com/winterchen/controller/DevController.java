package com.winterchen.controller;


import com.winterchen.model.*;
import com.winterchen.service.user.DevService;
import com.winterchen.service.user.DevTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/dev")
public class DevController {

    @Autowired
    private DevService devService;
    @Autowired
    private DevTypeService devTypeService;

    @ResponseBody
    @GetMapping("test")
    public ResultMessage<Boolean> test() {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        booleanResultMessage.setStatuscode("501");
        booleanResultMessage.setValue(false);
        booleanResultMessage.setMesg("服务端错误：");
        return booleanResultMessage;
    }

    /**
     * 新增设备或组件：type=1是设备，type=2是组件
     *
     * @param devElement
     * @return
     */
    @ResponseBody
    @PostMapping("/addDevOrDevElement")
    public ResultMessage<Boolean> addDevOrDevElement(@RequestBody DevElement devElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            if ("2".equals(devElement.getType())) {
                //插入组件
                if (devElement.getDev_parent_element_id() == null || "".equals(devElement.getDev_parent_element_id().trim())) {
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setMesg("添加组件时必须设置父组件id");
                    return booleanResultMessage;
                }
                DevElement queryParent = new DevElement();
                queryParent.setDev_element_id(devElement.getDev_parent_element_id());
                List<DevElement> devTypeElementList = devService.queryByEntity(queryParent);
                devElement.setDev_type_id(devTypeElementList.get(0).getDev_type_id());
                devElement.setDev_element_id(id);
                devService.insertEntity(devElement);
            } else {
                devElement.setDev_element_id(id);
                devElement.setDev_type_id(id);
                devService.insertEntity(devElement);
            }
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("添加成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setValue(false);
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            return booleanResultMessage;
        }
    }

    /**
     * 重命名
     *
     * @param devElement
     * @return
     */
    @ResponseBody
    @PostMapping("/editDevOrDevElement")
    public ResultMessage<Boolean> editDevTypeOrDevElement(@RequestBody DevElement devElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            devService.editDevOrDevElementById(devElement);
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("修改成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            return booleanResultMessage;
        }
    }

    /**
     * 删除组件或设备：type=1是设备，type=2是组件
     */
    @ResponseBody
    @PostMapping("/deleteDevType")
    public ResultMessage<Boolean> deleteDevType(@RequestBody DevElement devElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            //删除当前值、删除子节点
            devService.deleteElementAndSubElements(devElement.getDev_element_id());
            //如果是设备类型还需要删除数据：固定字段数据和用户自定义字段数据
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setMesg("删除成功");
            booleanResultMessage.setValue(true);
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setValue(false);
            return booleanResultMessage;
        }
    }


    /**
     * 获取树中的设备
     */
    @ResponseBody
    @PostMapping("/getTopElementsByEnterpriseId")
    public ResultMessage<List<DevElement>> getTopElements(@RequestBody Enterprise enterprise) {
        ResultMessage<List<DevElement>> resultMessage = new ResultMessage<>();
        try {
            DevElement devElement = new DevElement();
            devElement.setType("1");
            devElement.setEnterprise_id(enterprise.getEnterprise_id());
            List<DevElement> devTypeElements = devService.queryByEntity(devElement);
            resultMessage.setValue(devTypeElements);
            resultMessage.setMesg("查询成功");
            resultMessage.setStatuscode("200");
            return resultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(null);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
            return resultMessage;
        }
    }


    /**
     * 获取树中的下级列表
     */
    @ResponseBody
    @PostMapping("/getSubElementsByParentId")
    public ResultMessage<List<DevElement>> getSubElementsByParentId(@RequestBody DevElement devElement) {
        ResultMessage<List<DevElement>> resultMessage = new ResultMessage<>();
        try {
            List<DevElement> devTypeElements = devService.queryByEntity(devElement);
            resultMessage.setValue(devTypeElements);
            resultMessage.setMesg("查询成功");
            resultMessage.setStatuscode("200");
            return resultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(null);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
            return resultMessage;
        }
    }

    /**
     * 导入模板
     */
    @ResponseBody
    @PostMapping("/loadDevTypeTemplate")
    public ResultMessage<Boolean> loadDevTypeTemplate(@RequestBody DevInputRequest devInputRequest){

        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_type_id(devInputRequest.getType_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
