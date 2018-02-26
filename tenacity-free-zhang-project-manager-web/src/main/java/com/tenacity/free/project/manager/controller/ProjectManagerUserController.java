package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.dao.ProjectManagerUserDao;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import com.tenacity.free.project.manager.service.impl.ProjectManagerUserServiceImpl;
import com.tenacity.free.project.manager.util.JacksonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequestMapping("/user")
public class ProjectManagerUserController {

	@Resource(name = "projectManagerUserDao")
	private ProjectManagerUserDao projectManagerUserDao;
	@Resource(name = "projectManagerUserService")
	private ProjectManagerUserService projectManagerUserService;

	@RequestMapping
    @PermessionLimit(superUser = true)
	public String index(Model model, HttpServletRequest request) {

		// permission
		ProjectManagerUser loginUser = (request.getAttribute(ProjectManagerUserServiceImpl.LOGIN_IDENTITY_KEY)!=null)? (ProjectManagerUser) request.getAttribute(ProjectManagerUserServiceImpl.LOGIN_IDENTITY_KEY) :null;
		if (loginUser.getType()!=1) {
			throw new RuntimeException("权限拦截.");
		}

		List<ProjectManagerUser> userList = projectManagerUserDao.loadAll();
		if (CollectionUtils.isEmpty(userList)) {
			userList = new ArrayList<>();
		} else {
			for (ProjectManagerUser user: userList) {
				user.setPassword("***");
			}
		}
		model.addAttribute("userList", JacksonUtils.writeValueAsString(userList));

		return "user/user.list";
	}

	@RequestMapping("/add")
	@ResponseBody
    @PermessionLimit(superUser = true)
	public ReturnT<String> add(ProjectManagerUser xxlApiUser) {
		// valid
		if (StringUtils.isBlank(xxlApiUser.getUserName())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“登录账号”");
		}
		if (StringUtils.isBlank(xxlApiUser.getPassword())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“登录密码”");
		}

		// valid
		ProjectManagerUser existUser = projectManagerUserDao.findByUserName(xxlApiUser.getUserName());
		if (existUser != null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "“登录账号”重复，请更换");
		}

		int ret = projectManagerUserDao.add(xxlApiUser);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/update")
	@ResponseBody
    @PermessionLimit(superUser = true)
	public ReturnT<String> update(ProjectManagerUser xxlApiUser) {

		// exist
		ProjectManagerUser existUser = projectManagerUserDao.findByUserName(xxlApiUser.getUserName());
		if (existUser == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "更新失败，登录账号非法");
		}

		// update param
		if (StringUtils.isNotBlank(xxlApiUser.getPassword())) {
			existUser.setPassword(xxlApiUser.getPassword());
		}
		existUser.setType(xxlApiUser.getType());
		existUser.setRealName(xxlApiUser.getRealName());

		int ret = projectManagerUserDao.update(existUser);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/delete")
	@ResponseBody
	@PermessionLimit(superUser = true)
	public ReturnT<String> delete(HttpServletRequest request, int id) {

		// valid user
		ProjectManagerUser delUser = projectManagerUserDao.findById(id);
		if (delUser == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，用户ID非法");
		}

		ProjectManagerUser loginUser = projectManagerUserService.ifLogin(request);
		if (loginUser == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，系统登录异常");
		}

		if (delUser.getUserName().equals(loginUser.getUserName())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，不可以删除自己的用户信息");
		}

		// must leave one user
		List<ProjectManagerUser> allUser = projectManagerUserDao.loadAll();
		if (allUser==null || allUser.size()==1) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，系统至少保留一个登录用户");
		}

		int ret = projectManagerUserDao.delete(id);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

}
