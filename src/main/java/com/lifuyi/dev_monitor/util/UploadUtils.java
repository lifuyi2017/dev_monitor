package com.lifuyi.dev_monitor.util;

import com.lifuyi.dev_monitor.model.ResultMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String url = "D:\\log";

    public static ResultMessage<String> uploadPic(MultipartFile imgFile){
        if (imgFile.isEmpty()) {
            return new ResultMessage<String>("401", "不能为空", "失败");
        }
        String filename = imgFile.getOriginalFilename();
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        filename = UUID.randomUUID().toString().replace("-", "") + "." + prefix;
        try {
            // 构建真实的文件路径
            File newFile = new File(url + File.separator + filename);
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
