package com.chao.admin.restTemplate;

import com.alibaba.fastjson.JSONObject;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommonRestTemplate {

    private Logger logger= LoggerFactory.getLogger(CommonRestTemplate.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求
     * @param url
     * @param headers
     * @param uriVariables
     */
    @HystrixCommand(fallbackMethod = "getBack")
    public Result get(String url, HttpHeaders headers, Object...uriVariables){

        logger.info("GET请求开始:");
        logger.info("url:"+url);
        logger.info("uriVariables"+uriVariables);

        long start=System.currentTimeMillis();
        HttpEntity<Object> entity = new HttpEntity<Object>(null,headers);

        ResponseEntity<Result> exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<Result>(){},uriVariables);

        long end=System.currentTimeMillis();
        logger.info(url+"接口请求结束￥时长："+(end-start));
        return exchange.getBody();
    }

    /**
     * post请求
     * @param url
     * @param data
     * @param headers
     * @param uriVariables
     * @return
     */
    @HystrixCommand(fallbackMethod = "postBack")
    public Result post(String url, Object data, HttpHeaders headers, Object...uriVariables){

        logger.info("POST请求开始:");
        logger.info("url:"+url);
        logger.info("Data:"+ JSONObject.toJSONString(data));
        logger.info("uriVariables"+uriVariables);

        long start=System.currentTimeMillis();
        HttpEntity<Object> entity = new HttpEntity<Object>(data,headers);

        ResponseEntity<Result> exchange = restTemplate.exchange(url, HttpMethod.POST, entity,
                new ParameterizedTypeReference<Result>(){},uriVariables);

        long end=System.currentTimeMillis();
        logger.info(url+"接口请求结束￥时长："+(end-start));

        return exchange.getBody();
    }

    //post请求降级方法
    public Result postBack(String url, Object data, HttpHeaders headers, Object...uriVariables){

        logger.warn("POST请求结束:"+ResultCode.SystemTimeOutCode.getMsg());
        return new Result(ResultCode.SystemTimeOutCode.getCode(),ResultCode.SystemTimeOutCode.getMsg());
    }

    //get请求降级方法
    public Result getBack(String url, HttpHeaders headers, Object...uriVariables){

        logger.warn("GET请求结束:"+ResultCode.SystemTimeOutCode.getMsg());
        return new Result(ResultCode.SystemTimeOutCode.getCode(),ResultCode.SystemTimeOutCode.getMsg());
    }
}
