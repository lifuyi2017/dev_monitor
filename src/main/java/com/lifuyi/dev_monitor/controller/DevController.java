package com.lifuyi.dev_monitor.controller;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.dev.DevType;
import com.lifuyi.dev_monitor.model.enterprise.Resp.EnterpriseTypeResp;
import com.lifuyi.dev_monitor.service.DevService;
import com.lifuyi.dev_monitor.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/dev")
@CrossOrigin
public class DevController {

    @Autowired
    private DevService devService;

    /**
     * 查询类型
     */
//    @ResponseBody
//    @PostMapping("/getType")
//    public ResultMessage<List<DevType>> getType(){
//
//    }

    /**
     * 上传图片
     */
    @ResponseBody
    @PostMapping("/uploadPic")
    public ResultMessage<String> uploadPic(@RequestParam("imgFile") MultipartFile imgFile) {
        if (imgFile.isEmpty()) {
            return new ResultMessage<String>("401", "不能为空", "失败");
        }
        String filename = imgFile.getOriginalFilename();
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        filename = UUID.randomUUID().toString().replace("-", "") + "." + prefix;
        try {
            // 构建真实的文件路径
            File newFile = new File("D:\\log" + File.separator + filename);
            System.err.println(newFile.getAbsolutePath());
            // 上传图片到 -》 “绝对路径”
            imgFile.transferTo(newFile);
            return new ResultMessage<String>("200", "上传成功", filename);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMessage<String>("401", "上传异常:" + e.getMessage(), "失败");
        }
    }




}
