package com.springboot.base.utils;

/**
 * @author loki
 */
public class LokiFileUtils {
    public static String getFileSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
