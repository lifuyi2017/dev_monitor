package com.winterchen.service.user;

import com.winterchen.model.DevTypeElement;

import java.util.List;

public interface DevTypeService {
    List<DevTypeElement> queryByEntity(DevTypeElement queryParent) throws Exception;

    void insertEntity(DevTypeElement devTypeElement) throws Exception;

    void editDevTypeOrDevElement(DevTypeElement devTypeElement) throws Exception;

    void deleteElementAndSubElements(String dev_element_id) throws Exception;
}
