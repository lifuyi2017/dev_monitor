package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.MeasureMapper;
import com.winterchen.model.CollectionManager;
import com.winterchen.model.Measure;
import com.winterchen.model.ResultMessage;
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
    @Resource
    private CollectionManagerMapper collectionManagerMapper;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private LogicRelationMapper logicRelationMapper;

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
    public ResultMessage<Boolean> deleteById(String measure_id) {
        ResultMessage<Boolean> result=new ResultMessage<>();
        //判断是否有与物理节点相关的采集
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.setMeasure_id(measure_id);
        Integer integer = collectionManagerMapper.queryOnCollectCountByEntity(collectionManager);
        if(integer>0){
            result.setValue(false);
            result.setMesg("有与此物理节点相关的采集任务没有停止，请先停止");
            result.setStatuscode("401");
            return result;
        }
        result=channelService.deleteByMeasureId(measure_id);
        if(!result.getValue()){
            return result;
        }
        measureMapper.deleteById(measure_id);
        logicRelationMapper.deleteByMeasureId(measure_id);
        collectionManagerMapper.deleteByMeasureId(measure_id);
        return result;
    }

//    @Override
//    public void deleteByNetworkId(String net_id) {
//        List<String> measureIds=measureMapper.getByNetWorkId(net_id);
//        for(String m_id:measureIds){
//            channelService.deleteByMeasureId(m_id);
//            collectionManagerMapper.deleteByMeasureId(m_id);
//        }
//        measureMapper.deleteNetWorkId(net_id);
//    }

    @Override
    public void updateNetWorkIdNull(String network_id) {
        measureMapper.updateNetWorkIdNull(network_id);
    }
}
