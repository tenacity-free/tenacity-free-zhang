package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerBizDao;
import com.tenacity.free.project.manager.dao.ProjectManagerGroupDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import com.tenacity.free.project.manager.po.ProjectManagerGroup;
import com.tenacity.free.project.manager.po.ProjectManagerProject;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerProjectService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerProjectServiceImpl.java
 * @description
 * @create 2018-02-28 11:01
 */
@Service("projectManagerProjectService")
public class ProjectManagerProjectServiceImpl implements ProjectManagerProjectService {

    @Resource(name = "projectManagerProjectDao")
    private ProjectManagerProjectDao projectManagerProjectDao;
    @Resource(name = "projectManagerGroupDao")
    private ProjectManagerGroupDao projectManagerGroupDao;
    @Resource(name = "projectManagerBizDao")
    private ProjectManagerBizDao projectManagerBizDao;


    @Override
    public String index(Model model, int bizId) {
        // 业务线ID
        model.addAttribute("bizId", bizId);

        // 业务线列表
        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        model.addAttribute("bizList", bizList);

        return "project/project.list";
    }

    @Override
    public Map<String, Object> pageList(int start, int length, String name, int bizId) {
        // page list
        List<ProjectManagerProject> list = projectManagerProjectDao.pageList(start, length, name, bizId);
        int list_count = projectManagerProjectDao.pageListCount(start, length, name, bizId);

        // package result
        Map<String, Object> maps = new HashMap<>(4);
        // 总记录数
        maps.put("recordsTotal", list_count);
        // 过滤后的总记录数
        maps.put("recordsFiltered", list_count);
        // 分页列表
        maps.put("data", list);
        return maps;
    }

    @Override
    public ReturnT<String> add(ProjectManagerProject projectManagerProject) {
        // valid
        if (StringUtils.isBlank(projectManagerProject.getName())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入“项目名称”");
        }
        if (StringUtils.isBlank(projectManagerProject.getBaseUrlProduct())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入“跟地址：线上环境”");
        }

        int ret = projectManagerProjectDao.add(projectManagerProject);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> update(ProjectManagerProject projectManagerProject) {
        // exist
        ProjectManagerProject existProkect = projectManagerProjectDao.load(projectManagerProject.getId());
        if (existProkect == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "更新失败，项目ID非法");
        }

        // valid
        if (StringUtils.isBlank(projectManagerProject.getName())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入“项目名称”");
        }
        if (StringUtils.isBlank(projectManagerProject.getBaseUrlProduct())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入“跟地址：线上环境”");
        }

        int ret = projectManagerProjectDao.update(projectManagerProject);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(int id) {
        // 项目下是否存在分组
        List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(id);
        if (CollectionUtils.isNotEmpty(groupList)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "该项目下存在分组信息，拒绝删除");
        }

        int ret = projectManagerProjectDao.delete(id);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }
}
