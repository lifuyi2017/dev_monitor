package com.winterchen.dao;

import com.winterchen.model.DevCustomField;

import java.util.List;

public interface DevCustomFieldMapper {
    List<DevCustomField> getCustomFieldsByEntity(DevCustomField devCustomField);

    void insertEntity(DevCustomField customField);

    void deleteById(String dev_type_custom_field_id);
}
