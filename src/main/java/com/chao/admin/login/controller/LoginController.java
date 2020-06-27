package com.chao.admin.login.controller;

import com.chao.admin.login.service.LoginService;
import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制
 * @author 杨文超
 * @date 2020-06-27
 */
@RestController
@Api(value = "LoginController",description = "登录控制")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param user
     * @param headers
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user,@RequestHeader HttpHeaders headers){

        Result result = loginService.userLogin(user, headers);
        return result;
    }
}
