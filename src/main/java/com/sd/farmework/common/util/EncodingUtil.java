package com.sd.farmework.common.util;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 8/14/2013
 */
public class EncodingUtil {
	private static Log log = LogFactory.getLog(EncodingUtil.class);
	
	public static String gbk2utf8(String str) {
		String result = "";
		str = StringUtil.fixString(str);
		try {
			result = new String(str.getBytes("utf-8"), "gbk");
		} catch (UnsupportedEncodingException e) {
			log.error("encoding error.", e);
		}
		return result;
	}
	
	public static String utf82gbk(String str) {
		String result = "";
		str = StringUtil.fixString(str);
		try {
			result = new String(str.getBytes("gbk"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("encoding error.", e);
		}
		return result;
	}

}
