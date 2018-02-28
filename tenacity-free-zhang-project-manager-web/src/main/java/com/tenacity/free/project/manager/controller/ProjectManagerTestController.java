package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.po.ProjectManagerTestHistory;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerTestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/test")
public class ProjectManagerTestController {


	@Resource(name = "projectManagerTestService")
	private ProjectManagerTestService projectManagerTestService;

	/**
	 * 接口测试，待完善
	 * @return
	 */
	@RequestMapping
	public String index(Model model,
			@RequestParam(required = false, defaultValue = "0") int documentId,
			@RequestParam(required = false, defaultValue = "0") int testId) {
			return projectManagerTestService.index(model,documentId,testId);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<Integer> add(ProjectManagerTestHistory projectManagerTestHistory) {
		return projectManagerTestService.add(projectManagerTestHistory);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(ProjectManagerTestHistory projectManagerTestHistory) {
		return projectManagerTestService.update(projectManagerTestHistory);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id) {
		return projectManagerTestService.delete(id);
	}

	/**
	 * 测试Run
	 * @return
	 */
	@RequestMapping("/run")
	@ResponseBody
	public ReturnT<String> run(ProjectManagerTestHistory projectManagerTestHistory, HttpServletRequest request, HttpServletResponse response) {
		return projectManagerTestService.run(projectManagerTestHistory,request,response);

	}
}
