package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerMockDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerMockMapper;
import com.tenacity.free.project.manager.po.ProjectManagerMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerMockDaoImpl.java
 * @description
 * @create 2018-02-26 11:47
 */
@Repository("projectManagerMockDao")
public class ProjectManagerMockDaoImpl implements ProjectManagerMockDao {

    @Autowired
    private ProjectManagerMockMapper projectManagerMockMapper;

    @Override
    public int add(ProjectManagerMock xxlApiMock) {
        return projectManagerMockMapper.add(xxlApiMock);
    }

    @Override
    public int update(ProjectManagerMock xxlApiMock) {
        return projectManagerMockMapper.update(xxlApiMock);
    }

    @Override
    public int delete(int id) {
        return projectManagerMockMapper.delete(id);
    }

    @Override
    public List<ProjectManagerMock> loadAll(int documentId) {
        return projectManagerMockMapper.loadAll(documentId);
    }

    @Override
    public ProjectManagerMock load(int id) {
        return projectManagerMockMapper.load(id);
    }

    @Override
    public ProjectManagerMock loadByUuid(String uuid) {
        return projectManagerMockMapper.loadByUuid(uuid);
    }
}
