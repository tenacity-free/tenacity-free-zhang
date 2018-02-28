package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerProject;
import com.tenacity.free.project.manager.po.ReturnT;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerProjectService.java
 * @description
 * @create 2018-02-28 11:00
 */
public interface ProjectManagerProjectService {


    String index(Model model,int bizId);

    Map<String, Object> pageList(int start,int length,String name, int bizId);

    ReturnT<String> add(ProjectManagerProject projectManagerProject);

    ReturnT<String> update(ProjectManagerProject projectManagerProject);

    ReturnT<String> delete(int id);
}
