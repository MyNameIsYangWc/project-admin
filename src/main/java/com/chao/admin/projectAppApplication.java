package com.chao.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
public class projectAppApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(5000);
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(projectAppApplication.class,args);
        System.out.println("app启动成功！！！");
    }
}
