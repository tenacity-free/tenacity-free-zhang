package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.dao.ProjectManagerBizDao;
import com.tenacity.free.project.manager.dao.ProjectManagerDataTypeDao;
import com.tenacity.free.project.manager.dao.ProjectManagerDataTypeFieldDao;
import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.po.*;
import com.tenacity.free.project.manager.service.ProjectManagerDataTypeService;
import com.tenacity.free.project.manager.util.JacksonUtils;
import com.tenacity.free.project.manager.util.ProjectManagerDataTypeToCode;
import com.tenacity.free.project.manager.util.consistant.FieldTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/datatype")
public class ProjectManagerDataTypeController {

    @Resource(name = "projectManagerDataTypeDao")
    private ProjectManagerDataTypeDao projectManagerDataTypeDao;
    @Resource(name = "projectManagerDataTypeFieldDao")
    private ProjectManagerDataTypeFieldDao projectManagerDataTypeFieldDao;
    @Resource(name = "projectManagerBizDao")
    private ProjectManagerBizDao projectManagerBizDao;
    @Resource(name = "projectManagerDocumentDao")
    private ProjectManagerDocumentDao projectManagerDocumentDao;
    @Resource(name = "projectManagerDataTypeService")
    private ProjectManagerDataTypeService projectManagerDataTypeService;


