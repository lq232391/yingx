package com.baizhi.aspect;

import com.baizhi.annotcation.AddLog;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAspect {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private LogMapper logMapper;
    @Around("@annotation(com.baizhi.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        //获取用户操作数据
         Admin admin = (Admin) request.getSession().getAttribute("admin");
         //获取方法名
         String methodName = proceedingJoinPoint.getSignature().getName();
        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
         Method method = signature.getMethod();
         //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);
        //获取注解对应的属性值
         String value = addLog.value();
        String message=null;
        Object result =null;
        try {
            result = proceedingJoinPoint.proceed();
            System.out.println("放行结果"+result);
            message="success";
        } catch (Throwable throwable) {
            message="error";
        }
         Log log = new Log();
         log.setId(UUID.randomUUID().toString());
         log.setName(admin.getUsername());
         log.setTime(new Date());
         log.setOptions(methodName+"("+value+")");
         log.setStatus(message);
         logMapper.insert(log);

        System.out.println("数据入库"+log);
        return result;
    }
}
