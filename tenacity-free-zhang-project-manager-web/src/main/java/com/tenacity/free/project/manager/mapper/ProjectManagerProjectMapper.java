package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.ProjectManagerProject;

import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerProjectMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerProjectMapper {

    int add(ProjectManagerProject xxlApiProject);

    int update(ProjectManagerProject xxlApiProject);

    int delete(Integer id);

    ProjectManagerProject load(Integer id);

    List<ProjectManagerProject> pageList(Map<String,Object> pMap);

    int pageListCount(Map<String,Object> pMap);

}
