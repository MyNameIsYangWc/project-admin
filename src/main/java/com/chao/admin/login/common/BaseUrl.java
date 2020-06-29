package com.chao.admin.login.common;

public interface BaseUrl extends BaseHost {

     //用户注册
     String INSERT_USER=PROJECT_DOMAIN+"/user/insertUser";

     //用户登录
     String USER_LOGIN=PROJECT_DOMAIN+"/login";
     //用户注销
     String USER_LOGOUT=PROJECT_DOMAIN+"/userlogout?username={username}";

}
