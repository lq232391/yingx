package com.baizhi;

import com.baizhi.dao.CategoryDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.CategoryService;
import com.baizhi.service.UserService;
import com.sun.tools.internal.xjc.model.CValuePropertyInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

@SpringBootTest
class YingxLqApplicationTests {
@Autowired
private UserService userService;
@Autowired
private UserDao userDao;
@Autowired
private CategoryDao categoryDao;
@Autowired
private CategoryService categoryService;
@Autowired
private VideoMapper videoMapper;
    @Test
    void contextLoads() {
        List<Video> videos = videoMapper.selectAll();
        videos.forEach(video-> System.out.println(video));
    }
    @Test
    void queryAll(){
         List<User> users = userService.queryAll(1, 1);
        System.out.println(users);
    }
    @Test
    void queryCategory(){
         List<Category> categories = categoryDao.queryAllCategory(0,1);
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    @Test
    //查询所有二级类别
    void queryTwoCategory(){
        final List<Category> categories = categoryDao.queryTwoCategory(0, 1, "1");
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    //添加一级类别
    @Test
    void addOne(){
         Category category = new Category();
         category.setId("35");
         category.setCateName("小可爱");
         category.setLevels("2");
         category.setParentId("2");
         categoryDao.addOne(category);
    }
    @Test
    //查询二级类别总条数
    void queryTwoTotal(){
         Long aLong = categoryDao.queryTwoTotal("1");
        System.out.println(aLong);
    }
    @Test
    void updateCategory(){
         Category category = new Category();
         category.setCateName("软件网络");
         category.setId("1");
         categoryDao.updateOne(category);
    }
    //查旬所有一级类别
    @Test
    void queryALl(){
        final List<Category> categories = categoryDao.queryAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    //根据id查询
    @Test
    void queryByid(){
        final Category category = categoryService.queryById("1");
        System.out.println(category);
    }
    @Test
    void query(String sex){
     List<User> users = userDao.queryByCount("女");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
