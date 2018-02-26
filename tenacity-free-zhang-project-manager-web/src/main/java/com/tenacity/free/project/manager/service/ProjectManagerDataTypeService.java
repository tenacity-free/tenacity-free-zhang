package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerDataType;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerDataTypeService.java
 * @description
 * @create 2018-02-26 12:38
 */
public interface ProjectManagerDataTypeService {

    ProjectManagerDataType loadDataType(int dataTypeId);
}
