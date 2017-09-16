package com.sd.farmework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 获取录音文件
 * @author xsj
 *
 */
public class GetTapes {
	
	public static boolean getRecord(String url, String fileName){
		
		boolean f = false;
        try {
    		// 创建HttpClient实例     
            HttpClient httpclient =new DefaultHttpClient();
    		
            HttpPost post = new HttpPost(url);
            
			HttpResponse response = httpclient.execute(post);
			
			HttpEntity entity = response.getEntity();
			
			if(entity!=null){
				InputStream content = entity.getContent();
				 f = createInputStreamString(content,fileName);
				 return f;
			}else{
				return f;
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
	
	public static boolean createInputStreamString(InputStream context, String fileName){
		boolean f = false;
		if(context==null){
			return f;
		}
		
		FileOutputStream out = null;
		try {
			File file = new File("e:/"+fileName+".WAV");
			 out = new FileOutputStream(file);
			 byte[] b = new byte[1024*4];
			 int a = 0;
			 while((a =context.read(b))!=-1){
				 out.write(b);
				 f= true;
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return f;
		
	}
	public static void main(String[] args) {
		
	//	boolean record = getRecord("http://www.helloweba.com/demo/html5audio/music.mp3");
		File file = new File("F:/薛之谦 - 意外.mp3");
		try {
			FileInputStream input = new FileInputStream(file);
			
			createInputStreamString(input,"薛之谦 - 意外");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//System.out.println(record);
	
	}
}
