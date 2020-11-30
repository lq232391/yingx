package com.baizhi.app;

import com.baizhi.common.CommonResult;

import com.baizhi.entity.Category;
import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    VideoService videoService;
   @Resource
    CategoryService categoryService;
    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone){
        String code = ImageCodeUtil.getSecurityCode();
        System.out.println("手机验证码: "+code);
        String message = null;
        try {
            message = AliyunUtil.sendPhoneMsg(phone, code);
            return new CommonResult().success(message,phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){
        try {
            List<VideoPo> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }
    @RequestMapping("queryAllCategory")
    public CommonResult queryByPage(){
        try {
     List<Category> categories = categoryService.queryAll();
        return new CommonResult().success(categories);
    } catch (Exception e){
            return  new CommonResult().failed();
        }
    }

    @RequestMapping("queryByLikeVideoName")
    public CommonResult queryByLikeVideoName(String content){
        try {
            List<Video> videos = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videos);
        } catch (Exception e){
            return  new CommonResult().failed();
        }
    }
}
