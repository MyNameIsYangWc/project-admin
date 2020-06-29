package com.chao.admin.login.controller;

import com.chao.admin.login.service.LoginService;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCode;
import com.chao.admin.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

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
     * 用户头像上传
     * @param file
     * @author 杨文超
     * @date 2020-06-29
     */
    @ApiImplicitParams({

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @PostMapping(value = "/upload")
    public Result login(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return new Result(ResultCode.businErrorCode.getCode(),"未上传头像");
        }
        String fileName = UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split(".");
        System.out.println(split);
        String s = split[split.length];
        String path = "D:/test" ;
        File dest = new File(path + "/" + fileName+"."+s);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.businErrorCode.getCode(),"上传头像失败",e.getMessage());
        }
    }

    /**
     * 用户头像下载
     * @author 杨文超
     * @date 2020-06-29
     */
    @ApiImplicitParams({

            @ApiImplicitParam(name = "Accept",value = "",required = false,dataType = "String",paramType = "header",defaultValue = "application/json")
    })
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename="2.xlsx";
        String filePath = "D:/download";
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
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
