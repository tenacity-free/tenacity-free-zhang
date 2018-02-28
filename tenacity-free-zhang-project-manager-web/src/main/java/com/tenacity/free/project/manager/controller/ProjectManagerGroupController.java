package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.po.ProjectManagerGroup;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/group")
public class ProjectManagerGroupController {


	@Resource(name = "projectManagerGroupService")
	private ProjectManagerGroupService projectManagerGroupService;

	@RequestMapping
	public String index(Model model, int productId, @RequestParam(required = false, defaultValue = "-1")  int groupId) {

		return projectManagerGroupService.index(model,productId,groupId);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(ProjectManagerGroup projectManagerGroup) {
		return projectManagerGroupService.add(projectManagerGroup);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(ProjectManagerGroup projectManagerGroup) {
		return projectManagerGroupService.update(projectManagerGroup);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id) {
		return projectManagerGroupService.delete(id);
	}

}
