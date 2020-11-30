package com.baizhi.serviceImpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.VideoDao;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;
import com.baizhi.po.VideoPo;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOSSUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;


import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private HttpServletRequest request;
    @Override
    @AddLog
    @Transactional(propagation =  Propagation.SUPPORTS)
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        VideoExample example = new VideoExample();
        int records = videoMapper.selectCountByExample(example);
        map.put("records", records);
        Integer total = records / rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Video> videos = videoMapper.selectByRowBounds(new Video(), rowBounds);
        map.put("rows", videos);

        return map;
    }

    @Override
    @AddLog
    public Long queryTotal() {
        return videoDao.queryTotal();
    }

    @Override
    @AddLog
    @DelCahe
    public String add(Video video) {
        String uuid=UUID.randomUUID().toString();
        video.setId(uuid);
        video.setUploadTime(new Date());
        video.setCategoryId("1");
        video.setUserId("1");
        videoMapper.insertSelective(video);
        return uuid;
    }
    @Override
    public void upload(MultipartFile photo, String id) {
      // 获取文件名
         String filename = photo.getOriginalFilename();
        System.out.println("Filename"+filename);
         //拼接时间撮
        String newName=new Date().getTime()+"-"+filename;
        //拼接视频文件夹
        String videoName="cover/"+newName;
        //上传视频到阿里云 参数一：MultipartFile文件的类型 参数二 存储空间名 参数三 文件名
        AliyunOSSUtil.uploadFileByte(photo,"yingxue-lq",videoName);
        //截取文件名
        String[] split=newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";
        //截取视频第一帧 参数一存储空间名 参数二 视频名 参数三 文件名
        AliyunOSSUtil.interceptVideoCover("yingxue-lq",videoName,coverName);
        //修改视频的信息
         Video video = new Video();
        video.setId(id);
        System.out.println("88888888888"+videoName);
        System.out.println("9999999999999"+coverName);
        video.setVideoPath("http://yingxue-lq.oss-cn-beijing.aliyuncs.com/"+videoName);
        video.setCoverPath("http://yingxue-lq.oss-cn-beijing.aliyuncs.com/"+coverName);
        videoMapper.updateByPrimaryKeySelective(video);
    }
    //上传到本地
    @Override
    public void headUpload(MultipartFile photo, String id) {
        //文件上传
        //1.根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/photo");
        //2.判断上传文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();//创建文件夹
        }
        //3.获取文件名
        String filename = photo.getOriginalFilename();
        //给文件拼接时间戳
        String newName = new Date().getTime() + "-" + filename;

        //4.文件上传
        try {
            photo.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //5.数据修改
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        Video video = new Video();
        video.setCoverPath(newName);
        videoDao.update(video);
    }

    @Override
    @DelCahe
    public void delete(Video video) {
        //先查一个
         Video video1 = videoMapper.selectByPrimaryKey(video);
        String videoPath = video1.getVideoPath().replace("http://yingxue-lq.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = video1.getCoverPath().replace("http://yingxue-lq.oss-cn-beijing.aliyuncs.com/", "");
        //删除视频
        AliyunOSSUtil.deleteFile("yingxue-lq",videoPath);
        AliyunOSSUtil.deleteFile("yingxue-lq",coverPath);
        //删除封面
        videoMapper.deleteByPrimaryKey(video);
    }

    @Override
    public List<VideoPo> queryByReleaseTime() {
        List<VideoPo> videoPos = videoMapper.queryByReleaseTime();
        for (VideoPo videoPo : videoPos) {
            String id = videoPo.getId();
            videoPo.setLikeCount(2000);
        }
        return videoPos;
    }

    @Override
    public List<Video> queryByLikeVideoName(String content) {
        return videoMapper.queryByLikeVideoName(content);
    }
}
