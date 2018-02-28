package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerMock;
import com.tenacity.free.project.manager.po.ReturnT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerMockService.java
 * @description
 * @create 2018-02-28 10:55
 */
public interface ProjectManagerMockService {

    ReturnT<String> add(ProjectManagerMock projectManagerMock);

    ReturnT<String> delete(int id);

    ReturnT<String> update(ProjectManagerMock projectManagerMock);

    void run(String uuid, HttpServletRequest request, HttpServletResponse response);


}
