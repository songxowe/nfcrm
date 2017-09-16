package com.sd.farmework.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sd.farmework.controller.RoleController;
import com.sd.farmework.pojo.ApplicationConfig;

/**
 * 短信服务类
 * 
 * @category -11-30
 * @author 王超超
 */
public class SendSMSYes {

	private static Logger logger = Logger.getLogger(RoleController.class);

	/**
	 * 短信服务类
	 * 
	 * @category -11-30
	 * @author 王超超
	 */
	public static boolean send(String mob, String msg, ApplicationConfig config) {
		logger.info("start into 短信发送：mob=" + mob + "\t msg=" + msg);
		String str = "";
		try {
			// 创建HttpClient实例
			HttpClient httpclient = new DefaultHttpClient();

			// 构造一个post对象
			HttpPost httpPost = new HttpPost(config.SENDMsgApiUrl);
			// 添加所需要的post内容
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("userCode", config.SDGMAccount));
			nvps.add(new BasicNameValuePair("userPass", config.SDGMPwd));
			nvps.add(new BasicNameValuePair("DesNo", mob));
			nvps.add(new BasicNameValuePair("Msg", msg));
			nvps.add(new BasicNameValuePair("Channel", config.SDGMChannel));// 根据文档去获取

			logger.info("短信发送入参集合nvps=" + nvps.toString());

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			logger.info("开始发送短信");
			HttpResponse response = httpclient.execute(httpPost);
			logger.info("发送短信结束");
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				str = convertStreamToString(instreams);
				System.out.println(str);
			}

			Document doc = null;
			doc = DocumentHelper.parseText(str); // 将字符串转为XML

			if (doc == null)
				return false;
			Element rootElt = doc.getRootElement(); // 获取根节点
			if (rootElt == null)
				return false;
			// System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			System.out.println("根节点的值：" + rootElt.getText()); // 拿到根节点的名称
			if (rootElt.getText() == null || "".equals(rootElt.getText()))
				return false;
			if (Long.parseLong(rootElt.getText()) > 0) {
				return true;
			} else {
				return false;
			}

		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
