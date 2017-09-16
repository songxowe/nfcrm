package com.sd.farmework.common;

import org.springframework.stereotype.Service;

/**
 * 响应状态，响应结果等数据
 * @author 
 * 2016-05-31
 */
@Service
public class Constant {
	
	public static final String RESPONSE_SUCCESS = "0000"; //响应成功状态
	public static final String RESPONSE_SUCCESS_MESSAGE = "成功"; //响应成功描述
	public static final String RESPONSE_DEFAULT_OBJECT = "{}"; //响应成功结果集
	public static final String RESPONSE_ERROR = "9999"; //程序异常响应状态
	public static final String RESPONSE_ERROR_MESSAGE = "程序异常";//程序异常描述
	public static final String RESPONSE_ISNULL = "0002"; //数据查询结果集为空状态
	public static final String RESPONSE_ISNULL_MESSAGE = "数据集为空";//数据查询结果集为空描述
	public static final String RESPONSE_UPDATE = "0003"; //数据查询结果集为空状态
	public static final String RESPONSE_UPDATE_MESSAGE = "数据更新无效";//数据查询结果集为空描述
	public static final String RESPONSE_PARAM = "0004"; //参数错误状态
	public static final String RESPONSE_PARAM_MESSAGE = "参数错误"; //参数错误描述
	public static final String RESPONSE_PRODUCT = "0005"; //卡产品信息校验错误状态码
	public static final String RESPONSE_PRODUCT_MESSAGE = "卡产品信息校验错误"; //卡产品信息校验错误描述
	public static final String RESPONSE_CARDNO = "0006"; //卡号信息校验错误状态码
	public static final String RESPONSE_CARDNO_MESSAGE = "卡号信息校验错误"; //卡号信息校验错误描述
	
	public static final String ONECARDINFO_ISFLAG = "0"; //数据是否有效0:有效，1：无效
	public static final String ONECARDINFO_ISTRANSMISSION = "2"; //是否传输卡厂1：已传输，2未传输
	public static final String ONECARDINFO_ISPERMITMAKECARD = "2"; //是否生产允许制卡文件1：已生成，2：未生成
	
	public static final String CREDITCARD_NO_ISLOCK_0 = "0"; //临时锁定状态
	public static final String CREDITCARD_NO_ISLOCK_1 = "1"; //锁定60天
	
	//=========核心接口相关状态
	public static final String RESPONSE_CORE_ERROR = "C999";
	public static final String RESPONSE_CORE_ERROR_MESSAGE = "调用核心接口错误";
	public static final String RESPONSE_CORE_FAIL = "C998";
	public static final String RESPONSE_CORE_FAIL_MESSAGE = "核心响应错误";
	
	
}
