package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.model.Measure;
import com.winterchen.service.user.ChannelService;
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
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ChannelService channelService;

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
    public void deleteById(String measure_id) {
        measureMapper.deleteNetWorkById(measure_id);
        channelService.deleteByMeasureId(measure_id);
        collectionManagerMapper.deleteByMeasureId(measure_id);
//        logicRelationMapper.deleteByMeasureId(measure_id);
//        channelMapper.deleteByMeasureId(measure_id);
//        logicRelationMapper.deleteByMeasureId(measure_id);
    }

    @Override
    public void deleteByNetworkId(String net_id) {
        List<String> measureIds=measureMapper.getByNetWorkId(net_id);
        for(String m_id:measureIds){
            channelService.deleteByMeasureId(m_id);
            collectionManagerMapper.deleteByMeasureId(m_id);
        }
        measureMapper.deleteNetWorkId(net_id);

    }
}
