package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerBizMapper.java
 * @description
 * @create 2018-02-26 11:16
 */
public interface ProjectManagerBizDao {

    int add(ProjectManagerApiBiz xxlApiBiz);

    int udpate(ProjectManagerApiBiz xxlApiBiz);

    int delete(int id);

    List<ProjectManagerApiBiz> loadAll();

}
