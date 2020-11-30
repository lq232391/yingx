package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Autowired
    private HttpServletRequest request;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryAll(Integer page,Integer rows){
        int start=(page-1)*rows;
        int end=page*rows;
        return userDao.queryAll(start,end);
    }

    @Override
    public Long queryTotal() {
        return userDao.queryTotal();
    }

    @AddLog("修改用户状态")
    @Override
    @DelCahe
    public void updateStatus(String status, String id) {
        userDao.updateStatus(status, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectAll() {
         List<User> users = userDao.selectAll();
        for (User user : users) {
             String realPath = request.getSession().getServletContext().getRealPath(user.getPicImg());
            user.setPicImg(realPath);
        }
        return userDao.selectAll();
    }

    @Override
    public List<User> queryByCount(String sex) {
        return userDao.queryByCount(sex);
    }
}
