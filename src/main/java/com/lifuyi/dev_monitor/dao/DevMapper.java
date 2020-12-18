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
}
