package com.lifuyi.dev_monitor.util;

import com.lifuyi.dev_monitor.model.ResultMessage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class UploadUtils {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String url = "D:\\log";
//    public final static String url = "/home/ubuntu/code/dev_test/pic";

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

    public static ResultMessage<String> previewPic(String imgId) {
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(url + File.separator + imgId);
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            base64 = new String(Base64.encodeBase64(bytes),"UTF-8");
//            System.out.println("将文件["+imgId+"]转base64字符串:"+base64);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage<String>("501","失败:"+e.getMessage(),null);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResultMessage<String>("200","成功",base64);
    }
}
