package com.winterchen.service.user;

import com.winterchen.model.DevCustomField;

public interface DevCustomFieldValueService {
    void deleteByElementId(String dev_element_id) throws Exception;


    void deleteByCustomFieldId(String dev_type_custom_field_id) throws Exception;
}
