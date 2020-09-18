package com.winterchen.service.user;

import com.winterchen.model.DevCustomField;

import java.util.List;

public interface DevCustomFieldService {
    List<DevCustomField> getCustomFieldsByEntity(DevCustomField devCustomField) throws Exception;

    void insertEntity(DevCustomField customField) throws Exception;

    void deleteEntity(DevCustomField customField) throws Exception;
}
