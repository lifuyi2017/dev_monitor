package com.winterchen.controller;

import com.winterchen.model.*;
import com.winterchen.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/devType")
public class DevTypeController {

    @Autowired
    private DevFixedFieldValueService devFixedFieldValueService;
    @Autowired
    private DevCustomFieldValueService devCustomFieldValueService;
    @Autowired
    private DevCustomFieldService devCustomFieldService;
    @Autowired
    private DevTypeService devTypeService;

    /**
     * 新增设备或组件：type=1是设备，type=2是组件
     *
     * @param devTypeElement
     * @return
     */
    @ResponseBody
    @PostMapping("/addDevTypeOrDevElement")
    public ResultMessage<Boolean> addDevTypeOrDevElement(@RequestBody DevTypeElement devTypeElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            if ("2".equals(devTypeElement.getType())) {
                //插入组件
                if (devTypeElement.getDev_parent_element_id() == null || "".equals(devTypeElement.getDev_parent_element_id().trim())) {
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setValue(false);
                    booleanResultMessage.setMesg("添加组件时必须设置父组件id");
                    return booleanResultMessage;
                }
                DevTypeElement queryParent = new DevTypeElement();
                queryParent.setDev_element_id(devTypeElement.getDev_parent_element_id());
                List<DevTypeElement> devTypeElementList = devTypeService.queryByEntity(queryParent);
                devTypeElement.setDev_type_id(devTypeElementList.get(0).getDev_type_id());
                devTypeElement.setDev_element_id(id);
                devTypeService.insertEntity(devTypeElement);
            } else {
                devTypeElement.setDev_element_id(id);
                devTypeElement.setDev_type_id(id);
                devTypeService.insertEntity(devTypeElement);
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
     * @param devTypeElement
     * @return
     */
    @ResponseBody
    @PostMapping("/editDevTypeOrDevElement")
    public ResultMessage<Boolean> editDevTypeOrDevElement(@RequestBody DevTypeElement devTypeElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            devTypeService.editDevTypeOrDevElement(devTypeElement);
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
    public ResultMessage<Boolean> deleteDevType(@RequestBody DevTypeElement devTypeElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            //删除当前值、删除子节点
            devTypeService.deleteElementAndSubElements(devTypeElement.getDev_element_id());
            //如果是设备类型还需要删除数据：固定字段数据和用户自定义字段数据
            if ("1".equals(devTypeElement.getType())) {
                devFixedFieldValueService.deleteByElementId(devTypeElement.getDev_element_id());
                devCustomFieldValueService.deleteByElementId(devTypeElement.getDev_element_id());
            }
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
    @PostMapping("/getTopElements")
    public ResultMessage<List<DevTypeElement>> getTopElements() {
        ResultMessage<List<DevTypeElement>> resultMessage = new ResultMessage<>();
        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setType("1");
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
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
    public ResultMessage<List<DevTypeElement>> getSubElementsByParentId(@RequestBody DevTypeElement devTypeElement) {
        ResultMessage<List<DevTypeElement>> resultMessage = new ResultMessage<>();
        try {
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
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
     * 编辑设备的用户自定义字段
     */
    @Transactional
    @ResponseBody
    @PostMapping("/editCustomField")
    public ResultMessage<Boolean> editCustomField(@RequestBody DevCustomFieldRequest devCustomFieldRequest) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_element_id(devCustomFieldRequest.getDev_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            devCustomFieldRequest.setDev_element_id(devTypeElements.get(0).getDev_type_id());
            //新的设置设备id
            List<DevCustomField> devCustomFieldListNew = devCustomFieldRequest.getDevCustomFieldList();
            for (DevCustomField devCustomField : devCustomFieldListNew) {
                devCustomField.setDev_element_id(devTypeElements.get(0).getDev_type_id());
            }
            //先查询原有的用户自定义字段
            DevCustomField devCustomField = new DevCustomField();
            devCustomField.setDev_element_id(devCustomFieldRequest.getDev_element_id());
            List<DevCustomField> devCustomFieldList = devCustomFieldService.getCustomFieldsByEntity(devCustomField);
            //先删除再增加
            devCustomFieldService.deleteByElementId(devCustomFieldRequest.getDev_element_id());
            for (DevCustomField customField : devCustomFieldListNew) {
                customField.setDev_type_custom_field_id(customField.getDev_element_id() + "-" + customField.getDev_type_field_name());
                devCustomFieldService.insertEntity(customField);
            }
            //删除的,还需要删除值表
            List<DevCustomField> deleteList = devCustomFieldList;
            deleteList.removeAll(devCustomFieldListNew);
            for (DevCustomField customField : deleteList) {
                devCustomFieldService.deleteEntity(customField);
                devCustomFieldValueService.deleteByCustomFieldId(customField.getDev_type_custom_field_id());
            }
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("修改成功");
            booleanResultMessage.setStatuscode("200");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setStatuscode("501");
            return booleanResultMessage;
        }
    }

    /**
     * 获取所有的用户自定义字段:只需要传入dev_element_id
     */
    @ResponseBody
    @PostMapping("/getCustomFieldByElementId")
    public ResultMessage<DevCustomFieldRequest> getCustomFieldByElementId(@RequestBody DevCustomFieldRequest devCustomFieldRequest) {
        ResultMessage<DevCustomFieldRequest> devCustomFieldRequestResultMessage = new ResultMessage<>();
        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_element_id(devCustomFieldRequest.getDev_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            DevCustomField devCustomField = new DevCustomField();
            devCustomField.setDev_element_id(devTypeElements.get(0).getDev_type_id());
            List<DevCustomField> devCustomFieldList = devCustomFieldService.getCustomFieldsByEntity(devCustomField);
            devCustomFieldRequest.setDev_element_id(devTypeElements.get(0).getDev_type_id());
            devCustomFieldRequest.setDevCustomFieldList(devCustomFieldList);
            devCustomFieldRequestResultMessage.setValue(devCustomFieldRequest);
            devCustomFieldRequestResultMessage.setMesg("查询成功");
            devCustomFieldRequestResultMessage.setStatuscode("200");
        } catch (Exception e) {
            e.printStackTrace();
            devCustomFieldRequestResultMessage.setValue(null);
            devCustomFieldRequestResultMessage.setMesg("服务端错误：" + e.toString());
            devCustomFieldRequestResultMessage.setStatuscode("501");
        }
        return devCustomFieldRequestResultMessage;
    }

    /**
     * 列表中新增设备
     */
    @Transactional
    @ResponseBody
    @PostMapping("/insertFieldValue")
    public ResultMessage<Boolean> insertFieldValue(@RequestBody DevFieldValueRequest devFieldValueRequest) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_element_id(devFieldValueRequest.getDevFixedFieldValue().getDev_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            devFieldValueRequest.getDevFixedFieldValue().setDev_element_id(devTypeElements.get(0).getDev_type_id());
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            devFieldValueRequest.getDevFixedFieldValue().setDev_type_field_value_id(id);
            devFixedFieldValueService.insertFixedValue(devFieldValueRequest.getDevFixedFieldValue().getDev_element_id(),
                    devFieldValueRequest.getDevFixedFieldValue());
            devCustomFieldValueService.insertCustomValue(id, devFieldValueRequest.getDevFixedFieldValue().getDev_element_id(),
                    devFieldValueRequest.getCustomFieldValue());
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("新增成功");
            booleanResultMessage.setStatuscode("200");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setValue(false);
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setStatuscode("501");
            return booleanResultMessage;
        }
    }

    /**
     * 查询设备列表
     */
    @ResponseBody
    @PostMapping("/getValueByElementId")
    public ResultMessage<List<DevFieldValueRequest>> getValueByElementId(@RequestBody DevFieldValueRequest devFieldValueRequest) {
        ResultMessage<List<DevFieldValueRequest>> resultMessage = new ResultMessage<>();
        try {
            ArrayList<DevFieldValueRequest> devFieldValueRequestArrayList = new ArrayList<>();
            //设置类型
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_element_id(devFieldValueRequest.getDevFixedFieldValue().getDev_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            devFieldValueRequest.getDevFixedFieldValue().setDev_element_id(devTypeElements.get(0).getDev_type_id());
            //查询固定字段
            List<DevFixedFieldValue> devFixedFieldValueList = devFixedFieldValueService.getValueListByElementId(
                    devFieldValueRequest.getDevFixedFieldValue().getDev_element_id());
            //查询用户自定义字段
            Map<String, Map<String, String>> customValueMap = devCustomFieldValueService.getValueListByElementId(
                    devFieldValueRequest.getDevFixedFieldValue().getDev_element_id());
            //进行合并
            for (DevFixedFieldValue devFixedFieldValue : devFixedFieldValueList) {
                DevFieldValueRequest request = new DevFieldValueRequest();
                request.setDevFixedFieldValue(devFixedFieldValue);
                request.setCustomFieldValue(customValueMap.get(devFixedFieldValue.getDev_type_field_value_id()));
                devFieldValueRequestArrayList.add(request);
            }
            resultMessage.setValue(devFieldValueRequestArrayList);
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
     * 删除列表设备
     */
    @Transactional
    @ResponseBody
    @PostMapping("/deleteValueByValueId")
    public ResultMessage<Boolean> deleteValueByValueId(@RequestBody DevCustomFieldValue devCustomFieldValue) {
        ResultMessage<Boolean> resultMessage = new ResultMessage<>();
        try {
            devFixedFieldValueService.deleteByValueId(devCustomFieldValue.getDev_type_field_value_id());
            devCustomFieldValueService.deleteByValueId(devCustomFieldValue.getDev_type_field_value_id());
            resultMessage.setValue(true);
            resultMessage.setMesg("删除成功");
            resultMessage.setStatuscode("200");
            return resultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(false);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
            return resultMessage;
        }
    }

    /**
     * 修改列表设备
     */
    @ResponseBody
    @PostMapping("/updateValueByValueId")
    public ResultMessage<Boolean> updateValueByValueId(@RequestBody DevFieldValueRequest devFieldValueRequest) {
        ResultMessage<Boolean> resultMessage = new ResultMessage<>();
        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_element_id(devFieldValueRequest.getDevFixedFieldValue().getDev_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            devFieldValueRequest.getDevFixedFieldValue().setDev_element_id(devTypeElements.get(0).getDev_type_id());
            devFixedFieldValueService.updateByValueId(devFieldValueRequest.getDevFixedFieldValue());
            devCustomFieldValueService.updateByValueId(devFieldValueRequest.getDevFixedFieldValue().getDev_element_id(),
                    devFieldValueRequest.getDevFixedFieldValue().getDev_type_field_value_id(), devFieldValueRequest.getCustomFieldValue());
            resultMessage.setValue(true);
            resultMessage.setMesg("更新成功");
            resultMessage.setStatuscode("200");
            return resultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setValue(false);
            resultMessage.setMesg("服务端错误：" + e.toString());
            resultMessage.setStatuscode("501");
            return resultMessage;
        }
    }

}
