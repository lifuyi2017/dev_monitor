package com.winterchen.service.user.impl;

import com.winterchen.dao.DevCustomFieldMapper;
import com.winterchen.model.DevCustomField;
import com.winterchen.service.user.DevCustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "devCustomFieldService")
public class DevCustomFieldServiceImpl implements DevCustomFieldService {

    @Autowired
    private DevCustomFieldMapper devCustomFieldMapper;

    @Override
    public List<DevCustomField> getCustomFieldsByEntity(DevCustomField devCustomField) throws Exception {
        return devCustomFieldMapper.getCustomFieldsByEntity(devCustomField);
    }

    @Override
    public void insertEntity(DevCustomField customField) throws Exception {
        devCustomFieldMapper.insertEntity(customField);
    }

    @Override
    public void deleteEntity(DevCustomField customField) throws Exception{
        devCustomFieldMapper.deleteById(customField.getDev_type_custom_field_id());
    }

    @Override
    public void deleteByElementId(String dev_element_id) {
        devCustomFieldMapper.deleteByElementId(dev_element_id);
    }
}
