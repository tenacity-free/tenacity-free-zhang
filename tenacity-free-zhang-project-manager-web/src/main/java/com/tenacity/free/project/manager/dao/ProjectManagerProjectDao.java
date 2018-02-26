package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerProject;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerProjectMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerProjectDao {

    int add(ProjectManagerProject xxlApiProject);

    int update(ProjectManagerProject xxlApiProject);

    int delete(int id);

    ProjectManagerProject load(int id);

    List<ProjectManagerProject> pageList(int offset, int pagesize, String name, int bizId);

    int pageListCount(int offset, int pagesize, String name, int bizId);

}
