package com.baizhi.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminService {
    public HashMap<String,Object> login(String username, String password, String code);
}
