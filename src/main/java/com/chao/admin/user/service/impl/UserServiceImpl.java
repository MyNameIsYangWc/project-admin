package com.chao.admin.user.service.impl;

import com.chao.admin.restTemplate.CommonRestTemplate;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCodeMonitor;
import com.chao.admin.user.common.BaseUrl;
import com.chao.admin.user.service.UserService;
import com.chao.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CommonRestTemplate commonRestTemplate;

    /**
     * 新增用户
     */
    @Override
    public Result insertUser(User user, HttpHeaders headers) {

        Result result = commonRestTemplate.post(BaseUrl.INSERT_USER, user, headers);
        ResultCodeMonitor.handler(result,"新增用户");

        return result;
    }

    /**
     * 用户登录
     */
    @Override
    public Result userLogin(User user, HttpHeaders headers) {

        Result result = commonRestTemplate.post(BaseUrl.USER_LOGIN, user, headers);
        ResultCodeMonitor.handler(result,"用户登录");
        return result;
    }

    /**
     * 修改密码
     */
    @Override
    public Result modifyPwd(Map user, HttpHeaders headers) {

        return null;
    }
}
