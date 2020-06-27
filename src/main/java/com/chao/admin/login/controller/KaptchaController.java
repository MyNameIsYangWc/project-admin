package com.chao.admin.login.controller;

import com.alibaba.fastjson.util.IOUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码控制器
 * @author 杨文超
 * @date 2020-06-27
 */
@RestController
@Api(value = "KaptchaController",description = "验证码控制器")
public class KaptchaController {

    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cacha-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text=producer.createText();
        //生成图片验证码
        BufferedImage image=producer.createImage(text);
        //保存验证码到session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.close(out);
    }
}
