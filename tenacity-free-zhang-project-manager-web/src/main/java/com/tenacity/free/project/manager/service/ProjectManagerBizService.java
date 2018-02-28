package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import com.tenacity.free.project.manager.po.ReturnT;
import org.springframework.ui.Model;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerBizService.java
 * @description 业务service
 * @create 2018-02-28 10:29
 */
public interface ProjectManagerBizService {

    String index(Model model);

    ReturnT<String> add(ProjectManagerApiBiz projectManagerApiBiz);

    ReturnT<String> update(ProjectManagerApiBiz projectManagerApiBiz);

    ReturnT<String> delete(int id);

}
