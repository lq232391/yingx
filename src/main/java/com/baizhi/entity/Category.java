package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;


public class Category implements Serializable {
    private String id;
    private String cateName;
    private  String levels;
    private String parentId;
    private List<Category> categoryList;
    public Category() {
        super();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", cateName='" + cateName + '\'' +
                ", levels='" + levels + '\'' +
                ", parentId='" + parentId + '\'' +
                ", categoryList=" + categoryList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Category(String id, String cateName, String levels, String parentId, List<Category> categoryList) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
        this.categoryList = categoryList;
    }
}
