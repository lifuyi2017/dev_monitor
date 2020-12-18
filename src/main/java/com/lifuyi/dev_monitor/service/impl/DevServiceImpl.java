package com.lifuyi.dev_monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.dao.DevMapper;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.dev.*;
import com.lifuyi.dev_monitor.model.dev.Req.BaseDevEntityReq;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevPagesRsp;
import com.lifuyi.dev_monitor.service.DevService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("devService")
public class DevServiceImpl implements DevService {

    @Resource
    private DevMapper devMapper;

    @Override
    public List<DevType> getType() {
        DevType devType = new DevType();
        return devMapper.getType(devType);
    }

    @Override
    public ResultMessage<Boolean> insertOrUpdateDev(BaseDevEntity baseDevEntity) {
        String id= UUID.randomUUID().toString().replaceAll("-","");
        BaseDevEntity dev=devMapper.getBaseDevByCode(baseDevEntity.getDev_code());
        if((baseDevEntity.getId()==null && dev!=null) ||
                (baseDevEntity.getId()!=null && dev!=null && !dev.getId().equals(baseDevEntity.getId()))){
            return new ResultMessage<Boolean>("401","设备编码重复",false);
        }
        switch (baseDevEntity.getDev_type_id()){
            case 1:
                Motor motor= (Motor) baseDevEntity.getObject();
                if(baseDevEntity.getId()==null){
                    baseDevEntity.setId(id);
                    motor.setId(id);
                }
                devMapper.insertOrUpdateBase(baseDevEntity);
                devMapper.insertOrUpdateMotor(motor);
                return new ResultMessage<Boolean>("200",baseDevEntity.getId(),true);
            case 2:
                WaterPump waterPump= (WaterPump) baseDevEntity.getObject();
                if(baseDevEntity.getId()==null){
                    baseDevEntity.setId(id);
                    waterPump.setId(id);
                }
                devMapper.insertOrUpdateBase(baseDevEntity);
                devMapper.insertOrUpdateWaterPump(waterPump);
                return new ResultMessage<Boolean>("200",baseDevEntity.getId(),true);
            case 3:
                Fan fan= (Fan) baseDevEntity.getObject();
                if(baseDevEntity.getId()==null){
                    baseDevEntity.setId(id);
                    fan.setId(id);
                }
                devMapper.insertOrUpdateBase(baseDevEntity);
                devMapper.insertOrUpdateFan(fan);
                return new ResultMessage<Boolean>("200",baseDevEntity.getId(),true);
            default:
                return new ResultMessage<Boolean>("401","设备类型错误",false);
        }
    }

    /**
     * 带分页的查找
     * @param req
     * @return
     */
    @Override
    public ResultMessage<PageInfo<BaseDevEntity>> getDevByPages(BaseDevEntityReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<BaseDevPagesRsp> list=devMapper.getBaseListByEntity(req.getBaseDevEntity());

    }
}
