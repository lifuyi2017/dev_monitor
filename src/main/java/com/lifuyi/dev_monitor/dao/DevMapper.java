package com.lifuyi.dev_monitor.dao;

import com.lifuyi.dev_monitor.model.dev.*;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevPagesRsp;

import java.util.List;

public interface DevMapper {
    List<DevType> getType(DevType devType);

    void insertOrUpdateBase(BaseDevEntity baseDevEntity);

    BaseDevEntity getBaseDevByCode(String dev_code);

    void insertOrUpdateMotor(Motor motor);

    void insertOrUpdateWaterPump(WaterPump waterPump);

    void insertOrUpdateFan(Fan fan);

    List<BaseDevPagesRsp> getBaseListByEntity(BaseDevEntity baseDevEntity);

    Motor getMotorById(String id);

    WaterPump getWaterPumpById(String id);

    Fan getFanById(String id);

    void deleteMotorById(String id);

    void deleteWaterById(String id);

    void deleteFanById(String id);

    void deleteById(String id);
}
