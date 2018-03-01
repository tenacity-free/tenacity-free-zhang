package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

public class ProjectManagerMock extends BaseObject {

    private int id;
    // 接口ID
    private int documentId;
    private String uuid;
    // Response Content-type：如JSON、XML、HTML、TEXT、JSONP
    private String respType;
    // Response Content
    private String respExample;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getRespExample() {
        return respExample;
    }

    public void setRespExample(String respExample) {
        this.respExample = respExample;
    }
}
