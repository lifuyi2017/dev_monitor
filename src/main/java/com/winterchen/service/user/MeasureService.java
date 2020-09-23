package com.winterchen.service.user;

import com.winterchen.model.Measure;

import java.util.List;

public interface MeasureService {
    void updateById(Measure measure) throws Exception;

    void insertEntity(Measure measure) throws Exception;

    List<Measure> queryByEntity(Measure measure) throws Exception;

    void deleteNetWorkById(String measure_id);
}
