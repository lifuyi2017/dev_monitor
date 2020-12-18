package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.model.dev.DevType;
import com.lifuyi.dev_monitor.model.dev.Req.BaseDevEntityReq;

import java.util.List;

public interface DevService {
    List<DevType> getType();

    ResultMessage<Boolean> insertOrUpdateDev(BaseDevEntity baseDevEntity);

    ResultMessage<PageInfo<BaseDevEntity>> getDevByPages(BaseDevEntityReq req);
}
