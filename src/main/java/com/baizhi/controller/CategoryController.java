package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(Category category,String oper){
        System.out.println("当前什么操作"+oper);
        Map<String,Object> map=new HashMap<>();
        //判断是什么操作
        //添加一级类别
       if(StringUtils.equals("add",oper)){
           categoryService.addOne(category);
       }//修改一级类别
       if(StringUtils.equals("edit",oper)){
           categoryService.updateOne(category);
       }
       if(StringUtils.equals("del",oper)){
           String message= categoryService.deleteOne(category.getId());
           map.put("message",message);
            map.put("status",200);
       }
        System.out.println("删除信息回显"+map);
       return  map;
    }
    //查询所有一级类别
    @RequestMapping("queryAllcategory")
    @ResponseBody
    public Map<String, Object> queryAllCategory(Integer page, Integer rows){
         List<Category> categories = categoryService.queryAllCategory(page, rows);
         Long count = categoryService.queryTotal();
         long totalcount=count%rows==0?count/rows:count/rows+1;
         HashMap<String, Object> map = new HashMap<>();
         map.put("page",page);
         map.put("total",totalcount);
         map.put("records",count);
         map.put("rows",categories);
        return map;
    }
    //查询所有二级类别
    @RequestMapping("queryTwoCategory")
    @ResponseBody
    public Map<String,Object> queryTwoCategory(Integer page,Integer rows,String id){
         List<Category> categories = categoryService.queryTwoCategory(page, rows, id);
         Long count = categoryService.queryTwoTotal(id);
         long totalcount=count%rows==0?count/rows:count/rows+1;
         HashMap<String, Object> map = new HashMap<>();
         map.put("page",page);
         map.put("total",totalcount);
         map.put("records",count);
         map.put("rows",categories);
        return map;
    }
    @RequestMapping("queryAll")
    public void queryAll(HttpServletResponse response) throws IOException {
        //查询所有
         List<Category> categories = categoryService.queryAll();
         StringBuilder sb = new StringBuilder();
        sb.append("<select>");
        categories.forEach(aa->{
            sb.append("<option value="+aa.getId()+">"+ aa.getCateName()+"</opion>");
        });
        sb.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().println(sb.toString());
    }

}
