package com.chao.admin.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop 切面拦截
 * @author 杨文超
 * @date 2020-07-02
 */
@Aspect
@Component
public class AopInterceptor {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 方法拦截
     * @author 杨文超
     * @date 2020-07-02
     */
    @Pointcut("execution(* com.chao.admin.*.controller..*.*(..))")
    public void pointcut(){}


    /**
     * 方法拦截，日志输出
     * @author 杨文超
     * @date 2020-07-03
     */
    @Around("pointcut()")
    public void before(ProceedingJoinPoint point){
        HttpServletRequest request=
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(String.format("被请求URL:%s || %s请求",request.getRequestURL(),request.getMethod()));
    }
}