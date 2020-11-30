package com.baizhi.service;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {
    public List<Category> queryAllCategory(Integer page, Integer rows);
    //查询总条数
    Long queryTotal();
    //查询所有二级类别
    public List<Category> queryTwoCategory(Integer page, Integer rows, String id);
    //添加一级类别
    public void addOne(Category category);
    //查询二级类别总条数
    Long queryTwoTotal(String id);
    //修改一级类别
    public void updateOne(Category category);
    //删除一级类别
    public String deleteOne(String id);
    //查询所有一级类别
    List<Category> queryAll();
    //根据id查询
    Category queryById(String id);
    //分类查询 前台
    List<Category>  queryPageAll();
}
