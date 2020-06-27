package com.chao.admin.login.service;

import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import org.springframework.http.HttpHeaders;

public interface LoginService {

    Result userLogin(User user, HttpHeaders headers);
}
