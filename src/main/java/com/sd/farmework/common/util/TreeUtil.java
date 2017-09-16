package com.sd.farmework.common.util;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on 7/27/2013
 */
public class TreeUtil {

	/**
	 * ����tree HTMl���룬�����������ڵ㣬���ڵ�����htmlֱ�Ӷ��壬��������ԣ�����<ul class='tree tree_folder'>
	 * @param list
	 * @param callback �����Զ������ڵ����ʾ
	 * @return
	 */
	public static String getTreeHtml(List<Map<String, Object>> list, Callback callback) {
		StringBuilder buf = new StringBuilder();
		buf.append(genTreeHtml(list, callback));
		return buf.toString();
	}
	
	@SuppressWarnings("unchecked")
	private static String genTreeHtml(List<Map<String, Object>> list, Callback callback) {
		StringBuilder buf = new StringBuilder();
		
		for(Map<String, Object> map: list) {
			String id = (String)map.get("id");
			String text = (String)map.get("text");
			String parentId = (String)map.get("parentId");
			boolean hasChild = ((List<?>)map.get("child")).size() > 0 ? true : false;
			
			if(hasChild) {
				buf.append("<li>" + callback.getLink(id, text, parentId, map) + "\n<ul>\n");
				buf.append(genTreeHtml((List<Map<String, Object>>)map.get("child"), callback));
				buf.append("</ul>\n</li>\n");
			} else {
				buf.append("<li>" + callback.getLink(id, text, parentId, map) + "</li>\n");
			}
		}
		return buf.toString();
	}
	
	public interface Callback {
		public String getLink(String id, String text, String parentId, Map<String, Object> map);
	}
	
}
