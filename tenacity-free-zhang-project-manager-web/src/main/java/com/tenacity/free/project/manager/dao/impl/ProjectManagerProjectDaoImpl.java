package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerProjectMapper;
import com.tenacity.free.project.manager.po.ProjectManagerProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerProjectDaoImpl.java
 * @description
 * @create 2018-02-26 11:47
 */
@Repository("projectManagerProjectDao")
public class ProjectManagerProjectDaoImpl implements ProjectManagerProjectDao {

    @Autowired
    private ProjectManagerProjectMapper projectManagerProjectMapper;

    @Override
    public int add(ProjectManagerProject xxlApiProject) {
        return projectManagerProjectMapper.add(xxlApiProject);
    }

    @Override
    public int update(ProjectManagerProject xxlApiProject) {
        return projectManagerProjectMapper.update(xxlApiProject);
    }

    @Override
    public int delete(int id) {
        return projectManagerProjectMapper.delete(id);
    }

    @Override
    public ProjectManagerProject load(int id) {
        return projectManagerProjectMapper.load(id);
    }

    @Override
    public List<ProjectManagerProject> pageList(int offset, int pagesize, String name, int bizId) {
        Map<String,Object> paramMap = new HashMap<>(4);
        paramMap.put("offset",offset);
        paramMap.put("pagesize",pagesize);
        paramMap.put("name",name);
        paramMap.put("bizId",bizId);
        return projectManagerProjectMapper.pageList(paramMap);
    }

    @Override
    public int pageListCount(int offset, int pagesize, String name, int bizId) {
        Map<String,Object> paramMap = new HashMap<>(4);
        paramMap.put("offset",offset);
        paramMap.put("pagesize",pagesize);
        paramMap.put("name",name);
        paramMap.put("bizId",bizId);
        return projectManagerProjectMapper.pageListCount(paramMap);
    }
}
