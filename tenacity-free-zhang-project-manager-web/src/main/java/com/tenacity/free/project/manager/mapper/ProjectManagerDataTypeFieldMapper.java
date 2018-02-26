package com.tenacity.free.project.manager.mapper;

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
public interface ProjectManagerDataTypeFieldMapper {

    int add(List<ProjectManagerDataTypeField> xxlApiDataType);

    int deleteByParentDatatypeId(Integer parentDatatypeId);

    List<ProjectManagerDataTypeField> findByParentDatatypeId(Integer parentDatatypeId);

    List<ProjectManagerDataTypeField> findByFieldDatatypeId(Integer fieldDatatypeId);

}
