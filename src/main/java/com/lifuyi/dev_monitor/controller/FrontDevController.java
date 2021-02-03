package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.annotation.UserLoginToken;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.collect.WorkShopDev;
import com.lifuyi.dev_monitor.model.collect.resp.ShopDevGroup;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevPagesRsp;
import com.lifuyi.dev_monitor.model.role.Resp.EnterPriseAuthor;
import com.lifuyi.dev_monitor.service.DevService;
import com.lifuyi.dev_monitor.service.RoleService;
import com.lifuyi.dev_monitor.service.WorkShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/front/dev")
@CrossOrigin
@Api(description = "前台系统接口---与后台系统能打通部分")
public class FrontDevController {

    public final static String url = "/home/ubuntu/code/dev_test/pic";
    @Resource
    private RoleService roleService;
    @Resource
    private WorkShopService workShopService;
    @Autowired
    private DevService devService;

    @PostMapping("/getRoleAuthority")
    @ApiOperation(value = "获取角色权限",  notes = "用于展示设备树，展示flag是1的部分就行")
    public ResultMessage<List<EnterPriseAuthor>> getRoleAuthority(@RequestParam("roleId")
                                                                  @ApiParam(value = "角色id",required = true) String roleId){
        return roleService.getRoleAuthority(roleId);
    }

    @PostMapping(value = "/getDevByDevGroupId")
    @ApiOperation(value = "获取设备组下的设备", notes = "")
    public ResultMessage<List<WorkShopDev>> getDevByDevGroupId(@RequestParam("id")
                                                               @ApiParam(value = "设备组",required = true) String id){
        return workShopService.getDevByDevGroupId(id);
    }

    @PostMapping(value = "/getWorkShopDevList")
    @ApiOperation(value = "获取车间下面的设备", notes = "")
    public ResultMessage<List<WorkShopDev>> getWorkShopDevList(@RequestParam("workshopId")
                                                               @ApiParam(value = "车间id",required = true) String workshopId){
        return workShopService.getWorkShopDevList(workshopId);
    }

    @PostMapping(value = "/getWorkShopDevGroupList")
    @ApiOperation(value = "获取车间下面的设备组", notes = "")
    public ResultMessage<List<ShopDevGroup>> getWorkShopDevGroupList(@RequestParam("workshopId")
                                                                     @ApiParam(value = "车间id",required = true) String workshopId){
        return workShopService.getWorkShopDevGroupList(workshopId);
    }

    @GetMapping(value = "/getPic")
    @ApiOperation(value = "预览图片", notes = "输入id,返回base64")
    public void getPic(@RequestParam("imgId") String imgId, HttpServletResponse response) {
        InputStream in = null;
        try {
            if(!StringUtils.isBlank(imgId)){
                File file = new File(url + File.separator + imgId);
                in = new FileInputStream(file);
                response.setContentType("image/" + imgId.substring(imgId.lastIndexOf(".") + 1));
                OutputStream out = response.getOutputStream();
                byte[] buff = new byte[100];
                int rc = 0;
                while ((rc = in.read(buff, 0, 100)) > 0) {
                    out.write(buff, 0, rc);
                }
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/getDevById")
    @ApiOperation(value = "通过设备id查询设备信息", notes = "")
    public ResultMessage<BaseDevPagesRsp> getDevById(@RequestParam("id") String id ) {
        BaseDevEntity baseDevEntity=new BaseDevEntity();
        baseDevEntity.setId(id);
        List<BaseDevPagesRsp> value = devService.getDevList(baseDevEntity).getValue();
        if(value!=null && value.size()>0){
            return new ResultMessage<BaseDevPagesRsp>("200","success",value.get(0));
        }else {
            return new ResultMessage<BaseDevPagesRsp>("500","error,设备或已被删除",null);
        }
    }






}
