package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerIndexService;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerIndexServiceImpl.java
 * @description
 * @create 2018-02-28 10:52
 */
@Service("projectManagerIndexService")
public class ProjectManagerIndexServiceImpl implements ProjectManagerIndexService {

    @Resource
    private ProjectManagerUserService projectManagerUserService;

    @Override
    public String index(Model model, HttpServletRequest request) {
        ProjectManagerUser loginUser = projectManagerUserService.ifLogin(request);
        if (loginUser == null) {
            return "redirect:/toLogin";
        }
        return "redirect:/project";
    }

    @Override
    public String toLogin(Model model, HttpServletRequest request) {
        ProjectManagerUser loginUser = projectManagerUserService.ifLogin(request);
        if (loginUser != null) {
            return "redirect:/";
        }
        return "login";
    }

    @Override
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String ifRemember, String userName, String password) {
        // param
        boolean ifRem = false;
        if (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember)) {
            ifRem = true;
        }

        // do login
        ReturnT<String> loginRet = projectManagerUserService.login(request, response, ifRem, userName, password);
        return loginRet;
    }

    @Override
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        ReturnT<String> logoutRet = projectManagerUserService.logout(request, response);
        return logoutRet;
    }

    @Override
    public String help() {
        return "help";
    }
}
