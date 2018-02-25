package com.tenacity.free.project.manager.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.util
 * @file_name JacksonUtils.java
 * @description JSON转换
 * @create 2018-02-25 21:32
 */
public class JacksonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * @param '[obj]
     * @return java.lang.String
     * @class_name JacksonUtils.java
     * @method writeValueAsString
     * @description bean、array、List、Map --> json
     * @author free.zhang
     * @date 2018/2/25/025 21:33
     */
    public static String writeValueAsString(Object obj) {
        try {
            return getInstance().writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param '[jsonStr, clazz]
     * @return T
     * @class_name JacksonUtils.java
     * @method readValue
     * @description string --> bean、Map、List(array)
     * @author free.zhang
     * @date 2018/2/25/025 21:33
     */
    public static <T> T readValue(String jsonStr, Class<T> clazz) {

        try {
            return getInstance().readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValueRefer(String jsonStr, TypeReference typeReference) {
        try {
            return getInstance().readValue(jsonStr, typeReference);        // new TypeReference<T>() { }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("aaa", "111");
            map.put("bbb", "222");
            String json = writeValueAsString(map);
            System.out.println(json);
            System.out.println(readValue(json, Map.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
