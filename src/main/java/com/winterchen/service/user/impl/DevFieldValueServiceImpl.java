package com.winterchen.service.user.impl;

import com.winterchen.dao.DevFieldMapper;
import com.winterchen.dao.DevFieldValueMapper;
import com.winterchen.dao.DevRelationMapper;
import com.winterchen.model.*;
import com.winterchen.service.user.DevFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.text.SimpleDateFormat;

@Service(value = "devFieldValueService")
public class DevFieldValueServiceImpl implements DevFieldValueService {

    @Autowired
    private DevFieldValueMapper devFieldValueMapper;
    @Autowired
    private DevFieldMapper devFieldMapper;
    @Autowired
    private DevRelationMapper devRelationMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 添加一行设备参数值，@Transactional是开启事务
     *
     * @param devFieldValue
     * @return
     */
    @Override
    @Transactional
    public ResultMessage addFieldValue(DevFieldValue devFieldValue) {
        ResultMessage resultMessage = new ResultMessage();
        try {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            for (Map.Entry<String, FieldValue> entry : devFieldValue.getValueMap().entrySet()) {
                DevField devField = devFieldMapper.getFieldById(devFieldValue.getDevId() + "-" + entry.getKey());
                DevOneFieldValue devOneFieldValue;
                if ("1".equals(devField.getField_type())) {
                    devOneFieldValue = new DevOneFieldValue(id, devFieldValue.getTopId(), devFieldValue.getDevId(),
                            devFieldValue.getDevId() + "-" + entry.getKey(), entry.getValue().getValue(), null, null);
                } else if ("2".equals(devField.getField_type())) {
                    if (entry.getValue().getValue() == null || "".equals(entry.getValue().getValue().trim())) {
                        devOneFieldValue = new DevOneFieldValue(id, devFieldValue.getTopId(), devFieldValue.getDevId(),
                                devFieldValue.getDevId() + "-" + entry.getKey(), null, null, null);
                    } else {
                        Date date = sdf.parse(entry.getValue().getValue());
                        devOneFieldValue = new DevOneFieldValue(id, devFieldValue.getTopId(), devFieldValue.getDevId(),
                                devFieldValue.getDevId() + "-" + entry.getKey(), null, date, null);
                    }
                } else {
                    if (entry.getValue().getValue() == null || "".equals(entry.getValue().getValue().trim())) {
                        devOneFieldValue = new DevOneFieldValue(id, devFieldValue.getTopId(), devFieldValue.getDevId(),
                                devFieldValue.getDevId() + "-" + entry.getKey(), null, null, null);
                    } else {
                        byte[] decode = Base64.getDecoder().decode(entry.getValue().getValue());
                        devOneFieldValue = new DevOneFieldValue(id, devFieldValue.getTopId(), devFieldValue.getDevId(),
                                devFieldValue.getDevId() + "-" + entry.getKey(), null, null, decode);
                    }
                }
                devFieldValueMapper.insert(devOneFieldValue);
            }
            resultMessage.setSucess(true);
            resultMessage.setMesg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage.setSucess(false);
            resultMessage.setMesg("添加失败：" + e.toString());
        }
        return resultMessage;
    }

    /**
     * 根据设备id获取设备参数值
     *
     * @param devId
     * @return
     */
    @Override
    public ResultMessage<List<DevFieldValue>> getFieldValueByDevId(Integer devId) {
        ResultMessage<List<DevFieldValue>> listResultMessage = new ResultMessage<>();
        List<DevFieldValue> devFieldValueList=new LinkedList<>();
        try {
            //根据devId获取所有的uuid
            List<String> ids = devFieldValueMapper.getIdByDevId(devId);
            //根据uuid和devId获取所有的数据
            for (String id : ids) {
                DevOneFieldValue devOneFieldValue = new DevOneFieldValue();
                DevRelation devRelation = devRelationMapper.getRelationByDevId(devId);
                devOneFieldValue.setDev_top_id(devRelation.getTop_id());
                devOneFieldValue.setDev_id(devId);
                devOneFieldValue.setId(id);
                Map<String, FieldValue> mapValue = new HashMap<>();
                List<DevOneFieldValue> devOneFieldValue1 = devFieldValueMapper.query(devOneFieldValue);
                for (DevOneFieldValue fieldValue : devOneFieldValue1) {
                    DevField devField = devFieldMapper.getFieldById(fieldValue.getField_id());
                    mapValue.put(devField.getField_name(), castToValue(fieldValue, devField.getField_type()));
                }
                DevFieldValue devFieldValue = new DevFieldValue(devRelation.getTop_id(), devId, mapValue);
                devFieldValueList.add(devFieldValue);
            }
            listResultMessage.setSucess(true);
            listResultMessage.setMesg("查询成功");
            listResultMessage.setValue(devFieldValueList);
        }catch (Exception e){
            e.printStackTrace();
            listResultMessage.setSucess(false);
            listResultMessage.setMesg("查询失败:"+e.toString());
        }
        return listResultMessage;
    }

    private FieldValue castToValue(DevOneFieldValue fieldValue, String field_type) {
        FieldValue result = new FieldValue();
        result.setValueType(field_type);
        if ("1".equals(field_type)) {
            result.setValue(fieldValue.getField_string());
        } else if ("2".equals(field_type)) {
            if (fieldValue.getField_date() != null) {
                result.setValue(sdf.format(fieldValue.getField_date()));
            }
        } else {
            if (fieldValue.getField_blob() != null) {
                result.setValue(Base64.getEncoder().encodeToString(fieldValue.getField_blob()));
            }
        }
        return result;
    }

}
