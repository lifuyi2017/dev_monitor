package com.winterchen.service.user.impl;

import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.model.Measure;
import com.winterchen.service.user.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("measureService")
public class MeasureServiceImpl implements MeasureService {

    @Autowired
    private MeasureMapper measureMapper;
    @Autowired
    private LogicRelationMapper logicRelationMapper;
    @Resource
    private CollectionManagerMapper collectionManagerMapper;

    @Override
    public void updateById(Measure measure) throws Exception {
        measureMapper.updateById(measure);
    }

    @Override
    public void insertEntity(Measure measure) throws Exception {
        measureMapper.insertEntity(measure);
    }

    @Override
    public List<Measure> queryByEntity(Measure measure) throws Exception {
        return measureMapper.queryByEntity(measure);
    }

    @Override
    public void deleteNetWorkById(String measure_id) {
        measureMapper.deleteNetWorkById(measure_id);
        collectionManagerMapper.deleteByMeasureId(measure_id);
        logicRelationMapper.deleteByMeasureId(measure_id);
    }
}
