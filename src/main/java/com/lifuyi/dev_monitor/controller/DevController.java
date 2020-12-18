package com.lifuyi.dev_monitor.controller;

import com.github.pagehelper.PageInfo;
import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.dev.BaseDevEntity;
import com.lifuyi.dev_monitor.model.dev.DevType;
import com.lifuyi.dev_monitor.model.dev.Req.BaseDevEntityReq;
import com.lifuyi.dev_monitor.model.dev.Resp.BaseDevPagesRsp;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;
import com.lifuyi.dev_monitor.service.DevService;
import com.lifuyi.dev_monitor.util.UploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/dev")
@CrossOrigin
@Api(description = "设备管理")
public class DevController {

    @Autowired
    private DevService devService;

    /**
     * 查询类型
     */
    @PostMapping("/getType")
    @ApiOperation(value = "获取设备类型", notes = "获取设备类型")
    public ResultMessage<List<DevType>> getType(){
        return new ResultMessage<List<DevType>>("200","查询成功",devService.getType());
    }

    /**
     * 上传图片
     */
    @PostMapping("/uploadPic")
    @ApiResponses({ @ApiResponse(code = 200, message = "图片id"),@ApiResponse(code = 401, message = "上传失败") })
    public ResultMessage<String> uploadPic(@RequestParam("imgFile") MultipartFile imgFile) {
        return UploadUtils.uploadPic(imgFile);
    }

    /**
     * 下载图片
     */
    @GetMapping(value = "/previewPic")
    @ApiOperation(value = "预览图片", notes = "输入id,返回base64")
    public ResultMessage<String> previewPic(@RequestParam("imgId") String imgId) {
       return  UploadUtils.previewPic(imgId);
    }


    /**
     * 插入或者更新设备
     */
    @PostMapping(value = "/insertOrUpdateDev")
    @ApiOperation(value = "插入或者更新设备，不传id字段是插入，传是更新,object是根据设备类型的其他字段", notes = "返回的mesg是id")
    public ResultMessage<Boolean> insertOrUpdateDev(@RequestBody BaseDevEntity baseDevEntity){
        return devService.insertOrUpdateDev(baseDevEntity);
    }


    @PostMapping(value = "/getDevByPages")
    @ApiOperation(value = "带分页的查找设备", notes = "")
    public ResultMessage<PageInfo<BaseDevPagesRsp>> getDevByPages(@RequestBody BaseDevEntityReq baseDevEntityReq){
        return devService.getDevByPages(baseDevEntityReq);
    }











}
