package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerDocumentMapper;
import com.tenacity.free.project.manager.po.ProjectManagerDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerDocumentDaoImpl.java
 * @description
 * @create 2018-02-26 11:45
 */
@Repository("projectManagerDocumentDao")
public class ProjectManagerDocumentDaoImpl implements ProjectManagerDocumentDao {

    @Autowired
    private ProjectManagerDocumentMapper projectManagerDocumentMapper;

    @Override
    public int add(ProjectManagerDocument xxlApiDocument) {
        return projectManagerDocumentMapper.add(xxlApiDocument);
    }

    @Override
    public int update(ProjectManagerDocument xxlApiDocument) {
        return projectManagerDocumentMapper.update(xxlApiDocument);
    }

    @Override
    public int delete(int id) {
        return projectManagerDocumentMapper.delete(id);
    }

    @Override
    public ProjectManagerDocument load(int id) {
        return projectManagerDocumentMapper.load(id);
    }

    @Override
    public List<ProjectManagerDocument> loadAll(int productId, int groupId) {
        Map<String,Object> paramMap = new HashMap<>(2);
        paramMap.put("productId",productId);
        paramMap.put("groupId",groupId);
        return projectManagerDocumentMapper.loadAll(paramMap);
    }

    @Override
    public List<ProjectManagerDocument> loadByGroupId(int id) {
        return projectManagerDocumentMapper.loadByGroupId(id);
    }

    @Override
    public List<ProjectManagerDocument> findByResponseDataTypeId(int responseDatatypeId) {
        return projectManagerDocumentMapper.findByResponseDataTypeId(responseDatatypeId);
    }
}
