package com.winterchen.service.user;

import com.winterchen.model.DevElement;
import com.winterchen.model.ResultMessage;

import java.util.List;

public interface DevService {
    List<DevElement> queryByEntity(DevElement queryParent) throws Exception;

    void insertEntity(DevElement devElement) throws Exception;

    void editDevOrDevElementById(DevElement devElement) throws Exception;

    ResultMessage<Boolean> deleteElementAndSubElements(String dev_element_id) throws Exception;

    ResultMessage<Boolean> deleteByEnterpriseId(String id) throws Exception;
}
