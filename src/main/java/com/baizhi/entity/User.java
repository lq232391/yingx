package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    @Excel(name = "id")
    private String id;
    @Excel(name = "名字")
    private String nickName;
    @Excel(name = "头像",type=2)
    private String picImg;
    @Excel(name = "电话")
    private String phone;
    private String brief;
    @Excel(name = "密码")
    private String score;
    @Excel(name = "注册时间",exportFormat=("yyyy-MM-dd"),importFormat = ("yyy-MM-dd"))
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @Excel(name = "状态")
    private String status;
    @Excel(name="性别")
   private String sex;

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", picImg='" + picImg + '\'' +
                ", phone='" + phone + '\'' +
                ", brief='" + brief + '\'' +
                ", score='" + score + '\'' +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(String id, String nickName, String picImg, String phone, String brief, String score, Date createDate, String status, String sex) {
        this.id = id;
        this.nickName = nickName;
        this.picImg = picImg;
        this.phone = phone;
        this.brief = brief;
        this.score = score;
        this.createDate = createDate;
        this.status = status;
        this.sex = sex;
    }
}
