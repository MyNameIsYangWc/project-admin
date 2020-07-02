package com.chao.admin.interceptor;

import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常监控类
 * @author 杨文超
 * @date 2020-06-27
 */
@RestControllerAdvice
public class SystemExceptionMonitor{

    private Logger logger= LoggerFactory.getLogger(SystemExceptionMonitor.class);

    @Autowired
    HttpServletRequest httpServletRequest;

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        System.out.println("3");
//        model.addAttribute("Data", model);
    }

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     * @author 杨文超
     * @date 2020-06-27
     */
    @ExceptionHandler(Exception.class)
    public Result errorHandler(Exception e) {
        logger.error("系统异常，错误信息：",e);
        return new Result(ResultCode.SystemErrorCode.getCode(),ResultCode.SystemErrorCode.getMsg(),e.getMessage());
    }
}
