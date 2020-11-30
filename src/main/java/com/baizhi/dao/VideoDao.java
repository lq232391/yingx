package com.baizhi.dao;

import com.baizhi.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDao {
    //分页查询
    public List<Video>queryByPage(@Param("start")Integer start,@Param("rows")Integer rows);
    //查询总条数
    Long queryTotal();
    //添加
    void addVideo(Video video);
    //修改
    void update(Video video);
}
