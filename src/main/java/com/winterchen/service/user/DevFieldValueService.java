package com.winterchen.service.user;

import com.winterchen.model.DevFieldValue;
import com.winterchen.model.ResultMessage;

import java.util.List;

public interface DevFieldValueService {
    ResultMessage addFieldValue(DevFieldValue devFieldValue);

    ResultMessage<List<DevFieldValue>> getFieldValueByDevId(Integer devId);
}
