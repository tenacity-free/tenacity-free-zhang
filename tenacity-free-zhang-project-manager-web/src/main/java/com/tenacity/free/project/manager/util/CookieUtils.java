package com.tenacity.free.project.manager.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.util
 * @file_name CookieUtils.java
 * @description Cookie工具类
 * @create 2018-02-25 21:33
 */
public class CookieUtils {

    // 默认缓存时间,单位/秒, 2H
    private static final int COOKIE_MAX_AGE = 60 * 60 * 2;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";

    /**
     * @class_name CookieUtils.java
     * @method set
     * @description  保存
     * @author free.zhang
     * @date 2018/2/25/025 21:34
     * @param '[response, key, value, ifRemember]
     * @return void
     */
    public static void set(HttpServletResponse response, String key, String value, boolean ifRemember) {
        int age = COOKIE_MAX_AGE;
        if (ifRemember) {
            age = COOKIE_MAX_AGE;
        } else {
            age = -1;
        }

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(age);				// Cookie过期时间,单位/秒
        cookie.setPath(COOKIE_PATH);		// Cookie适用的路径
        response.addCookie(cookie);
    }

    /**
     * @class_name CookieUtils.java
     * @method set
     * @description 保存
     * @author free.zhang
     * @date 2018/2/25/025 21:34
     * @param '[response, key, value, maxAge, path]
     * @return void
     */
    private static void set(HttpServletResponse response,
                            String key, String value, int maxAge, String path) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);	// Cookie过期时间,单位/秒
        cookie.setPath(path);		// Cookie适用的路径
        response.addCookie(cookie);
    }

    /**
     * @class_name CookieUtils.java
     * @method getValue
     * @description 查询value
     * @author free.zhang
     * @date 2018/2/25/025 21:34
     * @param '[request, key]
     * @return java.lang.String
     */
    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * @class_name CookieUtils.java
     * @method get
     * @description 查询Cookie
     * @author free.zhang
     * @date 2018/2/25/025 21:35
     * @param '[request, key]
     * @return javax.servlet.http.Cookie
     */
    private static Cookie get(HttpServletRequest request, String key) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null && arr_cookie.length > 0) {
            for (Cookie cookie : arr_cookie) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * @class_name CookieUtils.java
     * @method remove
     * @description  删除Cookie
     * @author free.zhang
     * @date 2018/2/25/025 21:35
     * @param '[request, response, key]
     * @return void
     */
    public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            set(response, key, "", 0, COOKIE_PATH);
        }
    }
}
