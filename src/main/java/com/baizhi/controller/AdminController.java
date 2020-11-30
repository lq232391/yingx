package com.baizhi.controller;

import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    // 生成验证码
    @RequestMapping("getImageCode")
    public String crefatImg(HttpServletResponse response,HttpServletRequest request) throws IOException {
        // 1 获取验证码随机字符
        String imageCode = ImageCodeUtil.getSecurityCode();
        System.out.println("验证码:" + imageCode);
        BufferedImage bufferedImage = ImageCodeUtil.createImage(imageCode);
        ServletOutputStream out =response.getOutputStream();
        // 2 使用响应输出流 把bufferedImage 输出到 client
        ImageIO.write(bufferedImage, "gif", out);
        // 3 把随机验证码，存入session作用域
        HttpSession session = request.getSession(true);
        session.setAttribute("imageCode", imageCode);
        return null;
    }
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(String username, String password,String code) {
        return  adminService.login(username, password, code);
    }
    @RequestMapping("exit")
    public String exit(HttpServletRequest request){
           request.getSession().removeAttribute("login");
        return "redirect:/login/login.jsp";
    }
}
