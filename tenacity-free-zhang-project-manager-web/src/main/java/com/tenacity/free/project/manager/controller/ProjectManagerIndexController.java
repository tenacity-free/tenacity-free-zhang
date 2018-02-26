package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class ProjectManagerIndexController {

	@Resource
	private ProjectManagerUserService projectManagerUserService;

	@RequestMapping("/")
	@PermessionLimit(limit=false)
	public String index(Model model, HttpServletRequest request) {
		ProjectManagerUser loginUser = projectManagerUserService.ifLogin(request);
		if (loginUser == null) {
			return "redirect:/toLogin";
		}
		return "redirect:/project";
	}
	
	@RequestMapping("/toLogin")
	@PermessionLimit(limit=false)
	public String toLogin(Model model, HttpServletRequest request) {
		ProjectManagerUser loginUser = projectManagerUserService.ifLogin(request);
		if (loginUser != null) {
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	@PermessionLimit(limit=false)
	public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String ifRemember, String userName, String password){
		// param
		boolean ifRem = false;
		if (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember)) {
			ifRem = true;
		}

		// do login
		ReturnT<String> loginRet = projectManagerUserService.login(request, response, ifRem, userName, password);
		return loginRet;
	}
	
	@RequestMapping(value="logout", method=RequestMethod.POST)
	@ResponseBody
	@PermessionLimit(limit=false)
	public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response){
		ReturnT<String> logoutRet = projectManagerUserService.logout(request, response);
		return logoutRet;
	}
	
	@RequestMapping("/help")
	public String help() {
		return "help";
	}
	
}
