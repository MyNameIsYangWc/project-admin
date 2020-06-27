package com.chao.admin.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求返回码监控
 */
public class ResultCodeMonitor {

    private static Logger logger= LoggerFactory.getLogger(ResultCodeMonitor.class);

    public static void handler(Result result,String msg){

        if(result != null){
                //200成功
            if (result.getCode()==ResultCode.successCode.getCode()){
                logger.info(msg+"-"+ResultCode.successCode.getMsg());
               //201业务异常
            }else if(result.getCode()==ResultCode.businErrorCode.getCode()){
                logger.warn(msg+"-"+ResultCode.businErrorCode.getMsg());
                //202系统异常
            }else if(result.getCode()==ResultCode.SystemErrorCode.getCode()){
                logger.warn(msg+"-"+ResultCode.SystemErrorCode.getMsg());
                //203超时
            }else if(result.getCode()==ResultCode.SystemTimeOutCode.getCode()){
                logger.warn(msg+"-"+ResultCode.SystemTimeOutCode.getMsg());
            }
        }
        logger.warn(msg+"-"+ResultCode.SystemErrorCode.getMsg());
    }
}
