package com.baizhi.controller;

import com.baizhi.entity.Video;
import com.baizhi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {
    @Autowired
    private LogService logService;
    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String,Object> queryBuPage(Integer page, Integer rows){
        HashMap<String, Object> map = logService.queryByPage(page, rows);
        return map;
    }
}
