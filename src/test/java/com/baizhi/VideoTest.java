package com.baizhi;

import com.baizhi.dao.CategoryDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.VideoDao;
import com.baizhi.dao.VideoMapper;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.po.VideoPo;
import com.baizhi.service.UserService;
import com.baizhi.service.VideoService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VideoTest {
@Autowired
private VideoDao videoDao;
@Autowired
private VideoMapper videoMapper;
@Autowired
private VideoService videoService;
@Autowired
private CategoryDao categoryDao;
@Autowired
private UserDao userDao;
@Autowired
private UserService userService;
    @Test
    void contextLoads() {
        final List<VideoPo> videoPos = videoMapper.queryByReleaseTime();
        for (VideoPo videoPo : videoPos) {
            System.out.println(videoPo);
        }
    }
    @Test
    void queryPageAll(){
        final List<Category> categories = categoryDao.queryPageAll();
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    @Test
    void queryByCOunbt(String sex){
        final List<User> users = userDao.queryByCount("男");
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    void aaa(){
        final Long users = userDao.queryTotal();
        System.out.println(users);
    }

}
