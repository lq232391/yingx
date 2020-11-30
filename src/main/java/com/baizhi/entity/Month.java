package com.baizhi.entity;

import java.util.Date;

public class Month {
    private String id;
    private Date createDate;
    private String sex;

    public Month() {
        super();
    }

    @Override
    public String toString() {
        return "Month{" +
                "id='" + id + '\'' +
                ", createDate=" + createDate +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Month(String id, Date createDate, String sex) {
        this.id = id;
        this.createDate = createDate;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
