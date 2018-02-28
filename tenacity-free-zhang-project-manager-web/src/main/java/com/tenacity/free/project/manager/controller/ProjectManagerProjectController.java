package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.po.ProjectManagerProject;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping("/project")
public class ProjectManagerProjectController {


	@Resource(name = "projectManagerProjectService")
	private ProjectManagerProjectService projectManagerProjectService;

	@RequestMapping
	public String index(Model model, @RequestParam(required = false, defaultValue = "0") int bizId) {
		return projectManagerProjectService.index(model,bizId);
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
										@RequestParam(required = false, defaultValue = "10") int length,
										String name, int bizId) {
		return projectManagerProjectService.pageList(start,length,name,bizId);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(ProjectManagerProject projectManagerProject) {
		return projectManagerProjectService.add(projectManagerProject);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(ProjectManagerProject projectManagerProject) {
		return projectManagerProjectService.update(projectManagerProject);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id) {
		return projectManagerProjectService.delete(id);
	}

}
