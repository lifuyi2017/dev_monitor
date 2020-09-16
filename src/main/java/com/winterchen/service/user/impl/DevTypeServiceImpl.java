package com.winterchen.service.user.impl;

import com.winterchen.dao.DevTypeMapper;
import com.winterchen.model.DevTypeElement;
import com.winterchen.service.user.DevTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "devTypeService")
public class DevTypeServiceImpl implements DevTypeService {

    @Autowired
    private DevTypeMapper devTypeMapper;

    @Override
    public List<DevTypeElement> queryByEntity(DevTypeElement queryParent) throws Exception {
        return devTypeMapper.queryByEntity(queryParent);
    }

    @Override
    public void insertEntity(DevTypeElement devTypeElement) throws Exception{
        devTypeMapper.insertEntity(devTypeElement);
    }

    @Override
    public void editDevTypeOrDevElement(DevTypeElement devTypeElement) throws Exception {
        devTypeMapper.editById(devTypeElement);
    }

    @Override
    public void deleteElementAndSubElements(String dev_element_id) throws Exception{
        devTypeMapper.deleteByElementId(dev_element_id);
        devTypeMapper.deleteByParentId(dev_element_id);
    }


}
