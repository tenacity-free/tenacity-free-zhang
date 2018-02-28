package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDataTypeDao;
import com.tenacity.free.project.manager.dao.ProjectManagerDataTypeFieldDao;
import com.tenacity.free.project.manager.po.ProjectManagerDataType;
import com.tenacity.free.project.manager.po.ProjectManagerDataTypeField;
import com.tenacity.free.project.manager.service.ProjectManagerDataTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerDataTypeServiceImpl.java
 * @description
 * @create 2018-02-26 12:38
 */
@Service("projectManagerDataTypeService")
public class ProjectManagerDataTypeServiceImpl implements ProjectManagerDataTypeService {

    @Resource(name = "projectManagerDataTypeDao")
    private ProjectManagerDataTypeDao projectManagerDataTypeDao;

    @Resource(name = "projectManagerDataTypeFieldDao")
    private ProjectManagerDataTypeFieldDao projectManagerDataTypeFieldDao;

    @Override
    public ProjectManagerDataType loadDataType(int dataTypeId) {
        ProjectManagerDataType dataType = projectManagerDataTypeDao.load(dataTypeId);
        if (dataType == null) {
            return dataType;
        }
        // fill field datatype
        int maxRelateLevel = 5;
        dataType = fillFileDataType(dataType, maxRelateLevel);
        return dataType;
    }


    /**
     * @param '[dataType, maxRelateLevel]
     * @return com.tenacity.free.project.manager.po.ProjectManagerDataType
     * @class_name ProjectManagerDataTypeServiceImpl
     * @method fillFileDataType
     * @description parse field of datatype (注意，循环引用问题；此处显示最长引用链路长度为5；)
     * @author free.zhang
     * @date 2018/2/26 13:16
     */
    private ProjectManagerDataType fillFileDataType(ProjectManagerDataType dataType, int maxRelateLevel) {
        // init field list
        List<ProjectManagerDataTypeField> fieldList = projectManagerDataTypeFieldDao.findByParentDatatypeId(dataType.getId());
        dataType.setFieldList(fieldList);
        // parse field list
        if (CollectionUtils.isNotEmpty(dataType.getFieldList()) && maxRelateLevel > 0) {
            for (ProjectManagerDataTypeField field : dataType.getFieldList()) {
                ProjectManagerDataType fieldDataType = projectManagerDataTypeDao.load(field.getFieldDatatypeId());
                fieldDataType = fillFileDataType(fieldDataType, --maxRelateLevel);
                field.setFieldDatatype(fieldDataType);
            }
        }
        return dataType;
    }
}
