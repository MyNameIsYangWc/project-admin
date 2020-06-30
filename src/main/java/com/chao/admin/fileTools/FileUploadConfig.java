package com.chao.admin.fileTools;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {

    @Bean
    MultipartConfigElement multipartConfigElement(){

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //上传文件大小限制 10MB
        factory.setMaxFileSize(DataSize.ofBytes(1048576000));
        return factory.createMultipartConfig();
    }
}
