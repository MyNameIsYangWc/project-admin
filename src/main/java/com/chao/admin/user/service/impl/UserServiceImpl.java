package com.chao.admin.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chao.admin.restTemplate.CommonRestTemplate;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCodeMonitor;
import com.chao.admin.user.service.UserService;
import com.chao.admin.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static com.chao.admin.user.common.BaseUrl.INSERT_USER;
import static com.chao.admin.user.common.BaseUrl.RESET_PWD;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CommonRestTemplate commonRestTemplate;

    /**
     * 新增用户
     */
    @Override
    public Result insertUser(User user, HttpHeaders headers) {

        Result result = commonRestTemplate.post(INSERT_USER, user, headers);
        ResultCodeMonitor.handler(result,"新增用户");
        return result;
    }

    /**
     * 重置密码
     * user：账号，旧密码，新密码
     * @author 杨文超
     * @date 2020-07-01
     */
    @Override
    public Result resetPwd(JSONObject user, HttpHeaders headers) {

        Result result = commonRestTemplate.post(RESET_PWD, user, headers);
        ResultCodeMonitor.handler(result,"重置密码");
        return result;
    }
}
