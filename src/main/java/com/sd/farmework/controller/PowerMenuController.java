package com.sd.farmework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.PowerMenu;
import com.sd.farmework.service.PowerService;

/**
 * 权限菜单
 * 
 * @author 王超超 2016-10-28
 * 
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class PowerMenuController {
	private static Logger logger = Logger.getLogger(PowerMenuController.class);
	@Autowired
	private PowerService powerService;

	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMenuInfo", method = RequestMethod.GET)
	public String getMenuInfo(PowerMenu obj, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("PowerMenuController getMenuInfo:start");
		Map<String, Object> menuMap = new HashMap<String, Object>();
		Map menu1 = new HashMap();
		try {
			obj.setParent_function_id("0");
			List<BaseInfo> menuList = powerService.queryList(obj);
			if (menuList == null) {
				logger.info("没有相关记录信息");
				return null;
			}
			PowerMenu power = (PowerMenu) menuList.get(0);
			menuMap.put("id", power.getFunciton_id());
			menuMap.put("text", power.getFunction_name());
			List<Map<String, Object>> childListMap = new ArrayList<Map<String, Object>>();
			 getChildNodes(childListMap,menuMap);

			 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			 list.add(menuMap);
			 JSONArray js=JSONArray.fromObject(list);
			System.out.println( js.toString());
			 
			
			response.getWriter().write(js.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private List<Map<String, Object>> getChildNodes(List<Map<String, Object>> childListMap ,Map<String, Object> menuMap)
			throws Exception {

		PowerMenu powerPara = new PowerMenu();
		powerPara.setParent_function_id(String.valueOf(menuMap.get("id")));
		List<BaseInfo> childList = powerService.queryList(powerPara);
		
		
		List<Map<String, Object>> list = new  ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			PowerMenu function = (PowerMenu) childList.get(i);
			functionMap.put("id", function.getFunciton_id());
			functionMap.put("text", function.getFunction_name());
			list = getChildNodes1(childListMap,functionMap);
			
			functionMap.put("children", list);
			
			
			childListMap.add(functionMap);

		}
		
		menuMap.put("children",childListMap);
		
		
		return childListMap;
	}

	
	private List<Map<String, Object>> getChildNodes1(List<Map<String, Object>> childListMap ,Map<String, Object> menuMap)
			throws Exception {

		PowerMenu powerPara = new PowerMenu();
		powerPara.setParent_function_id(String.valueOf(menuMap.get("id")));
		List<BaseInfo> childList = powerService.queryList(powerPara);
		
		
		List<Map<String, Object>> list = new  ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			PowerMenu function = (PowerMenu) childList.get(i);
			functionMap.put("id", function.getFunciton_id());
			functionMap.put("text", function.getFunction_name());

			//childListMap.add(functionMap);
		
			list.add(functionMap);

		}
		
		return list;
	}
	
}
