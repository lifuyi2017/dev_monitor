package com.winterchen.dao;

import com.winterchen.model.DevTypeElement;

import java.util.List;

public interface DevTypeMapper {
    List<DevTypeElement> queryByEntity(DevTypeElement queryParent);

    void insertEntity(DevTypeElement devTypeElement);

    void editById(DevTypeElement devTypeElement);

    void deleteByElementId(String dev_element_id);

    void deleteByParentId(String dev_element_id);

    Integer sonCount(String dev_element_id);
}
