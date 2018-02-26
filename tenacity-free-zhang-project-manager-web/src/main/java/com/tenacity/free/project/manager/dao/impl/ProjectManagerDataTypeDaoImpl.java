package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDataTypeDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerDataTypeMapper;
import com.tenacity.free.project.manager.po.ProjectManagerDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerDataTypeDaoImpl.java
 * @description
 * @create 2018-02-26 11:44
 */
@Repository("projectManagerDataTypeDao")
public class ProjectManagerDataTypeDaoImpl implements ProjectManagerDataTypeDao {

    @Autowired
    private ProjectManagerDataTypeMapper projectManagerDataTypeMapper;


    @Override
    public int add(ProjectManagerDataType xxlApiDataType) {
        return projectManagerDataTypeMapper.add(xxlApiDataType);
    }

    @Override
    public int update(ProjectManagerDataType xxlApiDataType) {
        return projectManagerDataTypeMapper.update(xxlApiDataType);
    }

    @Override
    public int delete(int id) {
        return projectManagerDataTypeMapper.delete(id);
    }

    @Override
    public ProjectManagerDataType load(int id) {
        return projectManagerDataTypeMapper.load(id);
    }

    @Override
    public List<ProjectManagerDataType> pageList(int offset, int pagesize, int bizId, String name) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("offset", offset);
        params.put("pagesize", pagesize);
        params.put("bizId", bizId);
        params.put("name", name);
        return projectManagerDataTypeMapper.pageList(params);
    }

    @Override
    public int pageListCount(int offset, int pagesize, int bizId, String name) {
        HashMap<String, Object> params = new HashMap<>(4);
        params.put("offset", offset);
        params.put("pagesize", pagesize);
        params.put("bizId", bizId);
        params.put("name", name);

        return projectManagerDataTypeMapper.pageListCount(params);
    }

    @Override
    public ProjectManagerDataType loadByName(String name) {
        return projectManagerDataTypeMapper.loadByName(name);
    }
}
