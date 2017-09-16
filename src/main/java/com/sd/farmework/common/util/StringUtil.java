package com.sd.farmework.common.util;

import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * String utility class.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Dec 5, 2012
 */
public class StringUtil {
	private static Log log = LogFactory.getLog(StringUtil.class);
	public static final String SEPARATOR = ",";


	/**
	 * Fix the length for string. If the length of string is less than appointed
	 * length, default value "0" will be added in the front of this String.
	 * 
	 * @param str
	 *            Original string
	 * @param length
	 *            The length you want to fix the string
	 * @return Result string
	 */
	public static String prefixString(String str, int length) {
		return prefixString(str, length, "0");
	}
	/**
	 * Fix the length for string. If the length of string is less than appointed
	 * length, prefixStr will be added in the front of this String.
	 * 
	 * @param str
	 *            Original string
	 * @param length
	 *            The length you want to fix the string
	 * @param prefixStr
	 *            Indicate which string will be added in the front of string
	 * @return Result string
	 */
	public static String prefixString(String str, int length, String prefixStr) {
		if (str == null) {
			return str;
		}

		if (str.length() >= length) {
			return str;
		}

		StringBuilder sb = new StringBuilder(str);
		for (int i = str.length(); i < length; i++) {
			sb.insert(0, prefixStr);
		}
		str = sb.toString();

		return str;
	}
	
	/**
	 * @param str
	 * @param length
	 * @return
	 */
	public static String suffixString(String str, int length) {
		return suffixString(str, length, "0");
	}

	/**
	 * @param str
	 * @param length
	 * @param suffixString
	 * @return
	 */
	public static String suffixString(String str, int length, String suffixString) {
		if (str == null) {
			return str;
		}

		if (str.length() >= length) {
			return str;
		}

		for (int i = str.length(); i < length; i++) {
			str = str + suffixString;
		}

		return str;
	}

