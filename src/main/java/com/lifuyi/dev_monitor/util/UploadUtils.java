package com.lifuyi.dev_monitor.util;

import com.lifuyi.dev_monitor.model.ResultMessage;
import com.lifuyi.dev_monitor.model.dev.Motor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.*;

public class UploadUtils {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
//    public final static String url = "D:\\log";
    public final static String url = "/home/ubuntu/code/dev_test/pic";

    public static ResultMessage<String> uploadPic(MultipartFile imgFile) {
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

    public static String previewPic(String imgId) {
        String prefix = "data:image/"+imgId.substring(imgId.lastIndexOf(".") + 1)+";base64,";
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(url + File.separator + imgId);
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            base64 = new String(Base64.encodeBase64(bytes), "UTF-8");
//            System.out.println("将文件["+imgId+"]转base64字符串:"+base64);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  prefix+base64;
    }

    /**
     * 使用list自带的sort方法先进性排序，然后转成toString去判断两个集合是否相等
     * 方法6
     */
    private static boolean checkDiffrent5(List<String> list, List<String> list1) {
        long st = System.nanoTime();
        System.out.println("消耗时间为： " + (System.nanoTime() - st));
        list.sort(Comparator.comparing(String::hashCode));
        list1.sort(Comparator.comparing(String::hashCode));
        return list.toString().equals(list1.toString());
    }

    public static void main(String[] args) {
        String[] a={"s","a","c"};
        List<String> str1=Arrays.asList(a);
        String[] b={"c","a","s"};
        List<String> str2=Arrays.asList(a);
        System.out.println(checkDiffrent5(str1,str2));
    }

}
