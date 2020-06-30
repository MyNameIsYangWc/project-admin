package com.chao.admin.login.service.impl;

import com.chao.admin.common.Constants;
import com.chao.admin.login.service.LoginService;
import com.chao.admin.restTemplate.CommonRestTemplate;
import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCodeMonitor;
import com.chao.admin.vo.Attachment;
import com.chao.admin.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import static com.chao.admin.login.common.BaseUrl.*;

@Service
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonRestTemplate commonRestTemplate;

    /**
     * 登录
     * @param user
     * @param headers
     * @return
     */
    @Override
    public Result login(User user, HttpHeaders headers) {

        Result result = commonRestTemplate.post(USER_LOGIN, user, headers);
        ResultCodeMonitor.handler(result,"登录");
        return result;
    }

    /**
     * 退出
     * @param username
     * @param headers
     * @author 杨文超
     * @date 2020-06-29
     */
    @Override
    public Result logout(String username, HttpHeaders headers) {

        Result result = commonRestTemplate.get(USER_LOGOUT,headers,username);
        ResultCodeMonitor.handler(result,"退出");
        return result;
    }

    /**
     * 用户头像上传
     * @param fileId
     * @author 杨文超
     * @date 2020-06-30
     */
    @Override
    public void upload(String fileId,String username, HttpHeaders headers) {
        //封装附件信息
        Attachment attachment = new Attachment();
        attachment.setFileId(fileId);
        attachment.setFileName(username);
        attachment.setUsername(username);
        attachment.setFileTypeCd(Constants.FILE_TYPE_CD_ONE);
        attachment.setFileTypeName(Constants.FILE_TYPE_NAME_ONE);
        Result result = commonRestTemplate.post(UPLOAD,attachment,headers);
        ResultCodeMonitor.handler(result,"用户头像上传");
        //todo 上传异常 使用websocket通知用户
    }
}
