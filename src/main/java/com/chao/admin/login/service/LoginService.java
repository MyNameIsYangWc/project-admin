package com.chao.admin.login.service;

import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import org.springframework.http.HttpHeaders;

public interface LoginService {

    Result login(User user, HttpHeaders headers);

    Result logout(String username, HttpHeaders headers);
}
