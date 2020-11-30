package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
     List<User> queryAll(@Param("start") Integer start, @Param("rows") Integer rows);
     Long queryTotal();
     //修改用户状态
     void updateStatus(@Param("status") String status, @Param("id") String id);
     //查询在那个月份注册的
     List<User> queryByCount(String sex);
     //查所有
     List<User> selectAll();
}
