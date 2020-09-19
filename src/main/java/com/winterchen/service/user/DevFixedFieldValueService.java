package com.winterchen.service.user;

import com.winterchen.model.DevFixedFieldValue;

import java.util.List;

public interface DevFixedFieldValueService {
    void deleteByElementId(String dev_element_id) throws Exception;

    void insertFixedValue(String dev_element_id, DevFixedFieldValue devFixedFieldValue) throws Exception;

    List<DevFixedFieldValue> getValueListByElementId(String dev_element_id) throws Exception;

    void deleteByValueId(String dev_type_field_value_id)  throws Exception;

    void updateByValueId(DevFixedFieldValue devFixedFieldValue) throws Exception;
}
