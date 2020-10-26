package com.winterchen.controller;


import com.winterchen.annotation.UserLoginToken;
import com.winterchen.dao.DevElementMapper;
import com.winterchen.model.*;
import com.winterchen.service.user.CollectionService;
import com.winterchen.service.user.DevService;
import com.winterchen.service.user.DevTypeService;
import com.winterchen.util.EntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/dev")
@CrossOrigin
public class DevController {

    @Autowired
    private DevService devService;
    @Autowired
    private DevTypeService devTypeService;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private DevElementMapper devElementMapper;

    @ResponseBody
    @GetMapping("test")
    @UserLoginToken
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
    @UserLoginToken
    public ResultMessage<String> addDevOrDevElement(@RequestBody DevElement devElement) {
        ResultMessage<String> booleanResultMessage = new ResultMessage<>();
        try {
            String s = EntityUtil.checkObjectField(devElement);
            if(!"true".equals(s)){
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setMesg(s);
                booleanResultMessage.setValue("");
                return booleanResultMessage;
            }
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            if ("2".equals(devElement.getType())) {
                //插入组件
                if (devElement.getDev_parent_element_id() == null || "".equals(devElement.getDev_parent_element_id().trim())) {
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setValue("");
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
                DevElement devElement1 = new DevElement();
                devElement1.setDev_element_name(devElement.getDev_element_name());
                devElement1.setEnterprise_id(devElement.getEnterprise_id());
                List<DevElement> devElements = devService.queryByEntity(devElement1);
                if(devElements!=null && devElements.size()>0){
                    booleanResultMessage.setStatuscode("401");
                    booleanResultMessage.setMesg("已存在同名的设备");
                    booleanResultMessage.setValue("");
                    return booleanResultMessage;
                }
                devElement.setDev_element_id(id);
                devElement.setDev_type_id(id);
                devService.insertEntity(devElement);
            }
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setValue(id);
            booleanResultMessage.setMesg("添加成功");
            return booleanResultMessage;
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setValue("");
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
    @UserLoginToken
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
    @UserLoginToken
    public ResultMessage<Boolean> deleteDevType(@RequestBody DevElement devElement) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            //删除当前值、删除子节点
            return  devService.deleteElementAndSubElements(devElement.getDev_element_id());
        } catch (Exception e) {
            e.printStackTrace();
            booleanResultMessage.setStatuscode("501");
            booleanResultMessage.setMesg("服务端错误：" + e.toString());
            booleanResultMessage.setValue(false);
            return booleanResultMessage;
        }
    }


    /**
     * 获取树中某一企业的设备
     */
    @ResponseBody
    @PostMapping("/getTopElementsByEnterpriseId")
    @UserLoginToken
    public ResultMessage<List<DevElement>> getTopElements(@RequestBody Enterprise enterprise) {
        ResultMessage<List<DevElement>> resultMessage = new ResultMessage<>();
        try {
            DevElement devElement = new DevElement();
            devElement.setType("1");
            devElement.setEnterprise_id(enterprise.getEnterprise_id());
            List<DevElement> devTypeElements = devService.queryByEntity(devElement);
            for(DevElement dev:devTypeElements){
                dev.setHasSon(devElementMapper.sunCount(dev.getDev_element_id())>0);
            }
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
     * 获取树中的下级列表，组件
     */
    @ResponseBody
    @PostMapping("/getSubElementsByParentId")
    @UserLoginToken
    public ResultMessage<List<DevElement>> getSubElementsByParentId(@RequestBody DevElement devElement) {
        ResultMessage<List<DevElement>> resultMessage = new ResultMessage<>();
        try {
            List<DevElement> devTypeElements = devService.queryByEntity(devElement);
            for(DevElement dev:devTypeElements){
                dev.setHasSon(devElementMapper.sunCount(dev.getDev_element_id())>0);
            }
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
    @UserLoginToken
    public ResultMessage<Boolean> loadDevTypeTemplate(@RequestBody DevInputRequest devInputRequest) {
        ResultMessage<Boolean> booleanResultMessage = new ResultMessage<>();
        try {
            DevTypeElement devTypeElement = new DevTypeElement();
            devTypeElement.setDev_type_id(devInputRequest.getType_element_id());
            List<DevTypeElement> devTypeElements = devTypeService.queryByEntity(devTypeElement);
            if(devTypeElements==null){
                booleanResultMessage.setStatuscode("401");
                booleanResultMessage.setValue(false);
                booleanResultMessage.setMesg("不存在的设备类型，请校验");
                return booleanResultMessage;
            }
            String appendId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
            String parentId;
            String elementName;
            for (DevTypeElement devType : devTypeElements) {
                if (devType.getDev_parent_element_id() != null) {
                    parentId = devType.getDev_parent_element_id() + appendId;
                } else {
                    parentId = null;
                }
                if ("1".equals(devType.getType())) {
                    elementName = devInputRequest.getDev_element_name();
                } else {
                    elementName = devType.getDev_element_name();
                }
                DevElement devElement = new DevElement(devType.getDev_element_id() + appendId, parentId, elementName,
                        devType.getDev_type_id() + appendId, devType.getType(), devInputRequest.getEnterprise_id());
                devService.insertEntity(devElement);
            }
            booleanResultMessage.setStatuscode("200");
            booleanResultMessage.setValue(true);
            booleanResultMessage.setMesg("导入成功");
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
     * 复制设备
     */
    @ResponseBody
    @PostMapping("/copyDev")
    @UserLoginToken
    @Transactional
    public ResultMessage<Boolean> copyDev(@RequestBody DevInputRequest devInputRequest){
        ResultMessage<Boolean> result = new ResultMessage<>();
        try {
            DevElement queryDev = new DevElement();
            queryDev.setDev_element_id(devInputRequest.getType_element_id());
            queryDev.setType("1");
            List<DevElement> devElements1 = devService.queryByEntity(queryDev);
            if(devElements1==null || devElements1.size()==0){
                result.setStatuscode("401");
                result.setMesg("错误，只能复制设备");
                result.setValue(false);
                return result;
            }
            DevElement devElement = new DevElement();
            devElement.setDev_type_id(devInputRequest.getType_element_id());
            List<DevElement> devElements = devService.queryByEntity(devElement);
            String replaceId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
            String parentId;
            String elementName;
            for (DevElement dev:devElements) {
                if (dev.getDev_parent_element_id() != null) {
                    parentId = replaceId+dev.getDev_parent_element_id().substring(6);
                    elementName = dev.getDev_element_name();
                } else {
                    parentId = null;
                    elementName = devInputRequest.getDev_element_name();
                }
                DevElement devCopy = new DevElement(replaceId+dev.getDev_element_id().substring(6),
                        parentId, elementName, replaceId+dev.getDev_type_id().substring(6),
                        dev.getType(), devInputRequest.getEnterprise_id());
                devService.insertEntity(devCopy);
                CollectionManager collectionManager = new CollectionManager();
                collectionManager.setDev_element_id(dev.getDev_element_id());
                List<CollectionManager> collectionManagers = collectionService.queryByEntity(collectionManager);
                if(collectionManagers!=null && collectionManagers.size()>0){
                    for(CollectionManager collection:collectionManagers){
                        String id = UUID.randomUUID().toString().replaceAll("-", "");
                        CollectionManager insertCollection = new CollectionManager();
                        BeanUtils.copyProperties(collection,insertCollection,"collection_id",
                                "dev_element_id","status","update_time","measure_id","channel_id");
                        insertCollection.setCollection_id(id);
                        insertCollection.setDev_element_id(replaceId+dev.getDev_element_id().substring(6));
                        insertCollection.setStatus("0");
                        insertCollection.setUpdate_time(new Date());
                        insertCollection.setMeasure_id(null);
                        insertCollection.setChannel_id(null);
                    }
                }
            }
            result.setStatuscode("200");
            result.setValue(true);
            result.setMesg("复制成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatuscode("501");
            result.setMesg("服务端错误：" + e.toString());
            result.setValue(false);
            return result;
        }
    }

}
