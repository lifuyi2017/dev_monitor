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
    public ResultMessage<PageInfo<BaseDevPagesRsp>> getDevByPages(BaseDevEntityReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<BaseDevPagesRsp> list=devMapper.getBaseListByEntity(req.getBaseDevEntity());
        PageInfo<BaseDevPagesRsp> baseDevPagesRspPageInfo = new PageInfo<BaseDevPagesRsp>(list);
        setDevObject(list,req.getBaseDevEntity());
        baseDevPagesRspPageInfo.setList(list);
        return new ResultMessage<PageInfo<BaseDevPagesRsp>>("200","查询成功",baseDevPagesRspPageInfo);
    }

    @Override
    public ResultMessage<List<BaseDevPagesRsp>> getDevList(BaseDevEntity baseDevEntity) {
        List<BaseDevPagesRsp> list = devMapper.getBaseListByEntity(baseDevEntity);
        setDevObject(list,baseDevEntity);
        return new ResultMessage<List<BaseDevPagesRsp>>("200","查询成功",list);
    }

    /**
     * 设置object
     * @param list
     * @param baseDevEntity
     */
    private void setDevObject(List<BaseDevPagesRsp> list, BaseDevEntity baseDevEntity) {
        switch (baseDevEntity.getDev_type_id()){
            case 1:
                for(BaseDevPagesRsp resp:list){
                    Motor motor=devMapper.getMotorById(resp.getId());
                    resp.setObject(motor);
                }
                break;
            case 2:
                for(BaseDevPagesRsp resp:list){
                    WaterPump waterPump=devMapper.getWaterPumpById(resp.getId());
                    resp.setObject(waterPump);
                }
                break;
            case 3:
                for(BaseDevPagesRsp resp:list){
                    Fan fan=devMapper.getFanById(resp.getId());
                    resp.setObject(fan);
                }
                break;
            default:
                for(BaseDevPagesRsp resp:list){
                    switch (resp.getDev_type_name()){
                        case "电机":
                            Motor motor=devMapper.getMotorById(resp.getId());
                            resp.setObject(motor);
                            break;
                        case "水泵":
                            WaterPump waterPump=devMapper.getWaterPumpById(resp.getId());
                            resp.setObject(waterPump);
                            break;
                        case "风机":
                            Fan fan=devMapper.getFanById(resp.getId());
                            resp.setObject(fan);
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
    }



}
