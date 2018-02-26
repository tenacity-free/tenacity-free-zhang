package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerTestHistory;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerTestHistoryMapper.java
 * @description
 * @create 2018-02-26 11:18
 */
public interface ProjectManagerTestHistoryDao {


    int add(ProjectManagerTestHistory xxlApiTestHistory);

    int update(ProjectManagerTestHistory xxlApiTestHistory);

    int delete(int id);

    ProjectManagerTestHistory load(int id);

    List<ProjectManagerTestHistory> loadByDocumentId(int documentId);

}
