package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerUser;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerUserMapper.java
 * @description
 * @create 2018-02-26 11:18
 */
public interface ProjectManagerUserDao {

    int add(ProjectManagerUser xxlApiUser);

    int update(ProjectManagerUser xxlApiUser);

    int delete(int id);

    ProjectManagerUser findByUserName(String userName);

    ProjectManagerUser findById(int id);

    List<ProjectManagerUser> loadAll();
}
