package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

import java.util.Date;


public class ProjectManagerDocument extends BaseObject {

    // 接口ID
    private int id;
    // 项目ID
    private int projectId;
    // 分组ID
    private int groupId;
    // 接口名称
    private String name;
    // 状态：0-启用、1-维护、2-废弃
    private int status;
    // 星标等级：0-普通接口、1-一星接口
    private int starLevel;
    // Request URL：相对地址
    private String requestUrl;
    // Request Method：如POST、GET
    private String requestMethod;
    // Request Headers：Map-JSON格式字符串
    private String requestHeaders;
    // Query String Parameters：VO-JSON格式字符串
    private String queryParams;
    // 响应数据类型ID
    private int responseDatatypeId;
    // Response Parameters：VO-JSON格式字符串
    private String responseParams;
    // Response Content-type：成功接口，如JSON、XML、HTML、TEXT
    private String successRespType;
    // Response Content：成功接口
    private String successRespExample;
    // Response Content-type：失败接口
    private String failRespType;
    // Response Content：失败接口
    private String failRespExample;
    // 备注
    private String remark;
    // 创建时间
    private Date addTime;
    // 更新时间
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getResponseParams() {
        return responseParams;
    }

    public void setResponseParams(String responseParams) {
        this.responseParams = responseParams;
    }

    public int getResponseDatatypeId() {
        return responseDatatypeId;
    }

    public void setResponseDatatypeId(int responseDatatypeId) {
        this.responseDatatypeId = responseDatatypeId;
    }

    public String getSuccessRespType() {
        return successRespType;
    }

    public void setSuccessRespType(String successRespType) {
        this.successRespType = successRespType;
    }

    public String getSuccessRespExample() {
        return successRespExample;
    }

    public void setSuccessRespExample(String successRespExample) {
        this.successRespExample = successRespExample;
    }

    public String getFailRespType() {
        return failRespType;
    }

    public void setFailRespType(String failRespType) {
        this.failRespType = failRespType;
    }

    public String getFailRespExample() {
        return failRespExample;
    }

    public void setFailRespExample(String failRespExample) {
        this.failRespExample = failRespExample;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
