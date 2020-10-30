package com.winterchen.util;

import com.winterchen.annotation.NotBlank;
import com.winterchen.model.CollectionManager;
import com.winterchen.model.CollectionManagerHttp;
import com.winterchen.model.Measure;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static String listToString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        if(list!=null && list.size()>0){
            for(String str:list){
                stringBuilder.append(str).append(",");
            }
            String s = stringBuilder.substring(0, stringBuilder.length() - 1).toString();
            return s;
        }else {
            return null;
        }
    }

    public static List<String> stringToList(String str){
        String[] array = str.split(",");
        List<String> transferedList = new ArrayList<>();
        Arrays.stream(array).forEach(arr -> transferedList.add(arr));
        return transferedList;
//        if(str!=null){
//            return Arrays.asList(str.split(","));
//        }else {
//            return null;
//        }
    }

    public static void main(String[] args) throws IllegalAccessException {
//        ArrayList<String> list = new ArrayList<>();
        List<String> list = Arrays.asList("cdc,dsd,uiuj".split(","));
//        list.add("wdefrdfw");
        System.out.println(listToString(list));
        list.remove("cdc");
        List<String> strings = stringToList(listToString(list));
        System.out.println("---------");

    }
}
