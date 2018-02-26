package com.tenacity.free.project.manager.controller.interceptor;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import com.tenacity.free.project.manager.service.impl.ProjectManagerUserServiceImpl;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.controller.interceptor
 * @file_name PermissionInterceptor.java
 * @description 权限拦截
 * @create 2018-02-26 13:27
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {


    @Resource(name = "projectManagerUserService")
    private ProjectManagerUserService projectManagerUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        // if limit
        HandlerMethod method = (HandlerMethod) handler;
        PermessionLimit permission = method.getMethodAnnotation(PermessionLimit.class);
        boolean limit = (permission != null) ? permission.limit() : true;
        boolean superUser = (permission != null) ? permission.superUser() : false;

        // login user
        ProjectManagerUser loginUser = projectManagerUserService.ifLogin(request);
        request.setAttribute(ProjectManagerUserServiceImpl.LOGIN_IDENTITY_KEY, loginUser);

        // if pass
        boolean ifPass = false;
        if (limit) {
            if (loginUser == null) {
                ifPass = false;
            } else {
                if (superUser) {
                    // 0-普通用户、1-超级管理员
                    if (loginUser.getType() == 1) {
                        ifPass = true;
                    } else {
                        ifPass = false;
                    }
                } else {
                    ifPass = true;
                }
            }
        } else {
            ifPass = true;
        }

        if (!ifPass) {
            //request.getRequestDispatcher("/toLogin").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/toLogin");
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
