package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

public class TenacityFreeProjectManagerGroupPo extends BaseObject {
    private Integer id;

    private Integer projectId;

    private String name;

    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}