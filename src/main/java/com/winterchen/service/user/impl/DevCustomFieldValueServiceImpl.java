package com.winterchen.service.user.impl;

import com.winterchen.dao.DevCustomFieldMapper;
import com.winterchen.dao.DevCustomFieldValueMapper;
import com.winterchen.model.CustomValue;
import com.winterchen.model.DevCustomField;
import com.winterchen.model.DevCustomFieldValue;
import com.winterchen.service.user.DevCustomFieldValueService;
import com.winterchen.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;


@Service(value = "devCustomFieldValueService")
public class DevCustomFieldValueServiceImpl implements DevCustomFieldValueService {

    private static final Logger logger = LoggerFactory.getLogger(DevCustomFieldValueServiceImpl.class);

    @Autowired
    private DevCustomFieldValueMapper devCustomFieldValueMapper;
    @Autowired
    private DevCustomFieldMapper devCustomFieldMapper;


    @Override
    public void deleteByElementId(String dev_element_id) throws Exception {
        devCustomFieldValueMapper.deleteByElementId(dev_element_id);
    }

    @Override
    public void deleteByCustomFieldId(String dev_type_custom_field_id) throws Exception {
        devCustomFieldValueMapper.deleteByCustomFieldId(dev_type_custom_field_id);
    }

    @Override
    public void insertCustomValue(String id, String dev_element_id, Map<String, String> customFieldValue) throws Exception {
        for (Map.Entry<String, String> entry : customFieldValue.entrySet()) {
            DevCustomFieldValue devCustomFieldValue = new DevCustomFieldValue();
            devCustomFieldValue.setDev_element_id(dev_element_id);
            devCustomFieldValue.setDev_type_field_value_id(id);
            DevCustomField devCustomFieldQuery = new DevCustomField();
            devCustomFieldQuery.setDev_element_id(dev_element_id);
            devCustomFieldQuery.setDev_type_field_name(entry.getKey());
            List<DevCustomField> customFieldsByEntity = devCustomFieldMapper.getCustomFieldsByEntity(devCustomFieldQuery);
            devCustomFieldValue.setDev_type_custom_field_id(customFieldsByEntity.get(0).getDev_type_custom_field_id());
            setCustomValue(devCustomFieldValue, customFieldsByEntity.get(0).getDev_type_field_type(), entry.getValue());
            devCustomFieldValueMapper.insert(devCustomFieldValue);
        }
    }

    /**
     * 获取用户自定义字段的值
     */
    @Override
    public Map<String, Map<String, String>> getValueListByElementId(String dev_element_id, List<String> valueIds) throws Exception {
        //获取自定义字段
        DevCustomField devCustomField = new DevCustomField();
        devCustomField.setDev_element_id(dev_element_id);
        List<DevCustomField> customFieldsByEntity = devCustomFieldMapper.getCustomFieldsByEntity(devCustomField);
        List<String> fieldNames = new ArrayList<>();
        for (DevCustomField devCustomField1 : customFieldsByEntity) {
            fieldNames.add(devCustomField1.getDev_type_field_name());
        }
        Map<String, Map<String, String>> map = new HashMap<>();
//        DevCustomFieldValue devCustomFieldValue = new DevCustomFieldValue();
//        devCustomFieldValue.setDev_element_id(dev_element_id);
        List<DevCustomFieldValue> devCustomFieldValueList = devCustomFieldValueMapper.getByIdList(valueIds);
        Map<String, String> valueMap;
        CustomValue value;
        for (DevCustomFieldValue customFieldValue : devCustomFieldValueList) {
            if (map.containsKey(customFieldValue.getDev_type_field_value_id())) {
                valueMap = map.get(customFieldValue.getDev_type_field_value_id());
            }else {
                valueMap = new HashMap<>();
                for(String str:fieldNames){
                    valueMap.put(str,null);
                }
            }
            value = getCustomValue(customFieldValue);
            valueMap.put(value.getName(), value.getValue());
            map.put(customFieldValue.getDev_type_field_value_id(), valueMap);
        }
        return map;
    }

    @Override
    public void deleteByValueId(String dev_type_field_value_id) throws Exception {
        devCustomFieldValueMapper.deleteByValueId(dev_type_field_value_id);
    }

    @Override
    public void updateByValueId(String dev_element_id, String dev_type_field_value_id, Map<String, String> customFieldValue) throws Exception {
        for (Map.Entry<String, String> entry : customFieldValue.entrySet()) {
            logger.info(entry.getKey() + "====================================>>>>>>>" + entry.getValue());
            DevCustomField devCustomField = new DevCustomField();
            devCustomField.setDev_type_field_name(entry.getKey());
            devCustomField.setDev_element_id(dev_element_id);
            List<DevCustomField> customFieldsByEntity = devCustomFieldMapper.getCustomFieldsByEntity(devCustomField);
            DevCustomFieldValue value = new DevCustomFieldValue();
            value.setDev_element_id(dev_element_id);
            value.setDev_type_field_value_id(dev_type_field_value_id);
            value.setDev_type_custom_field_id(customFieldsByEntity.get(0).getDev_type_custom_field_id());
            setCustomValue(value, customFieldsByEntity.get(0).getDev_type_field_type(), entry.getValue());
            DevCustomFieldValue devCustomFieldValue = new DevCustomFieldValue();
            devCustomFieldValue.setDev_type_custom_field_id(value.getDev_type_custom_field_id());
            devCustomFieldValue.setDev_element_id(value.getDev_element_id());
            devCustomFieldValue.setDev_type_field_value_id(value.getDev_type_field_value_id());
            List<DevCustomFieldValue> byEntity = devCustomFieldValueMapper.getByEntity(devCustomFieldValue);
            if (byEntity != null && byEntity.size() > 0) {
                devCustomFieldValueMapper.updateById(value);
            } else {
                devCustomFieldValueMapper.insert(value);
            }
        }
    }

    private CustomValue getCustomValue(DevCustomFieldValue customFieldValue) {
        String value;
        DevCustomField devCustomField = new DevCustomField();
        devCustomField.setDev_type_custom_field_id(customFieldValue.getDev_type_custom_field_id());
        List<DevCustomField> list = devCustomFieldMapper.getCustomFieldsByEntity(devCustomField);
        if ("1".equals(list.get(0).getDev_type_field_type()) || "2".equals(list.get(0).getDev_type_field_type())) {
            value = customFieldValue.getValue_string();
        } else if ("3".equals(list.get(0).getDev_type_field_type())) {
            if (customFieldValue.getValue_date() != null) {
                value = DateUtil.formatDateTime(customFieldValue.getValue_date());
            } else {
                value = null;
            }
        } else {
            if (customFieldValue.getValue_blob() != null) {
                value = Base64.getEncoder().encodeToString(customFieldValue.getValue_blob());
            } else {
                value = null;
            }
        }
        return new CustomValue(list.get(0).getDev_type_field_name(), value);
    }

    private void setCustomValue(DevCustomFieldValue devCustomFieldValue, String dev_type_field_type, String value) throws ParseException {
        logger.info(dev_type_field_type + "================>>>>" + value);
        if ("1".equals(dev_type_field_type) || "2".equals(dev_type_field_type)) {
            devCustomFieldValue.setValue_string(value);
        } else if ("3".equals(dev_type_field_type)) {
            if (value != null && !"".equals(value)) {
                System.out.println(value);
                Date date = DateUtil.parseDate(value);
                devCustomFieldValue.setValue_date(date);
            }
        } else {
            if (value != null && !"".equals(value)) {
                devCustomFieldValue.setValue_blob(Base64.getDecoder().decode(value));
            }
        }
    }


}
