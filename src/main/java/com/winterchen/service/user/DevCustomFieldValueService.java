package com.winterchen.service.user;

import com.winterchen.model.DevCustomField;

import java.util.Map;

public interface DevCustomFieldValueService {
    void deleteByElementId(String dev_element_id) throws Exception;


    void deleteByCustomFieldId(String dev_type_custom_field_id) throws Exception;

    void insertCustomValue(String id, String dev_element_id, Map<String, String> customFieldValue) throws Exception;

    Map<String, Map<String, String>> getValueListByElementId(String dev_element_id) throws Exception;

    void deleteByValueId(String dev_type_field_value_id) throws Exception;

    void updateByValueId(String dev_element_id, String dev_type_field_value_id, Map<String, String> customFieldValue) throws Exception;
}
