package com.tenacity.free.project.manager.po;

public class ProjectManagerGroup {

    private int id;
    // 项目ID
    private int productId;
    // 分组名称
    private String name;
    // 分组排序
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
