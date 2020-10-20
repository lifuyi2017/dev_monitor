package com.winterchen.service.user;

import com.winterchen.model.DevElement;

import java.util.List;

public interface DevService {
    List<DevElement> queryByEntity(DevElement queryParent) throws Exception;

    void insertEntity(DevElement devElement) throws Exception;

    void editDevOrDevElementById(DevElement devElement) throws Exception;

    void deleteElementAndSubElements(String dev_element_id) throws Exception;

    void deleteByEnterpriseId(String id) throws Exception;
}
