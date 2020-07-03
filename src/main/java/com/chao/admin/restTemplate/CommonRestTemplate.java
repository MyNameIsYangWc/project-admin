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

import static com.chao.admin.toolsUtils.DateUtils.getCurrentTimeLong;

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

        logger.info(String.format("远程服务URL：%s || GET请求",url));
        logger.info(String.format("uriVariables参数：%s",uriVariables));

        long start=getCurrentTimeLong();
        HttpEntity<Object> entity = new HttpEntity<Object>(null,headers);
        ResponseEntity<Result> exchange = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<Result>(){},uriVariables);

        logger.info(url+"接口请求结束￥时长："+(getCurrentTimeLong()-start));
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

        logger.info(String.format("远程服务URL：%s || POST请求",url));
        logger.info(String.format("entity参数：%s,uriVariables参数：%s",JSONObject.toJSONString(data),uriVariables));

        long start = getCurrentTimeLong();
        HttpEntity<Object> entity = new HttpEntity<Object>(data,headers);
        ResponseEntity<Result> exchange = restTemplate.exchange(url, HttpMethod.POST, entity,
                new ParameterizedTypeReference<Result>(){},uriVariables);

        logger.info(url+"接口请求结束￥时长："+(getCurrentTimeLong()-start));
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
