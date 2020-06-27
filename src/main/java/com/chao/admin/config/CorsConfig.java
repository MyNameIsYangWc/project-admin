package com.chao.admin.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * @author 杨文超
 * @date 2020-06-27
 */
@Component
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")    // 允许跨域访问的路径
                .allowedOrigins("*")    // 允许跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")    // 允许请求方法
                .maxAge(168000)    // 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);    // 是否发送cookie
    }
}
