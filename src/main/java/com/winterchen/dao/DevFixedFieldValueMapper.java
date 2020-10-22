package com.winterchen.dao;

import com.winterchen.model.DevFixedFieldValue;
import com.winterchen.model.Enterprise;

import java.util.List;

public interface DevFixedFieldValueMapper {
    void deleteByElementId(String dev_element_id);

    void insertValue(DevFixedFieldValue devFixedFieldValue);

    List<DevFixedFieldValue> queryForEntity(DevFixedFieldValue devFixedFieldValue);

    void deleteByValueId(String dev_type_field_value_id);

    void updateByValueId(DevFixedFieldValue devFixedFieldValue);

    void deleteByEnterpriseId(String id);


    void updateByOperateEnterpriseId(Enterprise enterprise);

    void updateByServiceEnterpriseId(Enterprise enterprise);

    void updateByProductionEnterpriseId(Enterprise enterprise);

    void updateByOperateEnterpriseIdNull(String id);
    void updateByServiceEnterpriseIdNull(String id);
    void updateByProductionEnterpriseIdNull(String id);
}
