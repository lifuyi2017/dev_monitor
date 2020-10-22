package com.winterchen.service.user;

import com.winterchen.model.Measure;
import com.winterchen.model.ResultMessage;

import java.util.List;

public interface MeasureService {
    void updateById(Measure measure) throws Exception;

    void insertEntity(Measure measure) throws Exception;

    List<Measure> queryByEntity(Measure measure) throws Exception;

    ResultMessage<Boolean> deleteById(String measure_id);

//    void deleteByNetworkId(String net_id);

    void updateNetWorkIdNull(String network_id);
}