    @RequestMapping
    public String index(Model model) {

        // 业务线列表
        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        model.addAttribute("bizList", bizList);

        return "datatype/datatype.list";
    }


    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        int bizId, String name) {
        // page list
        List<ProjectManagerDataType> list = projectManagerDataTypeDao.pageList(start, length, bizId, name);
        int count = projectManagerDataTypeDao.pageListCount(start, length, bizId, name);

        // package result
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("recordsTotal", count);		// 总记录数
        maps.put("recordsFiltered", count);	    // 过滤后的总记录数
        maps.put("data", list);  				// 分页列表
        return maps;
    }


    @RequestMapping("/addDataTypePage")
    public String addDataTypePage(Model model) {

        // 业务线列表
        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        model.addAttribute("bizList", bizList);

        return "datatype/datatype.add";
    }

    @RequestMapping("/addDataType")
    @ResponseBody
    public ReturnT<Integer> addDataType(ProjectManagerDataType apiDataTypeDTO, String fieldTypeJson) {
        // parse json field
        if (StringUtils.isNotBlank(fieldTypeJson)) {
            List<ProjectManagerDataTypeField> fieldList = JacksonUtils.readValueRefer(fieldTypeJson, new TypeReference<List<ProjectManagerDataTypeField>>() { });
            if (CollectionUtils.isNotEmpty(fieldList)) {
                apiDataTypeDTO.setFieldList(fieldList);
            }
        }

        // valid datatype
        if (StringUtils.isBlank(apiDataTypeDTO.getName())) {
            return new ReturnT<Integer>(ReturnT.FAIL_CODE, "数据类型名称不可为空");
        }
        if (StringUtils.isBlank(apiDataTypeDTO.getAbout())) {
            return new ReturnT<Integer>(ReturnT.FAIL_CODE, "数据类型描述不可为空");
        }
        ProjectManagerDataType existsByName = projectManagerDataTypeDao.loadByName(apiDataTypeDTO.getName());
        if (existsByName != null) {
            return new ReturnT<Integer>(ReturnT.FAIL_CODE, "数据类型名称不可重复，请更换");
        }
        // valid field
        if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
            for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                // valid
                if (StringUtils.isBlank(field.getFieldName())) {
                    return new ReturnT<Integer>(ReturnT.FAIL_CODE, "字段名称不可为空");
                }

                ProjectManagerDataType filedDataType = projectManagerDataTypeService.loadDataType(field.getFieldDatatypeId());
                if (filedDataType == null) {
                    return new ReturnT<Integer>(ReturnT.FAIL_CODE, "字段数据类型ID非法");
                }

                if (FieldTypeEnum.match(field.getFieldType())==null) {
                    return new ReturnT<Integer>(ReturnT.FAIL_CODE, "字段形式非法");
                }
            }
        }

        // add datatype
        int addRet = projectManagerDataTypeDao.add(apiDataTypeDTO);
        if (addRet < 1) {
            return new ReturnT<Integer>(ReturnT.FAIL_CODE, "数据类型新增失败");
        }
        if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
            for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                field.setParentDatatypeId(apiDataTypeDTO.getId());
            }
            // add field
            int ret = projectManagerDataTypeFieldDao.add(apiDataTypeDTO.getFieldList());
            if (ret < 1) {
                return new ReturnT<Integer>(ReturnT.FAIL_CODE, "数据类型新增失败");
            }
        }

        return apiDataTypeDTO.getId()>0? new ReturnT<Integer>(apiDataTypeDTO.getId()) : new ReturnT<Integer>(ReturnT.FAIL_CODE, "");
    }

    @RequestMapping("/updateDataTypePage")
    public String updateDataTypePage(Model model, int dataTypeId) {

        ProjectManagerDataType apiDataType = projectManagerDataTypeService.loadDataType(dataTypeId);
        if (apiDataType == null) {
            throw new RuntimeException("数据类型ID非法");
        }
        model.addAttribute("apiDataType", apiDataType);

        // 业务线列表
        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        model.addAttribute("bizList", bizList);

        return "datatype/datatype.update";
    }

    @RequestMapping("/updateDataType")
    @ResponseBody
    public ReturnT<String> updateDataType(ProjectManagerDataType apiDataTypeDTO, String fieldTypeJson) {
        // parse json field
        if (StringUtils.isNotBlank(fieldTypeJson)) {
            List<ProjectManagerDataTypeField> fieldList = JacksonUtils.readValueRefer(fieldTypeJson, new TypeReference<List<ProjectManagerDataTypeField>>() { });
            if (CollectionUtils.isNotEmpty(fieldList)) {
                apiDataTypeDTO.setFieldList(fieldList);
            }
        }

        // valid datatype
        if (StringUtils.isBlank(apiDataTypeDTO.getName())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "数据类型名称不可为空");
        }
        if (StringUtils.isBlank(apiDataTypeDTO.getAbout())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "数据类型描述不可为空");
        }
        ProjectManagerDataType existsByName = projectManagerDataTypeDao.loadByName(apiDataTypeDTO.getName());
        if (existsByName != null && existsByName.getId()!=apiDataTypeDTO.getId()) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "数据类型名称不可重复，请更换");
        }
        // valid field
        if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
            for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                // valid
                if (StringUtils.isBlank(field.getFieldName())) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, "字段名称不可为空");
                }

                ProjectManagerDataType filedDataType = projectManagerDataTypeDao.load(field.getFieldDatatypeId());
                if (filedDataType == null) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, "字段数据类型ID非法");
                }

                if (FieldTypeEnum.match(field.getFieldType())==null) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, "字段形式非法");
                }
            }
        }

        // update datatype
        int ret1 = projectManagerDataTypeDao.update(apiDataTypeDTO);
        if (ret1 > 0) {
            // remove old, add new
            projectManagerDataTypeFieldDao.deleteByParentDatatypeId(apiDataTypeDTO.getId());
            if (CollectionUtils.isNotEmpty(apiDataTypeDTO.getFieldList())) {
                for (ProjectManagerDataTypeField field: apiDataTypeDTO.getFieldList()) {
                    field.setParentDatatypeId(apiDataTypeDTO.getId());
                }
                int ret = projectManagerDataTypeFieldDao.add(apiDataTypeDTO.getFieldList());
                if (ret < 1) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, "数据类型新增失败");
                }
            }
        }
        return ret1>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

    @RequestMapping("/dataTypeDetail")
    public String dataTypeDetail(Model model, int dataTypeId) {

        ProjectManagerDataType apiDataType = projectManagerDataTypeService.loadDataType(dataTypeId);
        if (apiDataType == null) {
            throw new RuntimeException("数据类型ID非法");
        }
        model.addAttribute("apiDataType", apiDataType);

        // 业务线列表
        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        model.addAttribute("bizList", bizList);

        // 代码生成
        String codeContent = ProjectManagerDataTypeToCode.parseDataTypeToCode(apiDataType);
        model.addAttribute("codeContent", codeContent);

        return "datatype/datatype.detail";
    }

    @RequestMapping("/deleteDataType")
    @ResponseBody
    public ReturnT<String> deleteDataType(int id) {
        // 被其他数据类型引用，拒绝删除
        List<ProjectManagerDataTypeField> list = projectManagerDataTypeFieldDao.findByFieldDatatypeId(id);
        if (CollectionUtils.isNotEmpty(list)) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "该数据类型被引用中，拒绝删除");
        }

        // 该数据类型被API引用，拒绝删除
        List<ProjectManagerDocument> apiList = projectManagerDocumentDao.findByResponseDataTypeId(id);
        if (CollectionUtils.isNotEmpty(apiList)) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "该数据类型被API引用，拒绝删除");
        }

        // delete
        int  ret = projectManagerDataTypeDao.delete(id);
        projectManagerDataTypeFieldDao.deleteByParentDatatypeId(id);
        return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

}
