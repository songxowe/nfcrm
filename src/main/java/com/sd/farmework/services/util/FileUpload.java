package com.sd.farmework.services.util;

 
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by shhao.
 * Date: 14-9-1
 * Time:涓嬪崍4:12
 */
public class FileUpload {

    public static final String FILE_PATH = "d:/upload/";

    //文件上传
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        File tempFile = new File(FILE_PATH, new Date().getTime() + String.valueOf(fileName));
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return "download.do?fileName=" + tempFile.getName();
    }
    
    public static File getFile(String fileName) {
        return new File(FILE_PATH, fileName);
    }
}
