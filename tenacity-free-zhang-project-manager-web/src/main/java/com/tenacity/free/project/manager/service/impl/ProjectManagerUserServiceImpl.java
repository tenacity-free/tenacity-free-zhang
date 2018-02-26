package com.tenacity.free.project.manager.service.impl;

import com.tenacity.free.project.manager.dao.ProjectManagerUserDao;
import com.tenacity.free.project.manager.po.ProjectManagerUser;
import com.tenacity.free.project.manager.po.ReturnT;
import com.tenacity.free.project.manager.service.ProjectManagerUserService;
import com.tenacity.free.project.manager.util.CookieUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

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

    @Resource(name = "projectManagerUserDao")
    private ProjectManagerUserDao projectManagerUserDao;

    @Override
    public ReturnT<String> login(HttpServletRequest request, HttpServletResponse response, boolean ifRemember, String userName, String password) {
        ProjectManagerUser loginUser = ifLogin(request);
        if (loginUser != null) {
            return ReturnT.SUCCESS;
        }

        ProjectManagerUser xxlApiUser = projectManagerUserDao.findByUserName(userName);
        if (xxlApiUser == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "登录用户不存在");
        }
        if (!xxlApiUser.getPassword().equals(password)) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "登录密码错误");
        }

        String token = makeToken(xxlApiUser);
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

    public static final String LOGIN_IDENTITY_KEY = "XXL_API_LOGIN_IDENTITY";
    private static String makeToken (ProjectManagerUser xxlApiUser) {
        String tokenStr = xxlApiUser.getUserName() + "_" + xxlApiUser.getPassword() + "_" + xxlApiUser.getType();
        String token = new BigInteger(1, tokenStr.getBytes()).toString(16);
        return token;
    }
    private static ProjectManagerUser parseToken(HttpServletRequest request){
        String token = CookieUtils.getValue(request, LOGIN_IDENTITY_KEY);
        if (token != null) {
            String tokenStr = new String(new BigInteger(token, 16).toByteArray());
            String[] tokenArr = tokenStr.split("_");
            if (tokenArr!=null && tokenArr.length==3) {
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
