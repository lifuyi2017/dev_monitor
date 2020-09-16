package com.winterchen.service.user.impl;

import com.winterchen.dao.DevFixedFieldValueMapper;
import com.winterchen.service.user.DevFixedFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "devFixedFieldValueService")
public class DevFixedFieldValueServiceImpl implements DevFixedFieldValueService {

    @Autowired
    private DevFixedFieldValueMapper devFixedFieldValueMapper;


    @Override
    public void deleteByElementId(String dev_element_id) throws Exception {
        devFixedFieldValueMapper.deleteByElementId(dev_element_id);
    }
}