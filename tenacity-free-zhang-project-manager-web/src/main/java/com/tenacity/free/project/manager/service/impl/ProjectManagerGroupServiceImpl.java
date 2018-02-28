package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerDocumentDao;
import com.tenacity.free.project.manager.dao.ProjectManagerGroupDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.po.ProjectManagerDocument;
import com.tenacity.free.project.manager.po.ProjectManagerGroup;
import com.tenacity.free.project.manager.po.ProjectManagerProject;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerGroupService;
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
 * @file_name ProjectManagerGroupServiceImpl.java
 * @description
 * @create 2018-02-28 10:48
 */
@Service("projectManagerGroupService")
public class ProjectManagerGroupServiceImpl implements ProjectManagerGroupService {

    @Resource(name = "projectManagerProjectDao")
    private ProjectManagerProjectDao projectManagerProjectDao;
    @Resource(name = "projectManagerGroupDao")
    private ProjectManagerGroupDao projectManagerGroupDao;
    @Resource(name = "projectManagerDocumentDao")
    private ProjectManagerDocumentDao projectManagerDocumentDao;

    @Override
    public String index(Model model, int productId, int groupId) {
        // 项目
        ProjectManagerProject projectManagerProject = projectManagerProjectDao.load(productId);
        if (projectManagerProject == null) {
            throw new RuntimeException("系统异常，项目ID非法");
        }
        model.addAttribute("productId", productId);
        model.addAttribute("project", projectManagerProject);

        // 分组列表
        List<ProjectManagerGroup> groupList = projectManagerGroupDao.loadAll(productId);
        model.addAttribute("groupList", groupList);

        // 选中分组
        ProjectManagerGroup groupInfo = null;
        if (CollectionUtils.isNotEmpty(groupList)) {
            for (ProjectManagerGroup groupItem : groupList) {
                if (groupId == groupItem.getId()) {
                    groupInfo = groupItem;
                }
            }
        }
        if (groupInfo == null && !(groupId == -1 || groupId == 0)) {
            // 合法性校验：全部(-1) | 默认分组(0) | 指定分组(匹配到数据)
            groupId = -1;
        }
        model.addAttribute("groupId", groupId);
        model.addAttribute("groupInfo", groupInfo);

        // 分组下的，接口列表
        List<ProjectManagerDocument> documentList = projectManagerDocumentDao.loadAll(productId, groupId);
        model.addAttribute("documentList", documentList);

        return "group/group.list";
    }

    @Override
    public ReturnT<String> add(ProjectManagerGroup projectManagerGroup) {
        // valid
        if (StringUtils.isBlank(projectManagerGroup.getName())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“分组名称”");
        }

        int ret = projectManagerGroupDao.add(projectManagerGroup);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> update(ProjectManagerGroup projectManagerGroup) {
        // exist
        ProjectManagerGroup existGroup = projectManagerGroupDao.load(projectManagerGroup.getId());
        if (existGroup == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "更新失败，分组ID非法");
        }

        // valid
        if (StringUtils.isBlank(projectManagerGroup.getName())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "请输入“分组名称”");
        }

        int ret = projectManagerGroupDao.update(projectManagerGroup);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(int id) {
        // 分组下是否存在接口
        List<ProjectManagerDocument> documentList = projectManagerDocumentDao.loadByGroupId(id);
        if (CollectionUtils.isNotEmpty(documentList)) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "拒绝删除，分组下存在接口，不允许强制删除");
        }

        int ret = projectManagerGroupDao.delete(id);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }
}
