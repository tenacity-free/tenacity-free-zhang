package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerGroupDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerGroupMapper;
import com.tenacity.free.project.manager.po.ProjectManagerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerGroupDaoImpl.java
 * @description
 * @create 2018-02-26 11:46
 */
@Repository("projectManagerGroupDao")
public class ProjectManagerGroupDaoImpl implements ProjectManagerGroupDao {

    @Autowired
    private ProjectManagerGroupMapper projectManagerGroupMapper;

    @Override
    public int add(ProjectManagerGroup xxlApiGroup) {
        return projectManagerGroupMapper.add(xxlApiGroup);
    }

    @Override
    public int update(ProjectManagerGroup xxlApiGroup) {
        return projectManagerGroupMapper.update(xxlApiGroup);
    }

    @Override
    public int delete(int id) {
        return projectManagerGroupMapper.delete(id);
    }

    @Override
    public ProjectManagerGroup load(int id) {
        return projectManagerGroupMapper.load(id);
    }

    @Override
    public List<ProjectManagerGroup> loadAll(int productId) {
        return projectManagerGroupMapper.loadAll(productId);
    }
}
