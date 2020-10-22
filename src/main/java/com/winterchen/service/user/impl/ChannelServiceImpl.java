package com.winterchen.service.user.impl;

import com.winterchen.dao.ChannelMapper;
import com.winterchen.dao.CollectionManagerMapper;
import com.winterchen.dao.LogicRelationMapper;
import com.winterchen.model.Channel;
import com.winterchen.model.CollectionManager;
import com.winterchen.model.ResultMessage;
import com.winterchen.service.user.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private LogicRelationMapper logicRelationMapper;
    @Resource
    private CollectionManagerMapper collectionManagerMapper;

    @Override
    public void updateById(Channel channel) throws Exception {
        channelMapper.updateById(channel);
    }

    @Override
    public void insertEntity(Channel channel) throws Exception {
        channelMapper.insertEntity(channel);
    }

    @Override
    public List<Channel> queryByEntity(Channel channel) throws Exception{
        return channelMapper.queryByEntity(channel);
    }

    @Override
    @Transactional
    public ResultMessage<Boolean> deleteById(String channel_id) throws Exception{
        ResultMessage<Boolean> result=new ResultMessage<>();
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.setChannel_id(channel_id);
        Integer integer = collectionManagerMapper.queryOnCollectCountByEntity(collectionManager);
        if(integer>0){
            result.setValue(false);
            result.setMesg("有与此物理节点下属的采集通道相关的采集任务没有停止，请先停止");
            result.setStatuscode("401");
            return result;
        }
        channelMapper.deleteById(channel_id);
        logicRelationMapper.deleteByChannelId(channel_id);
        collectionManagerMapper.deleteByChannelId(channel_id);
        result.setValue(true);
        result.setMesg("删除成功");
        result.setStatuscode("200");
        return result;
    }

    @Override
    @Transactional
    public ResultMessage<Boolean> deleteByMeasureId(String m_id) {
        ResultMessage<Boolean> result=new ResultMessage<>();
        List<String> chIds=channelMapper.getByMeasureId(m_id);
        if(chIds!=null && chIds.size()>0){
            for(String chId:chIds){
                CollectionManager collectionManager = new CollectionManager();
                collectionManager.setChannel_id(chId);
                Integer integer = collectionManagerMapper.queryOnCollectCountByEntity(collectionManager);
                if(integer>0){
                    result.setValue(false);
                    result.setMesg("有与此物理节点下属的采集通道相关的采集任务没有停止，请先停止");
                    result.setStatuscode("401");
                    return result;
                }
            }
        }
        if(chIds!=null && chIds.size()>0){
            for(String chId:chIds){
                logicRelationMapper.deleteByChannelId(chId);
                collectionManagerMapper.deleteByChannelId(chId);
            }
        }
        channelMapper.deleteByMeasureId(m_id);
        logicRelationMapper.deleteByMeasureId(m_id);
        result.setValue(true);
        result.setMesg("删除成功");
        result.setStatuscode("200");
        return result;
    }
}
