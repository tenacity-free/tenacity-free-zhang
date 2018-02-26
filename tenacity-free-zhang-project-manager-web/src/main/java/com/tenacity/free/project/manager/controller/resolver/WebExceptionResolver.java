package com.tenacity.free.project.manager.controller.resolver;

import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.controller.resolver
 * @file_name WebExceptionResolver.java
 * @description
 * @create 2018-02-26 13:32
 */
public class WebExceptionResolver implements HandlerExceptionResolver {

    private static transient Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        logger.error("WebExceptionResolver:{}", e);

        ModelAndView mv = new ModelAndView();
        HandlerMethod method = (HandlerMethod) handler;
        ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
        if (responseBody != null) {
            response.setContentType("application/json;charset=UTF-8");
            mv.addObject("result", JacksonUtils.writeValueAsString(new ReturnT<String>(500, e.toString().replaceAll("\n", "<br/>"))));
            mv.setViewName("/common/common.result");
        } else {
            mv.addObject("exceptionMsg", e.toString().replaceAll("\n", "<br/>"));
            mv.setViewName("/common/common.exception");
        }
        return mv;
    }
}
