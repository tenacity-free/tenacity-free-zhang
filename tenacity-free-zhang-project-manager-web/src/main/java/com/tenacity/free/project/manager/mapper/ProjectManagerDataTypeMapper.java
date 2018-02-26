package com.tenacity.free.project.manager.mapper;

import com.tenacity.free.project.manager.po.ProjectManagerDataType;

import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.mapper
 * @file_name ProjectManagerDataTypeMapper.java
 * @description
 * @create 2018-02-26 11:17
 */
public interface ProjectManagerDataTypeMapper {

    int add(ProjectManagerDataType xxlApiDataTypeFieldList);

    int update(ProjectManagerDataType xxlApiDataTypeFieldList);

    int delete(Integer id);

    ProjectManagerDataType load(Integer id);

    List<ProjectManagerDataType> pageList(Map<String,Object> pMap);

    int pageListCount(Map<String,Object> pMap);

   ProjectManagerDataType loadByName(String name);

}
