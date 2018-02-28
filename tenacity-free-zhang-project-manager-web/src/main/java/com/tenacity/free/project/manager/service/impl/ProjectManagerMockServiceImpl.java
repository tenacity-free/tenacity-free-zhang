package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.dao.ProjectManagerMockDao;
import com.tenacity.free.project.manager.po.ProjectManagerDocument;
import com.tenacity.free.project.manager.po.ProjectManagerMock;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerMockService;
import com.tenacity.free.project.manager.util.consistant.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerMockServiceImpl.java
 * @description
 * @create 2018-02-28 10:56
 */
@Service("projectManagerMockService")
public class ProjectManagerMockServiceImpl implements ProjectManagerMockService {

    private static Logger logger = LoggerFactory.getLogger(ProjectManagerMockServiceImpl.class);

    @Resource(name = "projectManagerMockDao")
    private ProjectManagerMockDao projectManagerMockDao;
    @Resource(name = "projectManagerDocumentDao")
    private ProjectManagerDocumentDao projectManagerDocumentDao;

    @Override
    public ReturnT<String> add(ProjectManagerMock projectManagerMock) {
        ProjectManagerDocument document = projectManagerDocumentDao.load(projectManagerMock.getDocumentId());
        if (document == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "保存Mock数据失败，接口ID非法");
        }
        String uuid = UUID.randomUUID().toString();

        projectManagerMock.setUuid(uuid);
        int ret = projectManagerMockDao.add(projectManagerMock);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(int id) {
        int ret = projectManagerMockDao.delete(id);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> update(ProjectManagerMock projectManagerMock) {
        int ret = projectManagerMockDao.update(projectManagerMock);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public void run(String uuid, HttpServletRequest request, HttpServletResponse response) {
        ProjectManagerMock xxlApiMock = projectManagerMockDao.loadByUuid(uuid);
        if (xxlApiMock == null) {
            throw new RuntimeException("Mock数据ID非法");
        }

        RequestConfig.ResponseContentType contentType = RequestConfig.ResponseContentType.match(xxlApiMock.getRespType());
        if (contentType == null) {
            throw new RuntimeException("Mock数据响应数据类型(MIME)非法");
        }
        // write response
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(contentType.type);

            PrintWriter writer = response.getWriter();
            writer.write(xxlApiMock.getRespExample());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
