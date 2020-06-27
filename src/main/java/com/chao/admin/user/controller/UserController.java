package com.chao.admin.user.controller;

import com.chao.admin.result.Result;
import com.chao.admin.user.service.UserService;
import com.chao.admin.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "UserController",description = "用户中心")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户",notes = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户信息",required = true,dataType = "User",paramType = "body"),

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping("/insertUser")
    public Result insertUser(@RequestHeader HttpHeaders headers, @RequestBody User user){

        Result result = userService.insertUser(user, headers);
        return result;
    }



    /**
     * 修改密码
     * user：账号，旧密码，新密码
     */
    @ApiOperation(value = "修改密码",notes = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户信息",required = true,dataType = "map",paramType = "body"),

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping("/modifyPwd")
    public Result modifyPwd(@RequestHeader HttpHeaders headers, @RequestBody Map user){

        Result result = userService.modifyPwd(user, headers);
        return result;
    }

    /**
     * 忘记密码
     */
    @ApiOperation(value = "忘记密码",notes = "忘记密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户信息",required = true,dataType = "User",paramType = "body"),

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping("/forgetPwd")
    public Result forgetPwd(){

        return null;
    }

}
