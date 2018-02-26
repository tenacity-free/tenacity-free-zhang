package com.tenacity.free.project.manager.controller.interceptor;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.controller.interceptor
 * @file_name CookieInterceptor.java
 * @description Cookie拦截器
 * @create 2018-02-26 13:30
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && ArrayUtils.isNotEmpty(request.getCookies())) {
            Map<String, Cookie> cookieMap = new HashMap<>();
            for (Cookie ck : request.getCookies()) {
                cookieMap.put(ck.getName(), ck);
            }
            modelAndView.addObject("cookieMap", cookieMap);
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
