package com.tenacity.free.project.manager.po;

public class TenacityFreeProjectManagerDataTypeFiledsPo {
    private Integer id;

    private Integer parentDatatypeId;

    private String fieldName;

    private String fieldAbout;

    private Integer fieldDatatypeId;

    private Byte fieldType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentDatatypeId() {
        return parentDatatypeId;
    }

    public void setParentDatatypeId(Integer parentDatatypeId) {
        this.parentDatatypeId = parentDatatypeId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldAbout() {
        return fieldAbout;
    }

    public void setFieldAbout(String fieldAbout) {
        this.fieldAbout = fieldAbout == null ? null : fieldAbout.trim();
    }

    public Integer getFieldDatatypeId() {
        return fieldDatatypeId;
    }

    public void setFieldDatatypeId(Integer fieldDatatypeId) {
        this.fieldDatatypeId = fieldDatatypeId;
    }

    public Byte getFieldType() {
        return fieldType;
    }

    public void setFieldType(Byte fieldType) {
        this.fieldType = fieldType;
    }
}