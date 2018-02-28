package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerGroup;
import com.tenacity.free.project.manager.po.ReturnT;
import org.springframework.ui.Model;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerGroupService.java
 * @description
 * @create 2018-02-28 10:47
 */
public interface ProjectManagerGroupService {

    String index(Model model, int productId, int groupId);

    ReturnT<String> add(ProjectManagerGroup projectManagerGroup);

    ReturnT<String> update(ProjectManagerGroup projectManagerGroup);

    ReturnT<String> delete(int id);
}
