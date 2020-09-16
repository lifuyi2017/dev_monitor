package com.winterchen.service.user.impl;

import com.winterchen.dao.DevCustomFieldValueMapper;
import com.winterchen.service.user.DevCustomFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "devCustomFieldValueService")
public class DevCustomFieldValueServiceImpl implements DevCustomFieldValueService {

    @Autowired
    private DevCustomFieldValueMapper devCustomFieldValueMapper;

    @Override
    public void deleteByElementId(String dev_element_id) throws Exception {
        devCustomFieldValueMapper.deleteByElementId(dev_element_id);
    }

}
