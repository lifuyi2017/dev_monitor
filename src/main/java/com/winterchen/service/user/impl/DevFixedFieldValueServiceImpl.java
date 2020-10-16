package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.winterchen.dao.DevFixedFieldValueMapper;
import com.winterchen.model.DevFixedFieldValue;
import com.winterchen.service.user.DevFixedFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service(value = "devFixedFieldValueService")
public class DevFixedFieldValueServiceImpl implements DevFixedFieldValueService {

    @Autowired
    private DevFixedFieldValueMapper devFixedFieldValueMapper;


    @Override
    public void deleteByElementId(String dev_element_id) throws Exception {
        devFixedFieldValueMapper.deleteByElementId(dev_element_id);
    }

    /**
     * 添加一条记录
     */
    @Override
    public void insertFixedValue(String dev_element_id, DevFixedFieldValue devFixedFieldValue) throws Exception {
        devFixedFieldValue.setDev_element_id(dev_element_id);
        if(devFixedFieldValue.getDev_type_pic_str()!=null){
            devFixedFieldValue.setDev_type_pic(Base64.getDecoder().decode(devFixedFieldValue.getDev_type_pic_str()));
        }else {
            devFixedFieldValue.setDev_type_pic(null);
        }
        devFixedFieldValueMapper.insertValue(devFixedFieldValue);
    }

    @Override
    public List<DevFixedFieldValue> getValueListByElementId(String dev_element_id) throws Exception {
        DevFixedFieldValue devFixedFieldValue = new DevFixedFieldValue();
        devFixedFieldValue.setDev_element_id(dev_element_id);
        List<DevFixedFieldValue> list=devFixedFieldValueMapper.queryForEntity(devFixedFieldValue);
        for(DevFixedFieldValue fieldValue:list){
            if(fieldValue.getDev_type_pic()!=null){
                fieldValue.setDev_type_pic_str(Base64.getEncoder().encodeToString(fieldValue.getDev_type_pic()));
            }
        }
        return list;
    }

    @Override
    public void deleteByValueId(String dev_type_field_value_id)  throws Exception{
        devFixedFieldValueMapper.deleteByValueId(dev_type_field_value_id);
    }

    @Override
    public void updateByValueId(DevFixedFieldValue devFixedFieldValue) throws Exception {
        if(devFixedFieldValue.getDev_type_pic_str()!=null){
            devFixedFieldValue.setDev_type_pic(Base64.getDecoder().decode(devFixedFieldValue.getDev_type_pic_str()));
        }
        devFixedFieldValueMapper.updateByValueId(devFixedFieldValue);
    }
}
