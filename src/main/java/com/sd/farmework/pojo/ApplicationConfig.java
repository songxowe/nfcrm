package com.sd.farmework.pojo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.sql.*;
import java.io.PrintStream;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.*;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sd.farmework.controller.RoleController;

/**
 * 系统配置
 * @category 2016-11-30
 * @author 王超超 
 */
public class ApplicationConfig {
	
	/*
	 * 短信服务配置
	 */
	public String SENDMsgApiUrl;//api地址
	public String SDGMAccount;//账号
	public String SDGMPwd;//密码
	public String SDGMSupplier;
	public String SDGMMsg;
	public String SDGMSendMsg;
	public String SDGMChannel;//通道号





	public String getSENDMsgApiUrl() {
		return SENDMsgApiUrl;
	}
	public void setSENDMsgApiUrl(String sENDMsgApiUrl) {
		SENDMsgApiUrl = sENDMsgApiUrl;
	}
	public String getSDGMAccount() {
		return SDGMAccount;
	}
	public void setSDGMAccount(String sDGMAccount) {
		SDGMAccount = sDGMAccount;
	}
	public String getSDGMPwd() {
		return SDGMPwd;
	}
	public void setSDGMPwd(String sDGMPwd) {
		SDGMPwd = sDGMPwd;
	}
	public String getSDGMSupplier() {
		return SDGMSupplier;
	}
	public void setSDGMSupplier(String sDGMSupplier) {
		SDGMSupplier = sDGMSupplier;
	}
	public String getSDGMMsg() {
		return SDGMMsg;
	}
	public void setSDGMMsg(String sDGMMsg) {
		SDGMMsg = sDGMMsg;
	}
	public String getSDGMSendMsg() {
		return SDGMSendMsg;
	}
	public void setSDGMSendMsg(String sDGMSendMsg) {
		SDGMSendMsg = sDGMSendMsg;
	}
	public String getSDGMChannel() {
		return SDGMChannel;
	}
	public void setSDGMChannel(String sDGMChannel) {
		SDGMChannel = sDGMChannel;
	}

	
}
