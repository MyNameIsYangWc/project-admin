package com.chao.admin.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
    @Before("pointcut()")
    public void before(JoinPoint point){
        HttpServletRequest request=
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("被请求URL:{} || {}请求",request.getRequestURL(),request.getMethod());
    }
}