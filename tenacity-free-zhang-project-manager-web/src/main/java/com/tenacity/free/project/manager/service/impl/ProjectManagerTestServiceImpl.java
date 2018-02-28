package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.dao.ProjectManagerTestHistoryDao;
import com.tenacity.free.project.manager.po.ProjectManagerDocument;
import com.tenacity.free.project.manager.po.ProjectManagerProject;
import com.tenacity.free.project.manager.po.ProjectManagerTestHistory;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerTestService;
import com.tenacity.free.project.manager.util.JacksonUtils;
import com.tenacity.free.project.manager.util.consistant.RequestConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerTestServiceImpl.java
 * @description
 * @create 2018-02-28 11:07
 */
@Service("projectManagerTestService")
public class ProjectManagerTestServiceImpl implements ProjectManagerTestService {

    @Resource(name = "projectManagerDocumentDao")
    private ProjectManagerDocumentDao projectManagerDocumentDao;
    @Resource(name = "projectManagerTestHistoryDao")
    private ProjectManagerTestHistoryDao projectManagerTestHistoryDao;
    @Resource(name = "projectManagerProjectDao")
    private ProjectManagerProjectDao projectManagerProjectDao;


    @Override
    public String index(Model model, int documentId, int testId) {
        // params
        ProjectManagerProject project = null;
        ProjectManagerDocument document = null;
        List<Map<String, String>> requestHeaders = null;
        List<Map<String, String>> queryParams = null;

        if (testId > 0) {
            ProjectManagerTestHistory testHistory = projectManagerTestHistoryDao.load(testId);
            documentId = testHistory.getDocumentId();

            document = projectManagerDocumentDao.load(documentId);
            project = projectManagerProjectDao.load(document.getProjectId());

            requestHeaders = (StringUtils.isNotBlank(testHistory.getRequestHeaders())) ? JacksonUtils.readValue(testHistory.getRequestHeaders(), List.class) : null;
            queryParams = (StringUtils.isNotBlank(testHistory.getQueryParams())) ? JacksonUtils.readValue(testHistory.getQueryParams(), List.class) : null;
        } else {
            document = projectManagerDocumentDao.load(documentId);
            project = projectManagerProjectDao.load(document.getProjectId());

            requestHeaders = (StringUtils.isNotBlank(document.getRequestHeaders())) ? JacksonUtils.readValue(document.getRequestHeaders(), List.class) : null;
            queryParams = (StringUtils.isNotBlank(document.getQueryParams())) ? JacksonUtils.readValue(document.getQueryParams(), List.class) : null;
        }

        model.addAttribute("document", document);
        model.addAttribute("project", project);
        model.addAttribute("requestHeaders", requestHeaders);
        model.addAttribute("queryParams", queryParams);
        model.addAttribute("documentId", documentId);
        model.addAttribute("testId", testId);

        // enum
        model.addAttribute("RequestMethodEnum", RequestConfig.RequestMethodEnum.values());
        model.addAttribute("requestHeadersEnum", RequestConfig.requestHeadersEnum);
        model.addAttribute("QueryParamTypeEnum", RequestConfig.QueryParamTypeEnum.values());
        model.addAttribute("ResponseContentType", RequestConfig.ResponseContentType.values());

        return "test/test.index";
    }

    @Override
    public ReturnT<Integer> add(ProjectManagerTestHistory projectManagerTestHistory) {
        int ret = projectManagerTestHistoryDao.add(projectManagerTestHistory);
        return ret > 0 ? new ReturnT<>(projectManagerTestHistory.getId()) : new ReturnT<Integer>(ReturnT.FAIL_CODE, null);
    }

