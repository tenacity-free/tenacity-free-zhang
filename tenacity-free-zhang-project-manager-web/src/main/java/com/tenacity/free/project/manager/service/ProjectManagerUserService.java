package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerUserService.java
 * @description
 * @create 2018-02-26 12:37
 */
public interface ProjectManagerUserService {

    ReturnT<String> login(HttpServletRequest request, HttpServletResponse response, boolean ifRemember, String userName, String password);

    ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response);

    ProjectManagerUser ifLogin(HttpServletRequest request);

    String index(Model model, HttpServletRequest request);

    ReturnT<String> add(ProjectManagerUser projectManagerUser);

    ReturnT<String> update(ProjectManagerUser projectManagerUser);

    ReturnT<String> delete(HttpServletRequest request, int id);



}
