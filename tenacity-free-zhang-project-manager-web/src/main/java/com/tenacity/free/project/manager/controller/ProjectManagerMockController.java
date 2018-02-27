package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.dao.ProjectManagerMockDao;
import com.tenacity.free.project.manager.po.ProjectManagerDocument;
import com.tenacity.free.project.manager.po.ProjectManagerMock;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.util.consistant.RequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.UUID;


@Controller
@RequestMapping("/mock")
public class ProjectManagerMockController {
	private static Logger logger = LoggerFactory.getLogger(ProjectManagerMockController.class);

	@Resource(name = "projectManagerMockDao")
	private ProjectManagerMockDao projectManagerMockDao;
	@Resource(name = "projectManagerDocumentDao")
	private ProjectManagerDocumentDao projectManagerDocumentDao;

	/**
	 * 保存Mock数据
	 * @param xxlApiMock
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(ProjectManagerMock xxlApiMock) {

		ProjectManagerDocument document = projectManagerDocumentDao.load(xxlApiMock.getDocumentId());
		if (document == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "保存Mock数据失败，接口ID非法");
		}
		String uuid = UUID.randomUUID().toString();

		xxlApiMock.setUuid(uuid);
		int ret = projectManagerMockDao.add(xxlApiMock);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ReturnT<String> delete(int id) {
		int ret = projectManagerMockDao.delete(id);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(ProjectManagerMock xxlApiMock) {
		int ret = projectManagerMockDao.update(xxlApiMock);
		return (ret>0)?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	@RequestMapping("/run/{uuid}")
	@PermessionLimit(limit=false)
	public void run(@PathVariable("uuid") String uuid, HttpServletRequest request, HttpServletResponse response) {
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
