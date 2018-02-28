package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ReturnT;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerIndexService.java
 * @description
 * @create 2018-02-28 10:51
 */
public interface ProjectManagerIndexService {

    String index(Model model, HttpServletRequest request);

    String toLogin(Model model, HttpServletRequest request);

    ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String ifRemember, String userName, String password);

    ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response);

    String help();
}
