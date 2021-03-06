package com.winterchen.dao;

import com.winterchen.model.Measure;

import java.util.List;

public interface MeasureMapper {
    void updateById(Measure measure);

    void insertEntity(Measure measure);

    List<Measure> queryByEntity(Measure measure);

    void deleteById(String measure_id);

    void deleteByEnterpriseId(String id);

    void deleteNetWorkId(String net_id);

    List<String> getByNetWorkId(String net_id);

    void updateByEnterpriseIdNull(String id);

    void updateNetWorkIdNull(String network_id);
}
