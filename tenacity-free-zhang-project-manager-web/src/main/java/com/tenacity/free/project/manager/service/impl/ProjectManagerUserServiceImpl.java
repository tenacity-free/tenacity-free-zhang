package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerUserDao;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import com.tenacity.free.project.manager.util.CookieUtils;
import com.tenacity.free.project.manager.util.JacksonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service.impl
 * @file_name ProjectManagerUserServiceImpl.java
 * @description
 * @create 2018-02-26 12:39
 */
@Service("projectManagerUserService")
public class ProjectManagerUserServiceImpl implements ProjectManagerUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagerUserServiceImpl.class);

    @Resource(name = "projectManagerUserDao")
    private ProjectManagerUserDao projectManagerUserDao;

    @Override
    public ReturnT<String> login(HttpServletRequest request, HttpServletResponse response, boolean ifRemember, String userName, String password) {
        ProjectManagerUser loginUser = ifLogin(request);
        if (loginUser != null) {
            return ReturnT.SUCCESS;
        }

        ProjectManagerUser projectManagerUser = projectManagerUserDao.findByUserName(userName);
        if (projectManagerUser == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "登录用户不存在");
        }
        if (!projectManagerUser.getPassword().equals(password)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "登录密码错误");
        }

        String token = makeToken(projectManagerUser);
        CookieUtils.set(response, LOGIN_IDENTITY_KEY, token, ifRemember);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        ProjectManagerUser loginUser = ifLogin(request);
        if (loginUser == null) {
            return ReturnT.SUCCESS;
        }
        CookieUtils.remove(request, response, LOGIN_IDENTITY_KEY);
        return ReturnT.SUCCESS;
    }

    @Override
    public ProjectManagerUser ifLogin(HttpServletRequest request) {
        ProjectManagerUser loginUser = parseToken(request);
        return loginUser;
    }

    @Override
    public String index(Model model, HttpServletRequest request) {
        // permission
        ProjectManagerUser loginUser = (request.getAttribute(ProjectManagerUserServiceImpl.LOGIN_IDENTITY_KEY) != null) ? (ProjectManagerUser) request.getAttribute(ProjectManagerUserServiceImpl.LOGIN_IDENTITY_KEY) : null;
        if (loginUser.getType() != 1) {
            throw new RuntimeException("权限拦截.");
        }

        List<ProjectManagerUser> userList = projectManagerUserDao.loadAll();
        if (CollectionUtils.isEmpty(userList)) {
            userList = new ArrayList<>();
        } else {
            for (ProjectManagerUser user : userList) {
                user.setPassword("***");
            }
        }
        model.addAttribute("userList", JacksonUtils.writeValueAsString(userList));

        return "user/user.list";
    }

    @Override
    public ReturnT<String> add(ProjectManagerUser projectManagerUser) {
        // valid
        if (StringUtils.isBlank(projectManagerUser.getUserName())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入“登录账号”");
        }
        if (StringUtils.isBlank(projectManagerUser.getPassword())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请输入“登录密码”");
        }

        // valid
        ProjectManagerUser existUser = projectManagerUserDao.findByUserName(projectManagerUser.getUserName());
        if (existUser != null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "“登录账号”重复，请更换");
        }

        int ret = projectManagerUserDao.add(projectManagerUser);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> update(ProjectManagerUser projectManagerUser) {
        // exist
        ProjectManagerUser existUser = projectManagerUserDao.findByUserName(projectManagerUser.getUserName());
        if (existUser == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "更新失败，登录账号非法");
        }

        // update param
        if (StringUtils.isNotBlank(projectManagerUser.getPassword())) {
            existUser.setPassword(projectManagerUser.getPassword());
        }
        existUser.setType(projectManagerUser.getType());
        existUser.setRealName(projectManagerUser.getRealName());

        int ret = projectManagerUserDao.update(existUser);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Override
    public ReturnT<String> delete(HttpServletRequest request, int id) {
        // valid user
        ProjectManagerUser delUser = projectManagerUserDao.findById(id);
        if (delUser == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "拒绝删除，用户ID非法");
        }

        ProjectManagerUser loginUser = this.ifLogin(request);
        if (loginUser == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "拒绝删除，系统登录异常");
        }

        if (delUser.getUserName().equals(loginUser.getUserName())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "拒绝删除，不可以删除自己的用户信息");
        }

        // must leave one user
        List<ProjectManagerUser> allUser = projectManagerUserDao.loadAll();
        if (allUser == null || allUser.size() == 1) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "拒绝删除，系统至少保留一个登录用户");
        }

        int ret = projectManagerUserDao.delete(id);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    public static final String LOGIN_IDENTITY_KEY = "XXL_API_LOGIN_IDENTITY";

    private static String makeToken(ProjectManagerUser xxlApiUser) {
        String tokenStr = xxlApiUser.getUserName() + "_" + xxlApiUser.getPassword() + "_" + xxlApiUser.getType();
        String token = new BigInteger(1, tokenStr.getBytes()).toString(16);
        return token;
    }

    private static ProjectManagerUser parseToken(HttpServletRequest request) {
        String token = CookieUtils.getValue(request, LOGIN_IDENTITY_KEY);
        if (token != null) {
            String tokenStr = new String(new BigInteger(token, 16).toByteArray());
            String[] tokenArr = tokenStr.split("_");
            if (tokenArr != null && tokenArr.length == 3) {
                ProjectManagerUser xxlApiUser = new ProjectManagerUser();
                xxlApiUser.setUserName(tokenArr[0]);
                xxlApiUser.setPassword(tokenArr[1]);
                xxlApiUser.setType(Integer.valueOf(tokenArr[2]));
                return xxlApiUser;
            }
        }

        return null;
    }
}
