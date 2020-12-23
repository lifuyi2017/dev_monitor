package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.physical.Physical;
import com.lifuyi.dev_monitor.model.physical.req.PhysicalReq;
import com.lifuyi.dev_monitor.model.physical.resp.PhysicalResp;
import com.lifuyi.dev_monitor.service.PhysicalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/physical")
@Api(description = "仪器管理-物理管理")
@CrossOrigin
public class PhysicalController {

    @Resource
    private PhysicalService physicalService;

    @PostMapping("/addOrUpdateNetWork")
    @ApiOperation(value = "插入或者更新物理节点",  notes = "返回的mesg是id")
//    @UserLoginToken
    public ResultMessage<Boolean> addOrUpdateNetWork(@RequestBody Physical physical) {
        return physicalService.addOrUpdatePhysical(physical);
    }

    @PostMapping("/getPhysicalPages")
    @ApiOperation(value = "获取分页列表",  notes = "")
    public ResultMessage<PageInfo<PhysicalResp>> getPhysicalPages(@RequestBody PhysicalReq req) {
        return physicalService.getPhysicalPages(req);
    }

    @PostMapping("/getPhysicalList")
    @ApiOperation(value = "获取列表",  notes = "")
    public ResultMessage<List<PhysicalResp>> getPhysicalList(@RequestBody Physical physical) {
        return physicalService.getPhysicalList(physical);
    }

}
