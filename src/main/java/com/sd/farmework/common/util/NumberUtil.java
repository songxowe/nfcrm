package com.sd.farmework.common.util;
import java.math.BigDecimal;

/**
 * <p>
 * Number utility class.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 8/22/2013
 */
public class NumberUtil {

	/**
	 * Parse a string to int. The default value will be used if string is null.
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public final static int parseInteger(String value, int defaultValue) {
		return (!StringUtil.isNullOrBlank(value)) ? Integer.parseInt(value) : defaultValue;
	}

	/**
	 * Parse a string to int. 0 will be default value if string is null.
	 * 
	 * @param value
	 * @return
	 */
	public final static int parseInteger(String value) {
		return parseInteger(value, 0);
	}

	/**
	 * Parse a BigDecimal to int. 0 will be default value if value is null.
	 * 
	 * @param value
	 * @return
	 */
	public final static int parseInteger(BigDecimal value) {
		int result = 0;
		if (value != null)
			result = value.intValue();
		return result;
	}

	/**
	 * Get BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public final static BigDecimal fixBigDecimal(BigDecimal value) {
		if(value == null) {
			return parseBigDecimal("", 0);
		}
		return value;
	}
	
	/**
	 * Get BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public final static BigDecimal parseBigDecimal(double value) {
		return new BigDecimal(value);
	}

	/**
	 * Get BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public final static BigDecimal parseBigDecimal(String value) {
		return parseBigDecimal(value, 0);
	}

	/**
	 * Get BigDecimal with specified default value
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public final static BigDecimal parseBigDecimal(String value, int defaultValue) {
		if (StringUtil.isNullOrBlank(value)) {
			value = "" + defaultValue;
		}
		return new BigDecimal(value);
	}

	/**
	 * �ӷ�����
	 * 
	 * @param v1
	 *            double
	 * @param v2
	 *            double
	 * @return BigDecimal
	 */
	public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
		return v1.add(v2);
	}

	/**
	 * �ṩ��ȷ�ļӷ����㡣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������ĺ�
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * Do add for string
	 * 
	 * @param str
	 * @param num
	 * @return
	 */
	public final static String add(String str, int num) {
		int value = Integer.parseInt(str);
		return (value + num) + "";
	}

	/**
	 * ��������
	 * 
	 * @param v1
	 *            double
	 * @param v2
	 *            double
	 * @return BigDecimal
	 */
	public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
		return v1.subtract(v2);
	}

	/**
	 * �ṩ��ȷ�ļ������㡣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������Ĳ�
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * �˷�����
	 * 
	 * @param v1
	 *            double
	 * @param v2
	 *            double
	 * @return BigDecimal
	 */
	public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
		return v1.multiply(v2);
	}

	/**
	 * �ṩ��ȷ�ĳ˷����㡣
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������Ļ�
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * �ṩ����ԣ���ȷ�ĳ������㡣
	 * �����������������ʱ����scale����ָ�����ȣ��Ժ�������������롣 *
	 * 
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @param scale
	 *            ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
	 * @return ������������
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * �ṩ��ȷ��С��λ�������봦��
	 * 
	 * @param v
	 *            ��Ҫ�������������
	 * @param scale
	 *            С���������λ
	 * @return ���������Ľ��
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
