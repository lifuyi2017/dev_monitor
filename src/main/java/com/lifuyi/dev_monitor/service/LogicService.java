package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.logic.req.LogicQueryReq;
import com.lifuyi.dev_monitor.model.logic.req.LogicSaveReq;
import com.lifuyi.dev_monitor.model.logic.resp.LogicResp;

public interface LogicService {
    ResultMessage<String> addOrUpdateLogic(LogicSaveReq logicSaveReq);

    ResultMessage<PageInfo<LogicResp>> getLogicPages(LogicQueryReq logicQueryReq);
}
