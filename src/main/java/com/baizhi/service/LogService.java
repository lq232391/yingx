package com.baizhi.service;

import java.util.HashMap;

public interface LogService {
    //分页查询
    HashMap<String, Object> queryByPage(Integer page, Integer rows);
    //查询条数
    int queryTotal();
}
