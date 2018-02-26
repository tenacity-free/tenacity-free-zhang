package com.tenacity.free.project.manager.mapper;

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
public interface ProjectManagerDocumentMapper {

    int add(ProjectManagerDocument xxlApiDocument);

    int update(ProjectManagerDocument xxlApiDocument);

    int delete(Integer id);

    ProjectManagerDocument load(Integer id);

    List<ProjectManagerDocument> loadAll(Integer productId, Integer groupId);

    List<ProjectManagerDocument> loadByGroupId(Integer groupId);

    List<ProjectManagerDocument> findByResponseDataTypeId(Integer responseDatatypeId);
}
