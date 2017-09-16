package com.sd.farmework.common.util;
import java.util.List;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 2013-3-20
 */
public class CollectionUtil {

	/**
	 * Judge whether the specified list is null or empty
	 * 
	 * @param list
	 * @return boolean
	 */
	public static boolean isBlank(List<?> list) {
		return (list == null || list.size() < 1);
	}
	
	/**
	 * @param src
	 * @param desc
	 * @return
	 */
	public static boolean isContains(String[] src, String desc) {
		desc = StringUtil.fixString(desc);
		for (String srcItem : src) {
			if(desc.equals(srcItem)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * @param src
	 * @param desc
	 * @return
	 */
	public static boolean isContains(int[] src, int desc) {
		for (int srcItem : src) {
			if(desc == srcItem) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * @param src
	 * @param desc
	 * @return
	 */
	public static boolean isContains(Object[] src, Object desc) {
		if(desc == null) {
			return false;
		}
		
		for (Object srcItem : src) {
			if(desc.equals(srcItem)) {
				return true;
			}
		}

		return false;
	}
	
}
