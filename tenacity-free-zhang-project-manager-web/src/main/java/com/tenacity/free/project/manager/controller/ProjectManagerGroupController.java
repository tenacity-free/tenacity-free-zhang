package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.dao.ProjectManagerGroupDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.po.ProjectManagerDocument;
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
import java.util.List;


@Controller
@RequestMapping("/group")
public class ProjectManagerGroupController {

	@Resource(name = "projectManagerProjectDao")
	private ProjectManagerProjectDao projectManagerProjectDao;
	@Resource(name = "projectManagerGroupDao")
	private ProjectManagerGroupDao projectManagerGroupDao;
	@Resource(name = "projectManagerDocumentDao")
	private ProjectManagerDocumentDao projectManagerDocumentDao;

	@RequestMapping
	public String index(Model model, int productId, @RequestParam(required = false, defaultValue = "-1")  int groupId) {

		// 项目
		ProjectManagerProject projectManagerProject = projectManagerProjectDao.load(productId);
		if (projectManagerProject == null) {
			throw new RuntimeException("系统异常，项目ID非法");
		}
		model.addAttribute("productId", productId);
		model.addAttribute("project", projectManagerProject);

		// 分组列表
		List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(productId);
		model.addAttribute("groupList", groupList);

		// 选中分组
		ProjectManagerGroup groupInfo = null;
		if (CollectionUtils.isNotEmpty(groupList)) {
			for (ProjectManagerGroup groupItem: groupList) {
				if (groupId == groupItem.getId()) {
					groupInfo = groupItem;
				}
			}
		}
		if (groupInfo == null && !(groupId==-1 || groupId==0)) {
			groupId = -1;	// 合法性校验：全部(-1) | 默认分组(0) | 指定分组(匹配到数据)
		}
		model.addAttribute("groupId", groupId);
		model.addAttribute("groupInfo", groupInfo);

		// 分组下的，接口列表
		List<ProjectManagerDocument> documentList = projectManagerDocumentDao.loadAll(productId, groupId);
		model.addAttribute("documentList", documentList);

		return "group/group.list";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(ProjectManagerGroup projectManagerGroup) {
		// valid
		if (StringUtils.isBlank(projectManagerGroup.getName())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“分组名称”");
		}

		int ret = projectManagerGroupDao.add(projectManagerGroup);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(ProjectManagerGroup projectManagerGroup) {
		// exist
		ProjectManagerGroup existGroup = projectManagerGroupDao.load(projectManagerGroup.getId());
		if (existGroup == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "更新失败，分组ID非法");
		}

		// valid
		if (StringUtils.isBlank(projectManagerGroup.getName())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“分组名称”");
		}

		int ret = projectManagerGroupDao.update(projectManagerGroup);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id) {

		// 分组下是否存在接口
		List<ProjectManagerDocument> documentList = projectManagerDocumentDao.loadByGroupId(id);
		if (CollectionUtils.isNotEmpty(documentList)) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，分组下存在接口，不允许强制删除");
		}

		int ret = projectManagerGroupDao.delete(id);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

}
