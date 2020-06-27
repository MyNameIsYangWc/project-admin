package com.chao.admin.login.service.impl;

import com.chao.admin.login.service.LoginService;
import com.chao.admin.restTemplate.CommonRestTemplate;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCodeMonitor;
import com.chao.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static com.chao.admin.login.common.BaseUrl.USER_LOGIN;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CommonRestTemplate commonRestTemplate;

    /**
     * 登录
     * @param user
     * @param headers
     * @return
     */
    @Override
    public Result userLogin(User user, HttpHeaders headers) {

        Result result = commonRestTemplate.post(USER_LOGIN, user, headers);
        ResultCodeMonitor.handler(result,"登录");
        return result;
    }
}
