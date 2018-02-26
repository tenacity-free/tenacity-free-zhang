package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerGroup;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerGroupMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerGroupDao {

    int add(ProjectManagerGroup xxlApiGroup);

    int update(ProjectManagerGroup xxlApiGroup);

    int delete(int id);

    ProjectManagerGroup load(int id);

    List<ProjectManagerGroup> loadAll(int productId);
}
