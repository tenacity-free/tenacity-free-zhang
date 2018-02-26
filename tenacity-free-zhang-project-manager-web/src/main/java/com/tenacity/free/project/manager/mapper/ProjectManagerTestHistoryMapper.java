package com.tenacity.free.project.manager.mapper;

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
public interface ProjectManagerTestHistoryMapper {


    int add(ProjectManagerTestHistory xxlApiTestHistory);

    int update(ProjectManagerTestHistory xxlApiTestHistory);

    int delete(Integer id);

    ProjectManagerTestHistory load(Integer id);

    List<ProjectManagerTestHistory> loadByDocumentId(Integer documentId);

}
