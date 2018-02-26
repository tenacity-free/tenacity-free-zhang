package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerBizDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerBizMapper;
import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerBizDaoImpl.java
 * @description
 * @create 2018-02-26 11:44
 */
@Repository("projectManagerBizDao")
public class ProjectManagerBizDaoImpl implements ProjectManagerBizDao {

    @Autowired
    private ProjectManagerBizMapper projectManagerBizMapper;

    @Override
    public int add(ProjectManagerApiBiz xxlApiBiz) {
        return 0;
    }

    @Override
    public int udpate(ProjectManagerApiBiz xxlApiBiz) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<ProjectManagerApiBiz> loadAll() {
        return null;
    }
}
