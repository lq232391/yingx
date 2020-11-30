package com.baizhi.po;

public class VideoPo {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private String uploadTime;
    private String description;
    private Integer likeCount; //点赞数
    private String cateName;  //类别名
    private String userPhoto;  //用户头像

    public VideoPo() {
        super();
    }

    @Override
    public String toString() {
        return "VideoPo{" +
                "id='" + id + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", cover='" + cover + '\'' +
                ", path='" + path + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", description='" + description + '\'' +
                ", likeCount=" + likeCount +
                ", cateName='" + cateName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                '}';
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVideoTitle() {
        return videoTitle;
    }
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
    public String getCateName() {
        return cateName;
    }
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    public String getUserPhoto() {
        return userPhoto;
    }
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    public VideoPo(String id, String videoTitle, String cover, String path, String uploadTime, String description, Integer likeCount, String cateName, String userPhoto) {
        this.id = id;
        this.videoTitle = videoTitle;
        this.cover = cover;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.likeCount = likeCount;
        this.cateName = cateName;
        this.userPhoto = userPhoto;
    }
}
