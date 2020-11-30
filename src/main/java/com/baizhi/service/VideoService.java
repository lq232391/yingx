package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoService {
    //分页查询
    public HashMap<String, Object> queryByPage(Integer page, Integer rows);
    //查询总条数
    Long queryTotal();
    //添加
    String add(Video video);
    //添加文件
    void upload(MultipartFile photo, String id);
    //上传到本地
    public void headUpload(MultipartFile photo, String id);
    //删除
    public void delete(Video video);
    //查所有视频
    List<VideoPo> queryByReleaseTime();
    //模糊查询
    List<Video> queryByLikeVideoName(String content);
}
