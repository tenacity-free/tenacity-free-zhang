package com.tenacity.free.project.manager.dao.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDataTypeFieldDao;
import com.tenacity.free.project.manager.mapper.ProjectManagerDataTypeFieldMapper;
import com.tenacity.free.project.manager.po.ProjectManagerDataTypeField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.dao.impl
 * @file_name ProjectManagerDataTypeFieldDaoImpl.java
 * @description
 * @create 2018-02-26 11:45
 */
@Repository("projectManagerDataTypeFieldDao")
public class ProjectManagerDataTypeFieldDaoImpl implements ProjectManagerDataTypeFieldDao {

    @Autowired
    private ProjectManagerDataTypeFieldMapper projectManagerDataTypeFieldMapper;


    @Override
    public int add(List<ProjectManagerDataTypeField> xxlApiDataTypeFieldList) {
        return projectManagerDataTypeFieldMapper.add(xxlApiDataTypeFieldList);
    }

    @Override
    public int deleteByParentDatatypeId(int parentDatatypeId) {
        return projectManagerDataTypeFieldMapper.deleteByParentDatatypeId(parentDatatypeId);
    }

    @Override
    public List<ProjectManagerDataTypeField> findByParentDatatypeId(int parentDatatypeId) {
        return projectManagerDataTypeFieldMapper.findByParentDatatypeId(parentDatatypeId);
    }

    @Override
    public List<ProjectManagerDataTypeField> findByFieldDatatypeId(int fieldDatatypeId) {
        return projectManagerDataTypeFieldMapper.findByFieldDatatypeId(fieldDatatypeId);
    }
}
