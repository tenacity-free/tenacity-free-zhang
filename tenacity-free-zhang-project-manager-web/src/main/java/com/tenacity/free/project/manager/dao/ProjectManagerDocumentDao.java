package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerDocument;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerDocumentMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerDocumentDao {

    int add(ProjectManagerDocument xxlApiDocument);

    int update(ProjectManagerDocument xxlApiDocument);

    int delete(int id);

    ProjectManagerDocument load(int id);

    List<ProjectManagerDocument> loadAll(int productId, int groupId);

    List<ProjectManagerDocument> loadByGroupId(int id);

    List<ProjectManagerDocument> findByResponseDataTypeId(int responseDatatypeId);
}
