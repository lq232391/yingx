package com.baizhi.serviceImpl;


import com.baizhi.annotcation.AddLog;
import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Video;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Resource
    HttpSession session;

    @Override
    public HashMap<String,Object> login(String username, String password, String code) {
        HashMap<String, Object> map = new HashMap<>();
        String imageCode = (String) session.getAttribute("imageCode");
        //判断验证码
        if (imageCode.equals(code)) {
            Admin admin = adminDao.login(username);
            if (admin != null) {
                if (password.equals(admin.getPassword())) {
                    session.setAttribute("admin", admin);
                    map.put("message", "登陆成功");
                    map.put("status", "200");
                } else {
                    map.put("message", "密码不对");
                    map.put("status", "201");
                }
            } else {
                map.put("message", "用户不存在");
                map.put("status", "201");
            }
        } else {
            map.put("message", "验证码不正确");
            map.put("status", "201");
        }
        return map;

    }
}
