package com.chao.admin.login.controller;

import com.chao.admin.login.service.LoginService;
import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

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
     * @author 杨文超
     * @date 2020-06-27
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户信息",required = true,dataType = "User",paramType = "body"),

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user,@RequestHeader HttpHeaders headers){

        Result result = loginService.login(user, headers);
        return result;
    }

    /**
     * 退出
     * @param username
     * @param headers
     * @author 杨文超
     * @date 2020-06-29
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "账号",required = true,dataType = "String",paramType = "query"),

            @ApiImplicitParam(name = "Authorization",value = "token",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @GetMapping("/logout")
    public Result logout(@RequestParam String username,@RequestHeader HttpHeaders headers){

        return loginService.logout(username, headers);
    }
}
