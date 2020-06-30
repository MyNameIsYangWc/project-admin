package com.chao.admin.login.controller;

import com.chao.admin.fileTools.FileUtils;
import com.chao.admin.login.service.LoginService;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCode;
import com.chao.admin.thread.MyThreadPoolExecutor;
import com.chao.admin.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private FileUtils fileUtils;

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
     * 用户头像上传
     * @param file
     * @author 杨文超
     * @date 2020-06-30
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "账号",required = true,dataType = "String",paramType = "query"),

            @ApiImplicitParam(name = "Authorization",value = "token",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping(value = "/upload")
    public Result upload(
            @RequestParam("file")MultipartFile file,
            @RequestParam String username,
            @RequestHeader HttpHeaders headers){
        Result upload = fileUtils.upload(file);
        //异步执行将fileId存入
        MyThreadPoolExecutor.getThreadPoolExecutor().submit(new Runnable() {
           @Override
           public void run() {
               if(ResultCode.successCode.getCode()==upload.getCode()){
                   // todo 此坑注意，上传文件的请求头需要修改，否则请求失败
                   headers.set("Content-type","application/json");
                   loginService.upload( upload.getData().toString(),username, headers);
               }
           }
        });
        return upload;
    }

    /**
     * 获取用户头像
     * @author 杨文超
     * @date 2020-06-30
     */
    @GetMapping("/userImage")
    public void userImage(String fileId, HttpServletResponse response) throws Exception {
        fileUtils.userImage(fileId,response);
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
