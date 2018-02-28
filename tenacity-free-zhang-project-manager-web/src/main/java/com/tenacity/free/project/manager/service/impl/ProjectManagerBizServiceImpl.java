package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerBizDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerBizService;
import com.tenacity.free.project.manager.util.JacksonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerBizServiceImpl.java
 * @description
 * @create 2018-02-28 10:31
 */
@Service("projectManagerBizService")
public class ProjectManagerBizServiceImpl implements ProjectManagerBizService {

    @Resource(name = "projectManagerBizDao")
    private ProjectManagerBizDao projectManagerBizDao;
    @Resource(name = "projectManagerProjectDao")
    private ProjectManagerProjectDao projectManagerProjectDao;


    @Override
    public String index(Model model) {
        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        if (CollectionUtils.isEmpty(bizList)) {
            bizList = new ArrayList<>();
        }
        model.addAttribute("bizList", JacksonUtils.writeValueAsString(bizList));

        return "biz/biz.list";
    }

    @Override
    public ReturnT<String> add(ProjectManagerApiBiz projectManagerApiBiz) {
        if (StringUtils.isBlank(projectManagerApiBiz.getBizName())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "业务线名称不可为空");
        }

        int ret = projectManagerBizDao.add(projectManagerApiBiz);
        return ret > 0 ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> update(ProjectManagerApiBiz projectManagerApiBiz) {
        if (StringUtils.isBlank(projectManagerApiBiz.getBizName())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "业务线名称不可为空");
        }

        int ret = projectManagerBizDao.udpate(projectManagerApiBiz);
        return ret > 0 ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(int id) {
        int count = projectManagerProjectDao.pageListCount(0, 10, null, id);
        if (count > 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "业务线下存在项目，不允许删除");
        }

        int ret = projectManagerBizDao.delete(id);
        return ret > 0 ? ReturnT.SUCCESS : ReturnT.FAIL;
    }
}
