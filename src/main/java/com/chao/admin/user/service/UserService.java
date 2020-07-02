package com.chao.admin.user.service;

import com.alibaba.fastjson.JSONObject;
import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import org.springframework.http.HttpHeaders;

public interface UserService {

    Result insertUser(User user, HttpHeaders headers);

    Result resetPwd(JSONObject user, HttpHeaders headers);
}
