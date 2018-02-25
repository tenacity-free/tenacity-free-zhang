package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

public class TenacityFreeProjectManagerMockPo extends BaseObject {
    private Integer id;

    private Integer documentId;

    private String uuid;

    private String respType;

    private String respExample;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType == null ? null : respType.trim();
    }

    public String getRespExample() {
        return respExample;
    }

    public void setRespExample(String respExample) {
        this.respExample = respExample == null ? null : respExample.trim();
    }
}