package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.po.*;
import com.tenacity.free.project.manager.service.ProjectManagerDocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/document")
public class ProjectManagerDocumentController {

    @Resource(name = "projectManagerDocumentService")
    private ProjectManagerDocumentService projectManagerDocumentService;

    @RequestMapping("/markStar")
    @ResponseBody
    public ReturnT<String> markStar(int id, int starLevel) {
        return projectManagerDocumentService.markStar(id, starLevel);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id) {
        return projectManagerDocumentService.delete(id);
    }

    /**
     * 新增，API
     *
     * @param productId
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(Model model, int productId) {

        return projectManagerDocumentService.addPage(model, productId);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnT<Integer> add(ProjectManagerDocument projectManagerDocument) {
        return projectManagerDocumentService.add(projectManagerDocument);
    }

    /**
     * 更新，API
     *
     * @return
     */
    @RequestMapping("/updatePage")
    public String updatePage(Model model, int id) {
        return projectManagerDocumentService.updatePage(model, id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(ProjectManagerDocument projectManagerDocument) {
        return projectManagerDocumentService.update(projectManagerDocument);
    }

    /**
     * 详情页，API
     *
     * @return
     */
    @RequestMapping("/detailPage")
    public String detailPage(Model model, int id) {
        return projectManagerDocumentService.detailPage(model, id);
    }

}
