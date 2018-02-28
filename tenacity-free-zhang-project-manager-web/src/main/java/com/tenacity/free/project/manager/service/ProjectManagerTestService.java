package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerTestHistory;
import com.tenacity.free.project.manager.po.ReturnT;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerTestService.java
 * @description
 * @create 2018-02-28 11:05
 */
public interface ProjectManagerTestService {


    String index(Model model,int documentId,int testId);

    ReturnT<Integer> add(ProjectManagerTestHistory projectManagerTestHistory);

    ReturnT<String> update(ProjectManagerTestHistory projectManagerTestHistory);

    ReturnT<String> delete(int id);

    ReturnT<String> run(ProjectManagerTestHistory projectManagerTestHistory, HttpServletRequest request, HttpServletResponse response);

    String markGetUrl(String url, Map<String, String> queryParamMap);

    String remoteCall(HttpRequestBase remoteRequest);


}
