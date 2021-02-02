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
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/dev")
@CrossOrigin
@Api(description = "设备管理")
public class DevController {

    public final static String url = "/home/ubuntu/code/dev_test/pic";
    @Autowired
    private DevService devService;


    @PostMapping("/getType")
    @ApiOperation(value = "获取设备类型", notes = "获取设备类型")
    public ResultMessage<List<DevType>> getType() {
        return new ResultMessage<List<DevType>>("200", "查询成功", devService.getType());
    }


    @PostMapping("/uploadPic")
    @ApiResponses({@ApiResponse(code = 200, message = "图片id"), @ApiResponse(code = 401, message = "上传失败")})
    public ResultMessage<String> uploadPic(@RequestParam("imgFile") MultipartFile imgFile) {
        return UploadUtils.uploadPic(imgFile);
    }


    @GetMapping(value = "/previewPic")
    @ApiOperation(value = "预览图片", notes = "输入id,返回base64")
    public String previewPic(@RequestParam("imgId") String imgId) {
        return UploadUtils.previewPic(imgId);
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
//            File file = new File(url + File.separator + imgId);
//            in = new FileInputStream(file);
//            response.setContentType("image/" + imgId.substring(imgId.lastIndexOf(".") + 1));
//            OutputStream out = response.getOutputStream();
//            byte[] buff = new byte[100];
//            int rc = 0;
//            while ((rc = in.read(buff, 0, 100)) > 0) {
//                out.write(buff, 0, rc);
//            }
//            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping(value = "/insertOrUpdateDev")
    @ApiOperation(value = "插入或者更新设备，不传id字段是插入，传是更新,object是根据设备类型的其他字段", notes = "返回的mesg是id")
    public ResultMessage<Boolean> insertOrUpdateDev(@RequestBody BaseDevEntity baseDevEntity) {
        return devService.insertOrUpdateDev(baseDevEntity);
    }


    @PostMapping(value = "/getDevByPages")
    @ApiOperation(value = "带分页的查找设备", notes = "")
    public ResultMessage<PageInfo<BaseDevPagesRsp>> getDevByPages(@RequestBody BaseDevEntityReq baseDevEntityReq) {
        return devService.getDevByPages(baseDevEntityReq);
    }


    @PostMapping(value = "/getDevList")
    @ApiOperation(value = "不带分页的查找设备", notes = "")
    public ResultMessage<List<BaseDevPagesRsp>> getDevList(@RequestBody BaseDevEntity baseDevEntity) {
        return devService.getDevList(baseDevEntity);
    }

    @PostMapping(value = "/deleteById")
    @ApiOperation(value = "根据id删除设备", notes = "")
    public ResultMessage<Boolean> deleteById(@RequestParam("id") List<String> id) {
        try {
            for(String s:id){
                devService.deleteById(s);
            }
            return new ResultMessage<Boolean>("200","success",true);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMessage<Boolean>("500",e.getMessage(),false);
        }
    }

}
