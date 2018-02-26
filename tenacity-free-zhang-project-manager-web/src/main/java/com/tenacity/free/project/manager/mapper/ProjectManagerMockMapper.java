package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.ProjectManagerMock;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerMockMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerMockMapper {

    int add(ProjectManagerMock xxlApiMock);

    int update(ProjectManagerMock xxlApiMock);

    int delete(Integer id);

    List<ProjectManagerMock> loadAll(Integer documentId);

    ProjectManagerMock load(Integer id);

    ProjectManagerMock loadByUuid(String uuid);
}
