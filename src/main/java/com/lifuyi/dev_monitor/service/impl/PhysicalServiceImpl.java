package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.PhysicalMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.physical.Physical;
import com.lifuyi.dev_monitor.model.physical.req.PhysicalReq;
import com.lifuyi.dev_monitor.model.physical.resp.PhysicalResp;
import com.lifuyi.dev_monitor.service.PhysicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("physicalService")
public class PhysicalServiceImpl implements PhysicalService {

    @Resource
    private PhysicalMapper physicalMapper;

    @Override
    public ResultMessage<Boolean> addOrUpdatePhysical(Physical physical) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        Physical codeP=physicalMapper.getByCode(physical.getCode());
        if((physical.getId()==null && codeP!=null) ||
                (physical.getId()!=null && codeP!=null && !codeP.getId().equals(physical.getId()))){
            return new ResultMessage<Boolean>("401","物理节点编码重复",false);
        }
        if(physical.getId()==null){
            physical.setId(id);
        }else {
            Physical query = new Physical();
            query.setId(physical.getId());
        }
        physicalMapper.addOrUpdatePhysical(physical);
        return new ResultMessage<Boolean>("200",physical.getId(),true);
    }

    @Override
    public ResultMessage<PageInfo<PhysicalResp>> getPhysicalPages(PhysicalReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<PhysicalResp> respList=physicalMapper.getPageByEntity(req.getPhysical());
        PageInfo<PhysicalResp> physicalRespPageInfo = new PageInfo<>(respList);
        return new ResultMessage<PageInfo<PhysicalResp>>("200","查询成功",physicalRespPageInfo);
    }

    @Override
    public ResultMessage<List<PhysicalResp>> getPhysicalList(Physical physical) {
        return new ResultMessage<List<PhysicalResp>>("200","查询成功",physicalMapper.getPageByEntity(physical));
    }
}
