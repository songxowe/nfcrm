package com.sd.farmework.controller;

 
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sd.farmework.services.util.FileUpload;

/**
 * Created by shhao.
 * Date: 14-9-1
 * Time:下午4:32
 * 处理文件上传下载
 */
@Controller
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("upload")
    public void upload( HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    	logger.info("1111111111111111111111111111111111111test");
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
    	Map<String, MultipartFile> map=   multipartRequest.getFileMap();
    	
    	Iterator<String> it= map.keySet().iterator();
     	if (it.hasNext()) {
    		String str=it.next();
    		
    		String filePath = FileUpload.uploadFile(map.get(str), request);
    		logger.info("filePath:" + filePath);
    		response.setContentType("text/html;charset=utf8");
    		response.getWriter().write(filePath);
		}
    	
    }

    @RequestMapping("download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("image/jpeg; charset=utf-8");
            os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
