package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerDataType;
import com.tenacity.free.project.manager.po.ProjectManagerDataTypeField;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerDataTypeFieldMapper.java
 * @description
 * @create 2018-02-26 11:16
 */
public interface ProjectManagerDataTypeFieldDao {

    int add(List<ProjectManagerDataTypeField> xxlApiDataTypeFieldList);

    int deleteByParentDatatypeId(int parentDatatypeId);

    List<ProjectManagerDataTypeField> findByParentDatatypeId(int parentDatatypeId);

    List<ProjectManagerDataTypeField> findByFieldDatatypeId(int fieldDatatypeId);
}
