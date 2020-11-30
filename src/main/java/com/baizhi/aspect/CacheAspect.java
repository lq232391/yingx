package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;

@Configuration
@Aspect
public class CacheAspect {
    @Autowired
    RedisTemplate redisTemplate;

    //添加缓存
    @Around("execution(* com.baizhi.serviceImpl.*.query*(..))")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("进入环绕通知");

        //解决redis乱码问题
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        StringBuilder stringBuilder = new StringBuilder();
        //获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
//        stringBuilder.append(name);
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        stringBuilder.append(methodName);
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            stringBuilder.append(arg);
        }
        String key = stringBuilder.toString();
        //判断key是否存在
        Boolean aBoolean = redisTemplate.hasKey(key);
        HashOperations opsForHash = redisTemplate.opsForHash();
        Object result = null;
        if (aBoolean) {
            //如果key存在
            result = opsForHash.get(className,key);
        } else {
            try {
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            opsForHash.put(className,key,result);
        }
        System.out.println("添加缓存成功"+result);
        return result;
    }

    @After("@annotation(com.baizhi.annotcation.DelCahe)")
    public void delCache(JoinPoint joinPoint) {
        System.out.println("后置通知++++");
        //获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();
        redisTemplate.delete(className);

    }
}
