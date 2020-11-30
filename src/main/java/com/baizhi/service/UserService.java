package com.baizhi.service;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //分页查询
    List<User> queryAll(Integer page, Integer rows);
    //查询总体哦啊书
    Long queryTotal();
    //修改状态
   void updateStatus(String status, String id);
  //查所有
    List<User>selectAll();
    //查询在那个月份注册的
    List<User> queryByCount(String sex);
}
