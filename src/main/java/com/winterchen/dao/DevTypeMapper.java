package com.winterchen.dao;

import com.winterchen.model.DevTypeElement;

import java.util.List;

public interface DevTypeMapper {
    List<DevTypeElement> queryByEntity(DevTypeElement queryParent);

    void insertEntity(DevTypeElement devTypeElement);

    void editById(DevTypeElement devTypeElement);
}
