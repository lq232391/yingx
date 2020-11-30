package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "video")
public class Video implements Serializable {
    @Id
    private String id;

    private String title;

    private String brief;
    @Column(name = "cover_path")
    private String coverPath;
    @Column(name = "video_path")
    private String videoPath;
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "like_count")
    private String likeCount;
    @Column(name = "play_count")
    private String playCount;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "grout_id")
    private String groutId;

    private String status;

    public Video() {
    }

    public Video(String id, String title, String brief, String coverPath, String videoPath, Date uploadTime, String likeCount, String playCount, String categoryId, String userId, String groutId, String ststus) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.coverPath = coverPath;
        this.videoPath = videoPath;
        this.uploadTime = uploadTime;
        this.likeCount = likeCount;
        this.playCount = playCount;
        this.categoryId = categoryId;
        this.userId = userId;
        this.groutId = groutId;
        this.status = ststus;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", uploadTime=" + uploadTime +
                ", likeCount='" + likeCount + '\'' +
                ", playCount='" + playCount + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userId + '\'' +
                ", groutId='" + groutId + '\'' +
                ", ststus='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroutId() {
        return groutId;
    }

    public void setGroutId(String groutId) {
        this.groutId = groutId;
    }

    public String getStstus() {
        return status;
    }

    public void setStstus(String ststus) {
        this.status = ststus;
    }
}