package com.sd.farmework.common;

import java.io.Serializable;

/**
 * 数据请求响应对象，接口数据响应状态及接口响应结果集包装
 * 2016-08-14
 * xiangmeng
 */
public class ResultModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String code;	//响应状态
	private String message;	//响应消息
	private Object result;	//响应数据
	
	public ResultModel(){
		 
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
