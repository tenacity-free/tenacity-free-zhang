package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

public class TenacityFreeProjectManagerBizPo extends BaseObject {
    private Integer id;

    private String bizName;

    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName == null ? null : bizName.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}