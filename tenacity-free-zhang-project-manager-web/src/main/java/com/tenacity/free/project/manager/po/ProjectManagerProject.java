package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

public class ProjectManagerProject extends BaseObject {

    // 项目ID
    private int id;
    // 项目名称
    private String name;
    // 项目描述
    private String desc;
    // 访问权限：0-公开、1-私有
    private int permission;
    // 跟地址：线上环境
    private String baseUrlProduct;
    // 跟地址：预发布环境
    private String baseUrlPpe;
    // 跟地址：测试环境
    private String baseUrlQa;
    // 业务线ID
    private int bizId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getBaseUrlProduct() {
        return baseUrlProduct;
    }

    public void setBaseUrlProduct(String baseUrlProduct) {
        this.baseUrlProduct = baseUrlProduct;
    }

    public String getBaseUrlPpe() {
        return baseUrlPpe;
    }

    public void setBaseUrlPpe(String baseUrlPpe) {
        this.baseUrlPpe = baseUrlPpe;
    }

    public String getBaseUrlQa() {
        return baseUrlQa;
    }

    public void setBaseUrlQa(String baseUrlQa) {
        this.baseUrlQa = baseUrlQa;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }
}
