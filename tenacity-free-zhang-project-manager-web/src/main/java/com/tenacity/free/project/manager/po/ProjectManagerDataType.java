package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

import java.util.List;


public class ProjectManagerDataType extends BaseObject {

    private int id;
    // 数据类型名称
    private String name;
    // 数据类型描述
    private String about;
    // 业务线ID，业务线ID，为0表示公共
    private int bizId;
    // 参数列表
    private List<ProjectManagerDataTypeField> fieldList;

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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<ProjectManagerDataTypeField> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<ProjectManagerDataTypeField> fieldList) {
        this.fieldList = fieldList;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }
}
