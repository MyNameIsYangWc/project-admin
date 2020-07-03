package com.chao.admin.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 上传文件大小限制
 * @author 杨文超
 * @date 2020-06-30
 */
@Configuration
public class FileUploadConfig {

    @Bean
    MultipartConfigElement multipartConfigElement(){

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //上传文件大小限制 10MB
        factory.setMaxFileSize(DataSize.ofBytes(10485760));
        return factory.createMultipartConfig();
    }
}
