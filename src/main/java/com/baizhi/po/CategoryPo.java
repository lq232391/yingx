package com.baizhi.po;

import com.baizhi.entity.Category;

import java.util.List;

public class CategoryPo {
    private String id;
    private String cateName;
    private  String levels;
    private String parentId;
    private List<CategoryPo> categoryList;

    public CategoryPo() {
        super();
    }

    @Override
    public String toString() {
        return "CategoryPo{" +
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

    public List<CategoryPo> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryPo> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryPo(String id, String cateName, String levels, String parentId, List<CategoryPo> categoryList) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
        this.categoryList = categoryList;
    }
}

