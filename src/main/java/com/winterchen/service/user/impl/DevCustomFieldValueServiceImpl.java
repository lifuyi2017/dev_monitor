package com.winterchen.service.user.impl;

import com.winterchen.dao.DevCustomFieldMapper;
import com.winterchen.dao.DevCustomFieldValueMapper;
import com.winterchen.model.CustomValue;
import com.winterchen.model.DevCustomField;
import com.winterchen.model.DevCustomFieldValue;
import com.winterchen.service.user.DevCustomFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service(value = "devCustomFieldValueService")
public class DevCustomFieldValueServiceImpl implements DevCustomFieldValueService {

    @Autowired
    private DevCustomFieldValueMapper devCustomFieldValueMapper;
    @Autowired
    private DevCustomFieldMapper devCustomFieldMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        DevCustomFieldValue devCustomFieldValue = new DevCustomFieldValue();
        devCustomFieldValue.setDev_element_id(dev_element_id);
        devCustomFieldValue.setDev_type_field_value_id(id);
        for(Map.Entry<String,String> entry:customFieldValue.entrySet()){
            DevCustomField devCustomFieldQuery = new DevCustomField();
            devCustomFieldQuery.setDev_element_id(dev_element_id);
            devCustomFieldQuery.setDev_type_field_name(entry.getKey());
            List<DevCustomField> customFieldsByEntity = devCustomFieldMapper.getCustomFieldsByEntity(devCustomFieldQuery);
            devCustomFieldValue.setDev_type_custom_field_id(customFieldsByEntity.get(0).getDev_type_custom_field_id());
            setCustomValue(devCustomFieldValue,customFieldsByEntity.get(0).getDev_type_field_type(),entry.getValue());
            devCustomFieldValueMapper.insert(devCustomFieldValue);
        }
    }

    /**
     * 获取用户自定义字段的值
     */
    @Override
    public Map<String, Map<String, String>> getValueListByElementId(String dev_element_id) throws Exception {
        Map<String, Map<String, String>> map = new HashMap<>();
        DevCustomFieldValue devCustomFieldValue = new DevCustomFieldValue();
        devCustomFieldValue.setDev_element_id(dev_element_id);
        List<DevCustomFieldValue> devCustomFieldValueList=devCustomFieldValueMapper.getByEntity(devCustomFieldValue);
        Map<String, String> valueMap;
        CustomValue value;
        for(DevCustomFieldValue customFieldValue:devCustomFieldValueList){
            valueMap=new HashMap<>();
            if(map.containsKey(customFieldValue.getDev_type_field_value_id())){
                valueMap=map.get(customFieldValue.getDev_type_field_value_id());
            }
            value=getCustomValue(customFieldValue);
            valueMap.put(value.getName(),value.getValue());
            map.put(customFieldValue.getDev_type_field_value_id(),valueMap);
        }
        return map;
    }

    @Override
    public void deleteByValueId(String dev_type_field_value_id) throws Exception {
        devCustomFieldValueMapper.deleteByValueId(dev_type_field_value_id);
    }

    @Override
    public void updateByValueId(String dev_element_id, String dev_type_field_value_id, Map<String, String> customFieldValue) throws Exception{
        for(Map.Entry<String,String> entry:customFieldValue.entrySet()){
            DevCustomField devCustomField = new DevCustomField();
            devCustomField.setDev_type_field_name(entry.getKey());
            devCustomField.setDev_element_id(dev_element_id);
            List<DevCustomField> customFieldsByEntity = devCustomFieldMapper.getCustomFieldsByEntity(devCustomField);
            DevCustomFieldValue value = new DevCustomFieldValue();
            value.setDev_element_id(dev_element_id);
            value.setDev_type_field_value_id(dev_type_field_value_id);
            value.setDev_type_custom_field_id(customFieldsByEntity.get(0).getDev_type_custom_field_id());
            setCustomValue(value,customFieldsByEntity.get(0).getDev_type_field_type(),entry.getValue());
            devCustomFieldValueMapper.updateById(value);
        }
    }

    private CustomValue getCustomValue(DevCustomFieldValue customFieldValue) {
        String value;
        DevCustomField devCustomField = new DevCustomField();
        devCustomField.setDev_type_custom_field_id(customFieldValue.getDev_type_custom_field_id());
        List<DevCustomField> list= devCustomFieldMapper.getCustomFieldsByEntity(devCustomField);
        if("1".equals(list.get(0).getDev_type_field_type()) || "2".equals(list.get(0).getDev_type_field_type())){
            value=customFieldValue.getValue_string();
        }else if("3".equals(list.get(0).getDev_type_field_type())){
            if(customFieldValue.getValue_date()!=null){
                value=sdf.format(customFieldValue.getValue_date());
            }else {
                value=null;
            }
        }else {
            if(customFieldValue.getValue_blob()!=null){
                value=Base64.getEncoder().encodeToString(customFieldValue.getValue_blob());
            }else {
                value=null;
            }
        }
        return new CustomValue(list.get(0).getDev_type_field_name(),value);
    }

    private void setCustomValue(DevCustomFieldValue devCustomFieldValue, String dev_type_field_type, String value) throws ParseException {
        if("1".equals(dev_type_field_type) || "2".equals(dev_type_field_type)){
            devCustomFieldValue.setValue_string(value);
        }else if("3".equals(dev_type_field_type)){
            if (value != null && !"".equals(value)) {
                devCustomFieldValue.setValue_date(sdf.parse(value));
            }
        }else {
            if (value != null && !"".equals(value)) {
                devCustomFieldValue.setValue_blob(Base64.getDecoder().decode(value));
            }
        }
    }


}
