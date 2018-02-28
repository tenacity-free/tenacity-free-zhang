package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class ProjectManagerUserController {

    @Resource(name = "projectManagerUserService")
    private ProjectManagerUserService projectManagerUserService;

    @RequestMapping
    @PermessionLimit(superUser = true)
    public String index(Model model, HttpServletRequest request) {
        return projectManagerUserService.index(model, request);
    }

    @RequestMapping("/add")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> add(ProjectManagerUser projectManagerUser) {
        return projectManagerUserService.add(projectManagerUser);
    }

    @RequestMapping("/update")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> update(ProjectManagerUser projectManagerUser) {
        return projectManagerUserService.update(projectManagerUser);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> delete(HttpServletRequest request, int id) {
        return projectManagerUserService.delete(request, id);
    }

}
