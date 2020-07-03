package com.chao.admin.toolsUtils;

import com.chao.admin.result.Result;
import com.chao.admin.result.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

/**
 * 操作文件工具类
 * @author 杨文超
 * @date 2020-06-30
 */
@Component
public class FileUtils {

    private static String path; //上传文件路径

    @Value("${filePath}")
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 图片上传
     * @param file
     * @author 杨文超
     * @date 2020-06-30
     */
    public static Result upload(MultipartFile file){
        if(file.isEmpty()){
            return new Result(ResultCode.businErrorCode.getCode(),"未上传头像");
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        File dest = new File(path+fileName+"."+"jpg");
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return new Result(ResultCode.successCode.getCode(),ResultCode.successCode.getMsg(),fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.businErrorCode.getCode(),"上传头像失败",e.getMessage());
        }
    }

    /**
     * 获取用户头像
     * @author 杨文超
     * @date 2020-06-30
     */
    public static void userImage(String fileId, HttpServletResponse response) throws Exception{
        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        try {
            File file = new File(path+fileId+".jpg");
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            response.setHeader("Content-Type", "image/jpeg");
            byte b[] = new byte[1024];
            int read;
            while ((read = bis.read(b)) != -1) {
                bos.write(b, 0, read);
            }
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }

}
