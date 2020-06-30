package com.chao.admin.login.common;

public interface BaseUrl extends BaseHost {

     //用户登录
     String USER_LOGIN=PROJECT_DOMAIN+"/login";
     //上传头像
     String UPLOAD=PROJECT_DOMAIN+"/attachment/upload";
     //用户注销
     String USER_LOGOUT=PROJECT_DOMAIN+"/userlogout?username={username}";

}
