package com.tenacity.free.project.manager.po;

public class ProjectManagerUser {

    // 用户ID
    private int id;
    // 账号
    private String userName;
    // 密码
    private String password;
    // 用户类型：0-普通用户、1-超级管理员
    private int type;
    // 真实姓名
    private String realName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
