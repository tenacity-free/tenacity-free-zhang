package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.*;
import com.tenacity.free.project.manager.po.*;
import com.tenacity.free.project.manager.service.ProjectManagerDataTypeService;
import com.tenacity.free.project.manager.service.ProjectManagerDocumentService;
import com.tenacity.free.project.manager.util.JacksonUtils;
import com.tenacity.free.project.manager.util.consistant.RequestConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerDocumentServiceImpl.java
 * @description
 * @create 2018-02-28 10:41
 */
@Service("projectManagerDocumentService")
public class ProjectManagerDocumentServiceImpl implements ProjectManagerDocumentService {

    @Resource(name = "projectManagerDocumentDao")
    private ProjectManagerDocumentDao projectManagerDocumentDao;
    @Resource(name = "projectManagerProjectDao")
    private ProjectManagerProjectDao projectManagerProjectDao;
    @Resource(name = "projectManagerGroupDao")
    private ProjectManagerGroupDao projectManagerGroupDao;
    @Resource(name = "projectManagerMockDao")
    private ProjectManagerMockDao projectManagerMockDao;
    @Resource(name = "projectManagerTestHistoryDao")
    private ProjectManagerTestHistoryDao projectManagerTestHistoryDao;
    @Resource(name = "projectManagerDataTypeService")
    private ProjectManagerDataTypeService projectManagerDataTypeService;

    @Override
    public ReturnT<String> markStar(int id, int starLevel) {
        ProjectManagerDocument document = projectManagerDocumentDao.load(id);
        if (document == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "操作失败，接口ID非法");
        }

        document.setStarLevel(starLevel);

        int ret = projectManagerDocumentDao.update(document);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(int id) {
        // 存在Test记录，拒绝删除
        List<ProjectManagerTestHistory> historyList = projectManagerTestHistoryDao.loadByDocumentId(id);
        if (CollectionUtils.isNotEmpty(historyList)) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，该接口下存在Test记录，不允许删除");
        }