    @Override
    public ReturnT<String> update(ProjectManagerTestHistory projectManagerTestHistory) {
        int ret = projectManagerTestHistoryDao.update(projectManagerTestHistory);
        return ret > 0 ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(int id) {
        int ret = projectManagerTestHistoryDao.delete(id);
        return ret > 0 ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> run(ProjectManagerTestHistory projectManagerTestHistory, HttpServletRequest request, HttpServletResponse response) {
        // valid
        RequestConfig.ResponseContentType contentType = RequestConfig.ResponseContentType.match(projectManagerTestHistory.getRespType());
        if (contentType == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "响应数据类型(MIME)非法");
        }

        if (StringUtils.isBlank(projectManagerTestHistory.getRequestUrl())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入接口URL");
        }

        // request headers
        Map<String, String> requestHeaderMap = null;
        List<Map<String, String>> requestHeaders = (StringUtils.isNotBlank(projectManagerTestHistory.getRequestHeaders())) ? JacksonUtils.readValue(projectManagerTestHistory.getRequestHeaders(), List.class) : null;
        if (CollectionUtils.isNotEmpty(requestHeaders)) {
            requestHeaderMap = new HashMap<>(requestHeaders.size());
            for (Map<String, String> item : requestHeaders) {
                requestHeaderMap.put(item.get("key"), item.get("value"));
            }
        }

        // query param
        Map<String, String> queryParamMap = null;
        List<Map<String, String>> queryParams = (StringUtils.isNotBlank(projectManagerTestHistory.getQueryParams())) ? JacksonUtils.readValue(projectManagerTestHistory.getQueryParams(), List.class) : null;
        if (CollectionUtils.isNotEmpty(queryParams)) {
            queryParamMap = new HashMap<>(queryParams.size());
            for (Map<String, String> item : queryParams) {
                queryParamMap.put(item.get("key"), item.get("value"));
            }
        }

        // invoke 1/3
        HttpRequestBase remoteRequest = null;
        if (RequestConfig.RequestMethodEnum.POST.name().equals(projectManagerTestHistory.getRequestMethod())) {
            HttpPost httpPost = new HttpPost(projectManagerTestHistory.getRequestUrl());
            // query params
            if (queryParamMap != null && !queryParamMap.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<>();
                for (Map.Entry<String, String> entry : queryParamMap.entrySet()) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            remoteRequest = httpPost;
        } else if (RequestConfig.RequestMethodEnum.GET.name().equals(projectManagerTestHistory.getRequestMethod())) {
            remoteRequest = new HttpGet(markGetUrl(projectManagerTestHistory.getRequestUrl(), queryParamMap));
        } else if (RequestConfig.RequestMethodEnum.PUT.name().equals(projectManagerTestHistory.getRequestMethod())) {
            remoteRequest = new HttpPut(markGetUrl(projectManagerTestHistory.getRequestUrl(), queryParamMap));
        } else if (RequestConfig.RequestMethodEnum.DELETE.name().equals(projectManagerTestHistory.getRequestMethod())) {
            remoteRequest = new HttpDelete(markGetUrl(projectManagerTestHistory.getRequestUrl(), queryParamMap));
        } else if (RequestConfig.RequestMethodEnum.HEAD.name().equals(projectManagerTestHistory.getRequestMethod())) {
            remoteRequest = new HttpHead(markGetUrl(projectManagerTestHistory.getRequestUrl(), queryParamMap));
        } else if (RequestConfig.RequestMethodEnum.OPTIONS.name().equals(projectManagerTestHistory.getRequestMethod())) {
            remoteRequest = new HttpOptions(markGetUrl(projectManagerTestHistory.getRequestUrl(), queryParamMap));
        } else if (RequestConfig.RequestMethodEnum.PATCH.name().equals(projectManagerTestHistory.getRequestMethod())) {
            remoteRequest = new HttpPatch(markGetUrl(projectManagerTestHistory.getRequestUrl(), queryParamMap));
        } else {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请求方法非法");
        }

        // invoke 2/3
        if (requestHeaderMap != null && !requestHeaderMap.isEmpty()) {
            for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
                remoteRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // write response
        String responseContent = remoteCall(remoteRequest);
        return new ReturnT<>(responseContent);
    }

    @Override
    public String markGetUrl(String url, Map<String, String> queryParamMap) {
        String finalUrl = url + "?";
        if (queryParamMap != null && !queryParamMap.isEmpty()) {
            for (Map.Entry<String, String> entry : queryParamMap.entrySet()) {
                finalUrl += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        finalUrl = finalUrl.substring(0, finalUrl.length() - 2);
        return finalUrl;
    }

    @Override
    public String remoteCall(HttpRequestBase remoteRequest) {
        // remote test
        String responseContent = null;

        CloseableHttpClient httpClient = null;
        try {
            org.apache.http.client.config.RequestConfig requestConfig = org.apache.http.client.config.RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            remoteRequest.setConfig(requestConfig);

            httpClient = HttpClients.custom().disableAutomaticRetries().build();

            // parse response
            HttpResponse response = httpClient.execute(remoteRequest);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    responseContent = EntityUtils.toString(entity, "UTF-8");
                } else {
                    responseContent = "请求状态异常：" + response.getStatusLine().getStatusCode();
                    if (statusCode == 302) {
                        responseContent += "；Redirect地址：" + response.getHeaders("Location");
                    }

                }
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            responseContent = "请求异常：" + e.getMessage();
        } finally {
            if (remoteRequest != null) {
                remoteRequest.releaseConnection();
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseContent;
    }
}
