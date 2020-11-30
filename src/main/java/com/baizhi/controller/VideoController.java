package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String,Object> queryBuPage(Integer page,Integer rows){
        HashMap<String, Object> map = videoService.queryByPage(page, rows);
        return map;
    }
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(Video video, String oper) {

        if (oper.equals("add")) {
            String id = videoService.add(video);
            return id;
        }
        if (oper.equals("edit")) {

        }
        if (oper.equals("del")) {
            videoService.delete(video);
        }

        return "";
    }
    @ResponseBody
    @RequestMapping("ipload")
    public void upload(MultipartFile photo,String id){
        System.out.println("filefile....................."+photo);
        videoService.upload(photo,id);
    }
}