	/**
	 * Convert string to int
	 * 
	 * @param str
	 * @return
	 */
	public final static int getInt(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			log.warn("", ex);
		}
		return result;
	}

	/**
	 * Convert string to long
	 * 
	 * @param str
	 * @return
	 */
	public final static long getLong(String str) {
		long result = 0;
		try {
			result = Long.parseLong(str);
		} catch (NumberFormatException ex) {
			log.warn("", ex);
		}
		return result;
	}

	/**
	 * Format file size to readable style
	 * @param size
	 * @return
	 */
	public final static String getReadableSize(long size) {
		BigDecimal bd = new BigDecimal(size);

		String result = "";
		if (size >= 0 && size < 1024) {
			result = size + "B";
		} else if (size >= 1024 && size < (1024 * 1024)) {
			result = getBigDecimalStr(bd.divide(new BigDecimal(1024)), 1) + "K";
		} else if (size >= (1024 * 1024) && size < (1024 * 1024 * 1024)) {
			result = getBigDecimalStr(bd.divide(new BigDecimal(1024 * 1024)), 1) + "M";
		} else if (size >= (1024 * 1024 * 1024)) {
			result = getBigDecimalStr(bd.divide(new BigDecimal(1024 * 1024 * 1024)), 1) + "G";
		}
		return result;
	}
	
	/**
	 * @param bd
	 * @param scale
	 * @return
	 */
	public final static String getBigDecimalStr(BigDecimal bd, int scale) {
		bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return truncateZeroPostfix(bd.toString());
	}
	
	/**
	 * Truncate unnecessary zero
	 * @param str
	 * @return
	 */
	private final static String truncateZeroPostfix(String str) {
		 String[] strs = str.split("\\.?0+$");
         return strs[0];
	}

	/**
	 * Conver initial letter of specified string to upper case
	 * 
	 * @param item
	 *            Original string
	 * @return Result string
	 */
	public static String getInitialUpperCase(String item) {
		String returnItem = Character.toUpperCase(item.charAt(0)) + item.substring(1);
		return returnItem;
	}

	/**
	 * Conver initial letter of specified string to lower case
	 * 
	 * @param item
	 *            Original string
	 * @return Result string
	 */
	public static String getInitialLowerCase(String item) {
		String returnItem = Character.toLowerCase(item.charAt(0)) + item.substring(1);
		return returnItem;
	}

	/**
	 * Convert string to the class name format, initial letter is upper case.
	 * The separater char between words is "_". Such as: USER_NAME convert to
	 * UserName
	 * 
	 * @param item
	 *            Original string
	 * @return Result string
	 */
	public static String getUpperVarName(String item) {
		StringBuffer returnBuf = new StringBuffer();

		String[] itemArr = item.split("_");
		for (int i = 0; i < itemArr.length; i++) {
			returnBuf.append(getInitialUpperCase(itemArr[i].toLowerCase()));
		}

		return returnBuf.toString();
	}

	/**
	 * Convert string to the class name format, initial letter is lower case.
	 * The separater char between words is "_". Such as: USER_NAME convert to
	 * UserName
	 * 
	 * @param item
	 *            Original string
	 * @return Result string
	 */
	public static String getLowerVarName(String item) {
		String returnItem = getUpperVarName(item);
		returnItem = getInitialLowerCase(returnItem);

		return returnItem;
	}

	/**
	 * Return getter method name for specified property name
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getGetterMethodName(String fieldName) {
		return "get" + getInitialUpperCase(fieldName);
	}

	/**
	 * Return setter method name for specified property name
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String getSetterMethodName(String fieldName) {
		return "set" + getInitialUpperCase(fieldName);
	}

	/**
	 * @param str
	 * @return
	 */
	public static List<String> getStringList(String str) {
		return getStringList(str, SEPARATOR);
	}

	/**
	 * @param str
	 * @param separater
	 * @return
	 */
	public static List<String> getStringList(String str, String separater) {
		String[] items = str.split(separater);
		List<String> result = Arrays.asList(items);
		return result;
	}

	/**
	 * Extract only calss name from specified full class name.
	 * 
	 * @param fullClassName
	 *            Including package
	 * @return String only class name
	 */
	public static String getClassSimpleName(String fullClassName) {
		return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
	}
	 
	
	 
 
	 
	 
	
	/**
	 * Check if string is int
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		boolean result = true;
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	/**
	 * Check if src exists in dest. Separator use default comma.
	 * @param src
	 * @param dest
	 * @return
	 */
	public static boolean isExisted(String src, String dest) {
		return isExisted(src, dest, ",");
	}
	
	/**
	 * Check if src exists in dest. Separator is specified.
	 * @param src
	 * @param dest
	 * @param separator
	 * @return
	 */
	public static boolean isExisted(String src, String dest, String separator) {
		boolean result = false;
		if (src != null && dest != null) {
			StringTokenizer tokennizer = new StringTokenizer(dest, separator);
			while (tokennizer.hasMoreTokens()) {
				if (tokennizer.nextToken().equals(src)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Convert string to int
	 * @param str
	 * @return
	 */
	public static int toInt(String str) {
		int result = 0;
		if(isInt(str)) {
			result = Integer.parseInt(str);
		}
		return result;
	}
	
	/**
	 * Convert package string to path string, replace dot to solidus
	 * 
	 * @param pkg
	 *            Jave package string
	 * @return Result path string
	 */
	public static String toPathFromPkg(String pkg) {
		return fixPathWithoutLastSlash(pkg.replaceAll("\\.", "/"));
	}
	
	/**
	 * Truncate string to specified length.
	 * 
	 * @param str
	 * @param length
	 * @return Truncated string
	 */
	public final static String truncateString(String str, int length) {
		if (str != null && str.length() > length) {
			str = str.substring(0, length);
		}
		return str;
	}
	
	 
	
	/**
	 * Prevent SQL injection
	 * @param string
	 * @return
	 */
	public final static String fixSqlString(String string) {
		return fixSqlString(string, "");
	}
	
	/**
	 * Prevent SQL injection
	 * @param string
	 * @param defaultString
	 * @return
	 */
	public final static String fixSqlString(Object obj, String defaultString) {
		String string = obj == null ? defaultString : obj.toString();
		return fixSqlString(string);
	}
	
	/**
	 * Prevent SQL injection
	 * @param string
	 * @return
	 */
	public final static String fixSqlString(Object obj) {
		return fixSqlString(obj, "");
	}
	
	 
	
	/**
	 * Return <code>string</code>, or <code>""</code> if <code>string</code> is
	 * <code>null</code>. Never returns <code>null</code>.
	 * <p>
	 * Examples:
	 * </p>
	 * 
	 * <pre>
	 * // prints 0
	 * String s = null;
	 * System.out.println(TextUtils.noNull(s).length());
	 * 
	 * // prints 1
	 * s = &quot;a&quot;;
	 * System.out.println(TextUtils.noNull(s).length());
	 * </pre>
	 * 
	 * @param string
	 *            the String to check
	 * @return a valid (non-null) string reference
	 */
	public final static String fixString(String string) {
		return fixString(string, "");
	}
	
	/**
	 * Return <code>string</code>, or <code>""</code> if <code>object</code> is
	 * <code>null</code>. Never returns <code>null</code>.
	 * 
	 * @param Object obj
	 * @param defaultString
	 *            The default string to return if <code>string</code> is
	 *            <code>null</code> or <code>""</code>
	 * @return a valid (non-null) string reference
	 */
	public final static String fixString(Object obj, String defaultString) {
		String string = (obj == null ? defaultString : obj.toString());
		return fixString(string);
	}
	
	/**
	 * Return <code>string</code>, or <code>""</code> if <code>object</code> is
	 * <code>null</code>. Never returns <code>null</code>.
	 * 
	 * @param Object obj
	 * @return a valid (non-null) string reference
	 */
	public final static String fixString(Object obj) {
		return fixString(obj, "");
	}

	/**
	 * Return string for double type
	 * @param value
	 * @return
	 */
	public final static String fixString(double value) {
		return String.valueOf(value);
	}
	
	/**
	 * @param str
	 * @return
	 */
	public final static String fixStringQuote(String str) {
		return str.replaceAll("\"", "\\\\\"").replaceAll("'", "\'");
	}
	
	 

	/**
	 * Fix path, add the file separator at the end of the path and eliminate the
	 * file separator at the begin of the path.
	 * 
	 * @param path
	 *            Original path string
	 * @return Result path string
	 */
	public static String fixPathWithoutLastSlash(String path) {
		path = path.replaceAll("\\\\", "/");
		if (path.endsWith("/") || path.endsWith("\\")) {
			path = path.substring(0, path.length() - 1);
		}
		if (path.startsWith("/") || path.startsWith("\\")) {
			path = path.substring(1);
		}
		return path;
	}
	
	/**
	 * Fix path, add the file separator at the end of the path and eliminate the
	 * file separator at the begin of the path.
	 * 
	 * @param path
	 *            Original path string
	 * @return Result path string
	 */
	public static String fixPathWithLastSlash(String path) {
		return fixPathWithoutLastSlash(path) + "/";
	}

	/**
	 * Convert a String to an boolean. Accepts: 1/0, yes/no, true/false - case
	 * insensitive. If the value does not map to "true,", <code>false</code> is
	 * returned.
	 * 
	 * @param in
	 *            String to be parsed.
	 * @return boolean representation of String.
	 */
	public final static boolean parseBoolean(String in) {
		in = fixString(in);

		if (in.length() == 0) {
			return false;
		}

		switch (in.charAt(0)) {
		case '1':
		case 'y':
		case 'Y':
		case 't':
		case 'T':
			return true;
		}

		return false;
	}

	/**
	 * Join the array with specified delimiter
	 * 
	 * @param src
	 *            Array source
	 * @param delimiter
	 *            Delimiter string
	 * @return Joined string
	 */
	public final static String join(Object[] arr) {
		return join(arr, SEPARATOR);
	}
	
	/**
	 * Join the array with specified delimiter
	 * 
	 * @param src
	 *            Array source
	 * @param delimiter
	 *            Delimiter string
	 * @return Joined string
	 */
	public final static String join(Object[] arr, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * Join the collection with default separator
	 * 
	 * @param <T>
	 * @param collection
	 * @return
	 */
	public final static <T> String join(Collection<T> collection) {
		return join(collection, SEPARATOR);
	}
	
	/**
	 * Join the collection with default separator. Parameter len is used to
	 * indicate how many items will be converted
	 * 
	 * @param collection
	 * @param len
	 * @return
	 */
	public final static <T> String join(Collection<T> collection, int len) {
		return join(collection, SEPARATOR, len);
	}
	
	/**
	 * Join the collection with specified separator
	 * 
	 * @param collection
	 * @param separator
	 * @return
	 */
	public final static <T> String join(Collection<T> collection, String separator) {
		return join(collection, separator, 0);
	}

	/**
	 * Join the collection with specified separator. Parameter len is used to
	 * indicate how many items will be converted
	 * 
	 * @param <T>
	 * @param collection
	 * @param separator
	 * @param len how many items will be converted
	 * @return
	 */
	public final static <T> String join(Collection<T> collection, String separator, int len) {
		StringBuilder result = new StringBuilder();
		int k = 0;
		for (T item : collection) {
			if(len > 0 && k >= len) {
				break;
			}
			
			if (k > 0) {
				result.append(separator);
			}
			result.append(item.toString());
			k++;
		}
		return result.toString();
	}

	/**
	 * ���Ҹ�ʽ��
	 * ����ֵ��ʽ����ָ����ʽ �磤0.00 ; #,##0.00 ,0.#######
	 * 
	 * @param oldStr String �ַ���
	 * @param formatStr String �µ���ʾ��ʽ
	 * @return String ����ֵ
	 */
	public static String getNumberFormat(double oldNumber, String pattern) throws Exception {
		if (pattern == null) {
			pattern = "0.00";
		}
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		return myFormatter.format(oldNumber);
	}

	/**
	 * ���Ҹ�ʽ��
	 */
	public static char[] getNumberFormatMoney(double oldNumber) throws Exception {
		String pattern = "0.00";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String result = myFormatter.format(oldNumber);
		int resultInt = result.length();
		int dotIndex = result.indexOf(".");
		String x = "��" + result.substring(0, dotIndex) + result.substring(dotIndex + 1, resultInt);
		char ch[] = x.toCharArray();
		return ch;
	}
	
	/**
	 * Convert version name to long for the comparison. Version name could not contain any string.
	 * @param versionName
	 * @return
	 */
	public static long convertVersionName(String versionName) {
		long result = 0;
		int bit = 3;
	
		if(versionName.startsWith("v") || versionName.startsWith("V")) {
			versionName = versionName.substring(1);
		}
		String[] versions = versionName.split("\\.");
		int len = versions.length;
		for (int i = 0; i < len; i++) {
			result += Integer.parseInt(versions[i]) * Math.pow(10, bit * (len - i));
		}
		
		return result;
	}
	
	public final static String getApiVesion(String apiKey) {
		if (apiKey == null) {
			return "";
		}
		return "V" + apiKey.replaceAll("\\.", "");
	}

	/**
	 * change object value to HTML layout string
	 * 
	 * @param obj
	 * @return HTML layout string
	 */
	public final static String getHtmlLayout(Object obj) {
		StringBuilder sb = new StringBuilder();

		CharacterIterator it = new StringCharacterIterator(obj.toString());

		for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
			if (c == '"') {

			} else if (c == '\\') {

			} else if (c == '/') {

			} else if (c == '\b') {

			} else if (c == '\f') {

			} else if (c == '\n') {
				sb.append("<br/>");
			} else if (c == '\r') {

			} else if (c == '\t') {

			} else if (Character.isISOControl(c)) {

			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}
	/**
	 * 前补零 
	 * @param 
	 * @return
	 */
	public static String fullNumberByLength(String b){
          
           int maxLength=10;
           StringBuffer bf=new StringBuffer(); 
           if(maxLength>=b.length()){
        	   for(int i=0;i<maxLength-b.length();i++){
        		   bf.append("0");
        	   }
        	   bf.append(b);
        	}
           
           return bf.toString();
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrBlank(Object obj){
		if(obj==null||String.valueOf(obj).equals("null")||String.valueOf(obj).equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotNullOrBlank(Object obj){
		if(obj==null||String.valueOf(obj).equals("null")||String.valueOf(obj).equals("")){
			return false;
		}
		return true;
	}

}
