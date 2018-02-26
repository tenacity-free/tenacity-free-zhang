package com.tenacity.free.project.manager.dao;

import com.tenacity.free.project.manager.po.ProjectManagerDataType;
import com.tenacity.free.project.manager.po.ProjectManagerDataTypeField;

import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerDataTypeMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerDataTypeDao {

    int add(ProjectManagerDataType xxlApiDataType);

    int update(ProjectManagerDataType xxlApiDataType);

    int delete(int id);

    ProjectManagerDataType load(int id);

    List<ProjectManagerDataType> pageList(int offset, int pagesize, int bizId, String name);

    int pageListCount(int offset, int pagesize, int bizId, String name);

    ProjectManagerDataType loadByName(String name);
}
