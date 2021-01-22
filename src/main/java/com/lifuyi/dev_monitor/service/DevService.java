package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.model.dev.DevType;
import com.lifuyi.dev_monitor.model.dev.Req.BaseDevEntityReq;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevPagesRsp;

import java.util.List;

public interface DevService {
    List<DevType> getType();

    ResultMessage<Boolean> insertOrUpdateDev(BaseDevEntity baseDevEntity);

    ResultMessage<PageInfo<BaseDevPagesRsp>> getDevByPages(BaseDevEntityReq req);

    ResultMessage<List<BaseDevPagesRsp>> getDevList(BaseDevEntity baseDevEntity);

    void deleteById(String id);
}
