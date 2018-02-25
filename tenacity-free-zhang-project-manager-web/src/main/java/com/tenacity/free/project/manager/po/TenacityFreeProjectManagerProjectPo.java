package com.tenacity.free.project.manager.po;

import com.tenacity.free.object.BaseObject;

public class TenacityFreeProjectManagerProjectPo extends BaseObject {
    private Integer id;

    private String name;

    private String desc;

    private Byte permission;

    private String baseUrlProduct;

    private String baseUrlPpe;

    private String baseUrlQa;

    private Integer bizId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    public String getBaseUrlProduct() {
        return baseUrlProduct;
    }

    public void setBaseUrlProduct(String baseUrlProduct) {
        this.baseUrlProduct = baseUrlProduct == null ? null : baseUrlProduct.trim();
    }

    public String getBaseUrlPpe() {
        return baseUrlPpe;
    }

    public void setBaseUrlPpe(String baseUrlPpe) {
        this.baseUrlPpe = baseUrlPpe == null ? null : baseUrlPpe.trim();
    }

    public String getBaseUrlQa() {
        return baseUrlQa;
    }

    public void setBaseUrlQa(String baseUrlQa) {
        this.baseUrlQa = baseUrlQa == null ? null : baseUrlQa.trim();
    }

    public Integer getBizId() {
        return bizId;
    }

    public void setBizId(Integer bizId) {
        this.bizId = bizId;
    }
}