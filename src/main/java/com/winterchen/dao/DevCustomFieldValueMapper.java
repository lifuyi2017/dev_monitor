package com.winterchen.dao;

import com.winterchen.model.DevCustomFieldValue;

import java.util.List;

public interface DevCustomFieldValueMapper {
    void deleteByElementId(String dev_element_id);

    void deleteByCustomFieldId(String dev_type_custom_field_id);

    void insert(DevCustomFieldValue devCustomFieldValue);

    List<DevCustomFieldValue> getByEntity(DevCustomFieldValue devCustomFieldValue);

    void deleteByValueId(String dev_type_field_value_id);

    void updateById(DevCustomFieldValue value);

    List<DevCustomFieldValue> getByIdList(List<String> valueIds);
}
