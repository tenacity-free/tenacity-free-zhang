package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.po.ProjectManagerMock;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerMockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/mock")
public class ProjectManagerMockController {


    @Resource(name = "projectManagerMockService")
    private ProjectManagerMockService projectManagerMockService;

    /**
     * 保存Mock数据
     *
     * @param projectManagerMock
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ReturnT<String> add(ProjectManagerMock projectManagerMock) {

        return projectManagerMockService.add(projectManagerMock);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id) {
        return projectManagerMockService.delete(id);
    }


    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(ProjectManagerMock projectManagerMock) {
        return projectManagerMockService.update(projectManagerMock);
    }

    @RequestMapping("/run/{uuid}")
    @PermessionLimit(limit = false)
    public void run(@PathVariable("uuid") String uuid, HttpServletRequest request, HttpServletResponse response) {

        projectManagerMockService.run(uuid, request, response);
    }

}
