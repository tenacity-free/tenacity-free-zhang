package com.tenacity.free.project.manager.po;


import com.tenacity.free.object.BaseObject;

public class ProjectManagerDataTypeField extends BaseObject {

    private int id;
    // 所属，数据类型ID
    private int parentDatatypeId;
    // 字段名称
    private String fieldName;
    // 字段描述
    private String fieldAbout;
    // 字段数据类型ID
    private int fieldDatatypeId;
    // 字段形式：0=默认、1=数组   @see FieldTypeEnum
    private int fieldType;

    // fieldDatatype dto
    private ProjectManagerDataType fieldDatatype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentDatatypeId() {
        return parentDatatypeId;
    }

    public void setParentDatatypeId(int parentDatatypeId) {
        this.parentDatatypeId = parentDatatypeId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldAbout() {
        return fieldAbout;
    }

    public void setFieldAbout(String fieldAbout) {
        this.fieldAbout = fieldAbout;
    }

    public int getFieldDatatypeId() {
        return fieldDatatypeId;
    }

    public void setFieldDatatypeId(int fieldDatatypeId) {
        this.fieldDatatypeId = fieldDatatypeId;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public ProjectManagerDataType getFieldDatatype() {
        return fieldDatatype;
    }

    public void setFieldDatatype(ProjectManagerDataType fieldDatatype) {
        this.fieldDatatype = fieldDatatype;
    }
}
