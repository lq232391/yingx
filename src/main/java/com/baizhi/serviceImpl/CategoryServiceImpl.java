package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired

    private CategoryDao categoryDao;
    //查询所有一级类别
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllCategory(Integer page, Integer rows) {
        int start=(page-1)*rows;
        int end=page*rows;
        return categoryDao.queryAllCategory(start,end);
    }
    //查询总条数
    @Override
    public Long queryTotal() {
        return categoryDao.queryTotal();
    }
    //查询所有二级类别
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryTwoCategory(Integer page, Integer rows, String id) {
        int start=(page-1)*rows;
        int end=page*rows;
        return categoryDao.queryTwoCategory(start,end,id);
    }

    //添加一级类别
    @Override
    @AddLog
    @DelCahe
    public void addOne(Category category) {
        category.setId(UUID.randomUUID().toString());
     if(category.getParentId()!=null){
         category.setLevels("2");
         //category.setParentId("1");
         categoryDao.addOne(category);
     }
     if(category.getParentId()==null){
         category.setLevels("1");
         category.setParentId(null);
         categoryDao.addOne(category);
     }
    }
    //查询二级类别总条数
    @Override
    public Long queryTwoTotal(String id) {
        return categoryDao.queryTwoTotal(id);
    }
    //修改一级类别
    @Override
    @AddLog
    @DelCahe
    public void updateOne(Category category) {
        categoryDao.updateOne(category);
    }

    @Override
    @AddLog("删除类别")
    @DelCahe
    //删除类别
    public String deleteOne(String id) {
        String message=null;
         Category category = categoryDao.queryById(id);
        System.out.println(category);
        if(category.getLevels().equals("1")){
            Long aLong = categoryDao.queryTwoTotal(category.getId());
            System.out.println(aLong);
            if(aLong==0){
                categoryDao.deleteOne(category.getId());
            }else{
                message="该类别上有一级类别不能删除";
                System.out.println(message);
            }
        }else{
            //二级类别上没有一级类别直接删除
            categoryDao.deleteOne(category.getId());
        }
        return  message;


    }

    @Override
    public List<Category> queryAll() {
        return categoryDao.queryAll();
    }
        //根据id查询
    @Override
    public Category queryById(String id) {
        return categoryDao.queryById(id);
    }

    @Override
    public List<Category> queryPageAll() {
        return categoryDao.queryPageAll();
    }
}
