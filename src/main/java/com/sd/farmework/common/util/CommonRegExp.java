package com.sd.farmework.common.util;

import java.util.regex.Pattern;

/**
 * 正则表达式类
 * @author uatxiangm
 * 2016-06-06
 */
public class CommonRegExp {
	
	//数字、中文、字母校验
	public static final String REGEXP_NUMBER_CHINESE_ALPHABET = "[0-9a-zA-Z\u4E00-\u9FA5]";

	/**
	 * 判断一个参数是否为空，去空格后是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return str == null || "".equals(str) || (str == null ? true : "".equals(str.trim()));
	}
	
	/**
	 * 正则表达式校验
	 * @param value
	 * @return
	 */
	public static boolean regExpData(String regExp, String value){
		return Pattern.compile(regExp).matcher(value).find();
	}
	
	public static void main(String[] args) {
			System.out.println(CommonRegExp.regExpData(REGEXP_NUMBER_CHINESE_ALPHABET, "3"));
}
}
