package com.winterchen.dao;

public interface DevCustomFieldValueMapper {
    void deleteByElementId(String dev_element_id);

    void deleteByCustomFieldId(String dev_type_custom_field_id);
}
