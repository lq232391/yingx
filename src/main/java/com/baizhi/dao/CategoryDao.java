package com.baizhi.dao;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;
import sun.awt.geom.AreaOp;

import java.util.List;

public interface CategoryDao {
    //分页查询
    public List<Category> queryAllCategory(@Param("start") Integer start, @Param("rows") Integer rows);
    //查询总条数
    Long queryTotal();
    //查询二级类别
    public List<Category> queryTwoCategory(@Param("start") Integer start, @Param("rows") Integer rows, @Param("id") String id);
    //添加一级类别
    public void addOne(Category category);
    //查询二级类别的总条数
    Long queryTwoTotal(@Param("id") String id);
    //修改一级类别
    public void updateOne(Category category);
    //删除一级类别
    void deleteOne(String id);
    //查村所有一级类别
    List<Category> queryAll();
    //根据id查询
    Category queryById(@Param("id") String id);

    //分类查询 前台
     List<Category>  queryPageAll();
}
