package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerUserDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerUserMapper;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerUserDaoImpl.java
 * @description
 * @create 2018-02-26 11:48
 */
@Repository("projectManagerUserDao")
public class ProjectManagerUserDaoImpl implements ProjectManagerUserDao {

    @Autowired
    private ProjectManagerUserMapper projectManagerUserMapper;

    @Override
    public int add(ProjectManagerUser xxlApiUser) {
        return projectManagerUserMapper.add(xxlApiUser);
    }

    @Override
    public int update(ProjectManagerUser xxlApiUser) {
        return projectManagerUserMapper.update(xxlApiUser);
    }

    @Override
    public int delete(int id) {
        return projectManagerUserMapper.delete(id);
    }

    @Override
    public ProjectManagerUser findByUserName(String userName) {
        return projectManagerUserMapper.findByUserName(userName);
    }

    @Override
    public ProjectManagerUser findById(int id) {
        return projectManagerUserMapper.findById(id);
    }

    @Override
    public List<ProjectManagerUser> loadAll() {
        return projectManagerUserMapper.loadAll();
    }
}
