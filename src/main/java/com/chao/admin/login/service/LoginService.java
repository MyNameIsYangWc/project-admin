package com.chao.admin.login.service;

import com.chao.admin.result.Result;
import com.chao.admin.vo.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<Result> login(User user, HttpHeaders headers);

    Result logout(String username, HttpHeaders headers);

    void upload(String fileId,String username, HttpHeaders headers);

    Result registerUser(User user, HttpHeaders headers);
}
