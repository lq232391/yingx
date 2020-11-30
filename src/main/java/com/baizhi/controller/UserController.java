package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;

import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page,Integer rows){
         List<User> users = userService.queryAll(page, rows);
         Long count = userService.queryTotal();
         long totalcount=count%rows==0?count/rows:count/rows+1;
         HashMap<String, Object> map = new HashMap<>();
         map.put("page",page);
         map.put("total",totalcount);
         map.put("records",count);
         map.put("rows",users);
        System.out.println(map);
         return map;
    }
    @RequestMapping("updateStatus")
    public void updateStatus(String status,String id) {
        userService.updateStatus(status, id);
    }
    //发送验证码
    @RequestMapping("code")
    @ResponseBody
    public Map<String, Object> sendPhoneMsg(String phone) {
        System.out.println("输入手机" + phone);
        Map<String, Object> map = null;
        String message = null;
        try {
            String code = ImageCodeUtil.getSecurityCode();
            message = AliyunUtil.sendPhoneMsg(phone, code);
            System.out.println("验证码" + code);
            System.out.println("手机验证码状态" + message);
            map = new HashMap<>();
            map.put("status", 200);
            map.put("messgae", message);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 201);
            map.put("message", message);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("PoiExport")
        public Map<String,Object> PoiExport(){
        Map<String,Object> map = new HashMap<>();
        List<User> users = userService.selectAll();
        String message = null;
        ExportParams exportParams = new ExportParams("用户信息","用户登录信息");
        Workbook workbooks = ExcelExportUtil.exportBigExcel(exportParams, User.class, users);
            try {
                workbooks.write(new FileOutputStream(new File("D:\\UserExport.xls")));
                message = "success";
                map.put("message",message);
            } catch (IOException e) {
                e.printStackTrace();
                message = "error";
                map.put("message",message);
            }
            return map;
        }
    @RequestMapping("PoiImport")
    @ResponseBody
    public Map<String,Object> PoiImport(){
        Map<String,Object> map = new HashMap<>();
        ImportParams params = new ImportParams();
        params.setTitleRows(1);//设置标题所占行
        params.setHeadRows(1);//设置表头所占行
        List<User> users = null;
        String message = null;
        try {
            users = ExcelImportUtil.importExcel(new File("D:\\UserExport.xls"), User.class, params);
            message = "success";
            map.put("message",message);
        } catch (Exception e) {
            message = "error";
            map.put("message",message);
            e.printStackTrace();
        }
        return map;
    }
    }


