package com.tenacity.free.project.manager.controller;

import com.tenacity.free.project.manager.controller.annotation.PermessionLimit;
import com.tenacity.free.project.manager.dao.ProjectManagerBizDao;
import com.tenacity.free.project.manager.dao.ProjectManagerProjectDao;
import com.tenacity.free.project.manager.po.ProjectManagerApiBiz;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.util.JacksonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/biz")
public class ProjectManagerBizController {

    @Resource(name = "projectManagerBizDao")
    private ProjectManagerBizDao projectManagerBizDao;
    @Resource(name = "projectManagerProjectDao")
    private ProjectManagerProjectDao projectManagerProjectDao;

    @RequestMapping
    public String index(Model model) {

        List<ProjectManagerApiBiz> bizList = projectManagerBizDao.loadAll();
        if (CollectionUtils.isEmpty(bizList)) {
            bizList = new ArrayList<>();
        }
        model.addAttribute("bizList", JacksonUtils.writeValueAsString(bizList));

        return "biz/biz.list";
    }

    @RequestMapping("/add")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> add(ProjectManagerApiBiz xxlApiBiz) {
        if (StringUtils.isBlank(xxlApiBiz.getBizName())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "业务线名称不可为空");
        }

        int ret = projectManagerBizDao.add(xxlApiBiz);
        return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

    @RequestMapping("/update")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> update(ProjectManagerApiBiz xxlApiBiz) {
        if (StringUtils.isBlank(xxlApiBiz.getBizName())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "业务线名称不可为空");
        }

        int ret = projectManagerBizDao.udpate(xxlApiBiz);
        return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

    @RequestMapping("/delete")
    @ResponseBody
    @PermessionLimit(superUser = true)
    public ReturnT<String> delete(int id) {

        int count = projectManagerProjectDao.pageListCount(0, 10, null, id);
        if (count > 0) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "业务线下存在项目，不允许删除");
        }

        int ret = projectManagerBizDao.delete(id);
        return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

}