        // 存在Mock记录，拒绝删除
        List<ProjectManagerMock> mockList = projectManagerMockDao.loadAll(id);
        if (CollectionUtils.isNotEmpty(mockList)) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，该接口下存在Mock记录，不允许删除");
        }

        int ret = projectManagerDocumentDao.delete(id);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public String addPage(Model model, int productId) {
        // project
        ProjectManagerProject project = projectManagerProjectDao.load(productId);
        if (project == null) {
            throw new RuntimeException("操作失败，项目ID非法");
        }
        model.addAttribute("productId", productId);

        // groupList
        List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(productId);
        model.addAttribute("groupList", groupList);

        // enum
        model.addAttribute("RequestMethodEnum", RequestConfig.RequestMethodEnum.values());
        model.addAttribute("requestHeadersEnum", RequestConfig.requestHeadersEnum);
        model.addAttribute("QueryParamTypeEnum", RequestConfig.QueryParamTypeEnum.values());
        model.addAttribute("ResponseContentType", RequestConfig.ResponseContentType.values());

        return "document/document.add";
    }

    @Override
    public ReturnT<Integer> add(ProjectManagerDocument projectManagerDocument) {
        int ret = projectManagerDocumentDao.add(projectManagerDocument);
        return (ret > 0) ? new ReturnT<>(projectManagerDocument.getId()) : new ReturnT<>(ReturnT.FAIL_CODE, null);
    }

    @Override
    public String updatePage(Model model, int id) {
        // document
        ProjectManagerDocument xxlApiDocument = projectManagerDocumentDao.load(id);
        if (xxlApiDocument == null) {
            throw new RuntimeException("操作失败，接口ID非法");
        }
        model.addAttribute("document", xxlApiDocument);
        model.addAttribute("requestHeadersList", (StringUtils.isNotBlank(xxlApiDocument.getRequestHeaders())) ? JacksonUtils.readValue(xxlApiDocument.getRequestHeaders(), List.class) : null);
        model.addAttribute("queryParamList", (StringUtils.isNotBlank(xxlApiDocument.getQueryParams())) ? JacksonUtils.readValue(xxlApiDocument.getQueryParams(), List.class) : null);
        model.addAttribute("responseParamList", (StringUtils.isNotBlank(xxlApiDocument.getResponseParams())) ? JacksonUtils.readValue(xxlApiDocument.getResponseParams(), List.class) : null);

        // project
        int projectId = xxlApiDocument.getProjectId();
        model.addAttribute("productId", projectId);

        // groupList
        List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(projectId);
        model.addAttribute("groupList", groupList);

        // enum
        model.addAttribute("RequestMethodEnum", RequestConfig.RequestMethodEnum.values());
        model.addAttribute("requestHeadersEnum", RequestConfig.requestHeadersEnum);
        model.addAttribute("QueryParamTypeEnum", RequestConfig.QueryParamTypeEnum.values());
        model.addAttribute("ResponseContentType", RequestConfig.ResponseContentType.values());

        // 响应数据类型
        ProjectManagerDataType responseDatatypeRet = projectManagerDataTypeService.loadDataType(xxlApiDocument.getResponseDatatypeId());
        model.addAttribute("responseDatatype", responseDatatypeRet);

        return "document/document.update";
    }

    @Override
    public ReturnT<String> update(ProjectManagerDocument projectManagerDocument) {
        // fill not-change val
        ProjectManagerDocument oldVo = projectManagerDocumentDao.load(projectManagerDocument.getId());
        projectManagerDocument.setProjectId(oldVo.getProjectId());
        projectManagerDocument.setStarLevel(oldVo.getStarLevel());
        projectManagerDocument.setAddTime(oldVo.getAddTime());

        int ret = projectManagerDocumentDao.update(projectManagerDocument);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public String detailPage(Model model, int id) {
        // document
        ProjectManagerDocument xxlApiDocument = projectManagerDocumentDao.load(id);
        if (xxlApiDocument == null) {
            throw new RuntimeException("操作失败，接口ID非法");
        }
        model.addAttribute("document", xxlApiDocument);
        model.addAttribute("requestHeadersList", (StringUtils.isNotBlank(xxlApiDocument.getRequestHeaders())) ? JacksonUtils.readValue(xxlApiDocument.getRequestHeaders(), List.class) : null);
        model.addAttribute("queryParamList", (StringUtils.isNotBlank(xxlApiDocument.getQueryParams())) ? JacksonUtils.readValue(xxlApiDocument.getQueryParams(), List.class) : null);
        model.addAttribute("responseParamList", (StringUtils.isNotBlank(xxlApiDocument.getResponseParams())) ? JacksonUtils.readValue(xxlApiDocument.getResponseParams(), List.class) : null);

        // project
        int projectId = xxlApiDocument.getProjectId();
        ProjectManagerProject project = projectManagerProjectDao.load(projectId);
        model.addAttribute("productId", projectId);
        model.addAttribute("project", project);

        // groupList
        List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(projectId);
        model.addAttribute("groupList", groupList);

        // mock list
        List<ProjectManagerMock> mockList = projectManagerMockDao.loadAll(id);
        model.addAttribute("mockList", mockList);

        // test list
        List<ProjectManagerTestHistory> testHistoryList = projectManagerTestHistoryDao.loadByDocumentId(id);
        model.addAttribute("testHistoryList", testHistoryList);

        // enum
        model.addAttribute("RequestMethodEnum", RequestConfig.RequestMethodEnum.values());
        model.addAttribute("requestHeadersEnum", RequestConfig.requestHeadersEnum);
        model.addAttribute("QueryParamTypeEnum", RequestConfig.QueryParamTypeEnum.values());
        model.addAttribute("ResponseContentType", RequestConfig.ResponseContentType.values());

        // 响应数据类型
        ProjectManagerDataType responseDatatypeRet = projectManagerDataTypeService.loadDataType(xxlApiDocument.getResponseDatatypeId());
        model.addAttribute("responseDatatype", responseDatatypeRet);

        return "document/document.detail";
    }
}
