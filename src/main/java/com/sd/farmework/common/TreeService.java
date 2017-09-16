package com.sd.farmework.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.RoleInfo;

 
/**
 * @author  yangyang 
 */
public class TreeService {
	private static Log log = LogFactory.getLog(TreeService.class);

	 
	public List<Map<String, Object>> getTreeData(List<Map<String, Object>> dataList,String nodeParam,RoleInfo param) {
		return genTreeStucture(dataList, nodeParam,param);
	}
	
 
	
	private List<Map<String, Object>> genTreeStucture(List<Map<String, Object>> list, String rootId,RoleInfo param) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if(rootId.equals(map.get("PARENT_ID"))) {
				//TODO Matched item could be remove from list to do the optimization
				List<Map<String, Object>> childList = genTreeStucture(list, (String)map.get("ID"),param);
				
				HashMap<String, Object> treeMap = new HashMap<String, Object>();
				treeMap.put("id", map.get("ID"));
				treeMap.put("text", map.get("NAME"));
				treeMap.put("parentId", map.get("PARENT_ID"));
				StringBuffer sb = new StringBuffer();
				sb.append(map.get("PARENT_ID"));
				sb.append("_");
				sb.append(map.get("ID"));
				if(!StringUtil.isNullOrBlank(param.getFunction_operate_ids())){
					if(param.getFunction_operate_ids().indexOf(String.valueOf(sb))>-1){
						treeMap.put("checked", "checked");
					}
				}
				treeMap.put("functionOperateIds", sb.toString());
				treeMap.put("children", childList);
				result.add(treeMap);
			}
		}
		return result;
	}
	
}
