package com.chao.admin.user.service;

import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface UserService {

    Result insertUser(User user, HttpHeaders headers);

    Result userLogin(User user, HttpHeaders headers);

    Result modifyPwd(Map user, HttpHeaders headers);
}
