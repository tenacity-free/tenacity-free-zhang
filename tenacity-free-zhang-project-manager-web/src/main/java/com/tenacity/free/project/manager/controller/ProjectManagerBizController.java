package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerBizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/biz")
public class ProjectManagerBizController {

    @Resource(name = "projectManagerBizService")
    private ProjectManagerBizService projectManagerBizService;

    @RequestMapping
    public String index(Model model) {

        return projectManagerBizService.index(model);
    }

    @RequestMapping("/add")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> add(ProjectManagerApiBiz projectManagerApiBiz) {
        return projectManagerBizService.add(projectManagerApiBiz);
    }

    @RequestMapping("/update")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> update(ProjectManagerApiBiz projectManagerApiBiz) {
        return projectManagerBizService.update(projectManagerApiBiz);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> delete(int id) {
        return projectManagerBizService.delete(id);
    }

}
