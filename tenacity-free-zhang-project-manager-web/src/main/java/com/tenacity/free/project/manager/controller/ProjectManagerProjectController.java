package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.dao.ProjectManagerBizDao;
import com.tenacity.free.project.manager.dao.ProjectManagerGroupDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import com.tenacity.free.project.manager.po.ProjectManagerGroup;
import com.tenacity.free.project.manager.po.ProjectManagerProject;
import com.tenacity.free.project.manager.po.ReturnT;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/project")
public class ProjectManagerProjectController {

	@Resource(name = "projectManagerProjectDao")
	private ProjectManagerProjectDao projectManagerProjectDao;
	@Resource(name = "projectManagerGroupDao")
	private ProjectManagerGroupDao projectManagerGroupDao;
	@Resource(name = "projectManagerBizDao")
	private ProjectManagerBizDao projectManagerBizDao;

	@RequestMapping
	public String index(Model model, @RequestParam(required = false, defaultValue = "0") int bizId) {

		// 业务线ID
		model.addAttribute("bizId", bizId);

		// 业务线列表
		List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
		model.addAttribute("bizList", bizList);

		return "project/project.list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
										@RequestParam(required = false, defaultValue = "10") int length,
										String name, int bizId) {
		// page list
		List<ProjectManagerProject> list = projectManagerProjectDao.pageList(start, length, name, bizId);
		int list_count = projectManagerProjectDao.pageListCount(start, length, name, bizId);

		// package result
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("recordsTotal", list_count);		// 总记录数
		maps.put("recordsFiltered", list_count);	// 过滤后的总记录数
		maps.put("data", list);  					// 分页列表
		return maps;
	}

	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(ProjectManagerProject xxlApiProject) {
		// valid
		if (StringUtils.isBlank(xxlApiProject.getName())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“项目名称”");
		}
		if (StringUtils.isBlank(xxlApiProject.getBaseUrlProduct())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“跟地址：线上环境”");
		}

		int ret = projectManagerProjectDao.add(xxlApiProject);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(ProjectManagerProject xxlApiProject) {
		// exist
		ProjectManagerProject existProkect = projectManagerProjectDao.load(xxlApiProject.getId());
		if (existProkect == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "更新失败，项目ID非法");
		}

		// valid
		if (StringUtils.isBlank(xxlApiProject.getName())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“项目名称”");
		}
		if (StringUtils.isBlank(xxlApiProject.getBaseUrlProduct())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“跟地址：线上环境”");
		}

		int ret = projectManagerProjectDao.update(xxlApiProject);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id) {

		// 项目下是否存在分组
		List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(id);
		if (CollectionUtils.isNotEmpty(groupList)) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "该项目下存在分组信息，拒绝删除");
		}

		int ret = projectManagerProjectDao.delete(id);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

}
