package com.tenacity.free.project.manager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.util
 * @file_name PropertiesUtils.java
 * @description properties文件工具类
 * @create 2018-02-25 21:12
 */
public class PropertiesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * @class_name PropertiesUtils.java
     * @method loadProperties
     * @description 配置文件名称
     * @author free.zhang
     * @date 2018/2/25/025 21:15
     * @param '[propertyFileName]
     * @return java.util.Properties
     */
    public static Properties loadProperties(String propertyFileName){
        Properties properties = null;
        InputStreamReader input = null;
        URL url = null;
        try{
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            url = loader.getResource(propertyFileName);
            input = new InputStreamReader(new FileInputStream(url.getPath()),"UTF-8");
            properties = new Properties();
            properties.load(input);
        }catch (IOException e){
            LOGGER.error("load{} error!",propertyFileName);
        }finally {
            if (null != input){
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close {} error!",propertyFileName);
                }
            }
        }
        return properties;
    }

    /**
     * @class_name PropertiesUtils.java
     * @method getString
     * @description  从properties当中获取值
     * @author free.zhang
     * @date 2018/2/25/025 21:28
     * @param '[fileName, key]
     * @return java.lang.String
     */
    public static String getString(String fileName,String key){
        Properties properties = loadProperties(fileName);
        if (null != properties){
            return properties.getProperty(key);
        }
        return null;
    }

}
