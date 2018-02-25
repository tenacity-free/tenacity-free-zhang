package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

import java.util.Date;

public class TenacityFreeProjectManagerDocumentPo extends BaseObject {
    private Integer id;

    private Integer projectId;

    private Integer groupId;

    private String name;

    private Byte status;

    private Byte starLevel;

    private String requestUrl;

    private String requestMethod;

    private Integer responseDatatypeId;

    private String successRespType;

    private String failRespType;

    private Date addTime;

    private Date updateTime;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Byte starLevel) {
        this.starLevel = starLevel;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    public Integer getResponseDatatypeId() {
        return responseDatatypeId;
    }

    public void setResponseDatatypeId(Integer responseDatatypeId) {
        this.responseDatatypeId = responseDatatypeId;
    }

    public String getSuccessRespType() {
        return successRespType;
    }

    public void setSuccessRespType(String successRespType) {
        this.successRespType = successRespType == null ? null : successRespType.trim();
    }

    public String getFailRespType() {
        return failRespType;
    }

    public void setFailRespType(String failRespType) {
        this.failRespType = failRespType == null ? null : failRespType.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}