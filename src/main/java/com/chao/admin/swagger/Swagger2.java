package com.chao.admin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chao.admin.user"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("应用层用户中心服务API",
                "",
                "1.0",
                "",
                "杨文超",
                "",
                "");
    }

    @Bean
    public Docket logApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("login")
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chao.admin.login.controller"))
                .build()
                .apiInfo(logInfo());
    }

    private ApiInfo logInfo() {
        return new ApiInfo("应用层登录服务API",
                "",
                "1.0",
                "",
                "杨文超",
                "",
                "");
    }
}
