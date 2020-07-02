package com.chao.admin.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * aop 切面
 */
@Aspect
@Component
public class aopa {

    @Pointcut("execution(* com.chao.admin.user.controller..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void before(JoinPoint point){
        System.out.println("123");
    }
}
