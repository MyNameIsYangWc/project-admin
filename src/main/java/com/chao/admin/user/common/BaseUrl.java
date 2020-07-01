package com.chao.admin.user.common;

public interface BaseUrl extends BaseHost{

     //用户注册
     String INSERT_USER=PROJECT_DOMAIN+"/user/insertUser";

     //重置密码
     String RESET_PWD=PROJECT_DOMAIN+"/user/resetPwd";

}
