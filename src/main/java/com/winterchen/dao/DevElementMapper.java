package com.winterchen.dao;

import com.winterchen.model.DevElement;

import java.util.List;

public interface DevElementMapper {
    List<DevElement> queryByEntity(DevElement queryParent);

    void insertEntity(DevElement devElement);

    void editDevOrDevElementById(DevElement devElement);

    void deleteByElementId(String dev_element_id);

    void deleteByParentId(String dev_element_id);

    void deleteByEnterpriseId(String id);

    Integer sunCount(String dev_element_id);

    List<String> getTypeIdByEnterpriseId(String id);
}
