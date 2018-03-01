package com.tenacity.free.project.manager.po;


import com.tenacity.free.object.BaseObject;

public class ProjectManagerApiBiz extends BaseObject {

    private int id;
    private String bizName;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
