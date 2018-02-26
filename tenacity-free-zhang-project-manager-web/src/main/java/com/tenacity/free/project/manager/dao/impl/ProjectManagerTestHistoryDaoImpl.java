package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerTestHistoryDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerTestHistoryMapper;
import com.tenacity.free.project.manager.po.ProjectManagerTestHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerTestHistoryDaoImpl.java
 * @description
 * @create 2018-02-26 11:48
 */
@Repository("projectManagerTestHistoryDao")
public class ProjectManagerTestHistoryDaoImpl implements ProjectManagerTestHistoryDao {

    @Autowired
    private ProjectManagerTestHistoryMapper projectManagerTestHistoryMapper;

    @Override
    public int add(ProjectManagerTestHistory xxlApiTestHistory) {
        return projectManagerTestHistoryMapper.add(xxlApiTestHistory);
    }

    @Override
    public int update(ProjectManagerTestHistory xxlApiTestHistory) {
        return projectManagerTestHistoryMapper.update(xxlApiTestHistory);
    }

    @Override
    public int delete(int id) {
        return projectManagerTestHistoryMapper.delete(id);
    }

    @Override
    public ProjectManagerTestHistory load(int id) {
        return projectManagerTestHistoryMapper.load(id);
    }

    @Override
    public List<ProjectManagerTestHistory> loadByDocumentId(int documentId) {
        return projectManagerTestHistoryMapper.loadByDocumentId(documentId);
    }
}
