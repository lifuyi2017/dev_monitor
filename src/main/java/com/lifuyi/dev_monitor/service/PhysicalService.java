package com.lifuyi.dev_monitor.service;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.physical.Physical;
import com.lifuyi.dev_monitor.model.physical.req.PhysicalReq;
import com.lifuyi.dev_monitor.model.physical.resp.PhysicalResp;

import java.util.List;

public interface PhysicalService {
    ResultMessage<Boolean> addOrUpdatePhysical(Physical physical);

    ResultMessage<PageInfo<PhysicalResp>> getPhysicalPages(PhysicalReq req);

    ResultMessage<List<PhysicalResp>> getPhysicalList(Physical physical);
}
