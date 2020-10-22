package com.winterchen.util;

import com.winterchen.annotation.NotBlank;
import com.winterchen.model.Measure;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityUtil {

    public static String checkObjectField(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] field = clazz.getDeclaredFields();
        for(Field f : field){
            if(f.isAnnotationPresent(NotBlank.class)){
                f.setAccessible(true);
                if(f.get(obj)==null || StringUtils.isBlank(f.get(obj).toString())){
                    NotBlank annotation = f.getAnnotation(NotBlank.class);
                    return annotation.message();
                }
            }
        }
        return "true";
    }

    public static void main(String[] args) throws IllegalAccessException {
        //获得AnnotationDemo中的字段与注解的详细信息
        Measure measure = new Measure();
        measure.setMeasure_id("00000000000000");
        measure.setMeasure_code("888888888888");
        measure.setMeasure_name("ggggggg");
        measure.setMeasure_channel_num("333");
        System.out.println(checkObjectField(measure));
/*        //1.类加载
        Class<? extends Measure> clazz = measure.getClass();
        //2.获取字段
        Field[] field = clazz.getDeclaredFields();
        for (Field f : field) {
            if(f.isAnnotationPresent(NotBlank.class)){
                f.setAccessible(true);
                if(f.get(measure)==null || StringUtils.isBlank(f.get(measure).toString())){
                    NotBlank annotation = f.getAnnotation(NotBlank.class);
                    System.out.println(annotation.message());
                }
            }
        }*/

    }
}
