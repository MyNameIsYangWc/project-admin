package com.chao.admin.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCode;
import com.chao.admin.user.service.UserService;
import com.chao.admin.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心操作类
 * @author 杨文超
 * @date 2020-07-01
 */
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
     * 重置密码
     * user：账号，旧密码，新密码
     * @author 杨文超
     * @date 2020-07-01
     */
    @ApiOperation(value = "重置密码",notes = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user",value = "用户信息",required = true,dataType = "JSONObject",paramType = "body"),

            @ApiImplicitParam(name = "Authorization",value = "token",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping("/resetPwd")
    public Result resetPwd(@RequestHeader HttpHeaders headers, @RequestBody JSONObject user){
        if(StringUtils.isBlank(user.getString("username")) ||
                StringUtils.isBlank(user.getString("oldPassword")) || StringUtils.isBlank(user.getString("newPassword"))){
            return new Result(ResultCode.businErrorCode.getCode(),"参数错误");
        }
        return userService.resetPwd(user, headers);
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
