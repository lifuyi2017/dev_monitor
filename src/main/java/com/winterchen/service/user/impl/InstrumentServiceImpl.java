package com.winterchen.service.user.impl;

import com.winterchen.dao.InstrumentMapper;
import com.winterchen.model.Network;
import com.winterchen.service.user.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("instrumentService")
public class InstrumentServiceImpl implements InstrumentService {

    @Autowired
    private InstrumentMapper instrumentMapper;

    @Override
    public void updateById(Network network) throws Exception {
        instrumentMapper.updateById(network);
    }

    @Override
    public void insertEntity(Network network) throws Exception{
        instrumentMapper.insert(network);
    }
}
