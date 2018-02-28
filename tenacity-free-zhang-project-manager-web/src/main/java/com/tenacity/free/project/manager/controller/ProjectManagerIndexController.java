package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerIndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class ProjectManagerIndexController {


    @Resource(name = "projectManagerIndexService")
    private ProjectManagerIndexService projectManagerIndexService;

    @RequestMapping("/")
    @PermessionLimit(limit = false)
    public String index(Model model, HttpServletRequest request) {
        return projectManagerIndexService.index(model, request);
    }

    @RequestMapping("/toLogin")
    @PermessionLimit(limit = false)
    public String toLogin(Model model, HttpServletRequest request) {
        return projectManagerIndexService.toLogin(model, request);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    @PermessionLimit(limit = false)
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String ifRemember, String userName, String password) {
        return projectManagerIndexService.loginDo(request, response, ifRemember, userName, password);
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    @PermessionLimit(limit = false)
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return projectManagerIndexService.logout(request, response);
    }

    @RequestMapping("/help")
    public String help() {
        return projectManagerIndexService.help();
    }

}
