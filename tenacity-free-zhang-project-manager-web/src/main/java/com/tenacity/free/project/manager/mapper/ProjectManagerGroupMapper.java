package com.tenacity.free.project.manager.mapper;

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
public interface ProjectManagerGroupMapper {

    int add(ProjectManagerGroup xxlApiGroup);

    int update(ProjectManagerGroup xxlApiGroup);

    int delete(Integer id);

    ProjectManagerGroup load(Integer id);

    List<ProjectManagerGroup> loadAll(Integer productId);
}
