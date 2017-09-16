package com.sd.farmework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.DateDictionaryInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.DateDictionaryInfoService;
import com.sd.farmework.service.SysLogInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value="/dateDictionary")
public class DateDictionaryInfoController {
	@Autowired
	private DateDictionaryInfoService dateDictionaryInfoService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	/**
	 * 查询数据字典
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/selectDateDiction", method = RequestMethod.POST)
	public Map<String,Object> selectDateDiction(DateDictionaryInfo obj,Model model){
		
		try {
				Map<String, Object> map = new HashMap<String, Object>();
				List list=dateDictionaryInfoService.queryList(obj);
				map.put("list", list);
				
				return map;
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		return null;
	}
	/**
	 * 查询数据字典，根据类型id
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/selectDateDictionType", method = RequestMethod.POST)
	public Map<String,Object> selectDateDictionType(DateDictionaryInfo obj,Model model,HttpServletRequest request){
		
		try {
				String functiontype=request.getParameter("function_type");
				obj.setFunction_type(functiontype);
				Map<String, Object> map = new HashMap<String, Object>();
				List list=dateDictionaryInfoService.queryType(obj);
				map.put("list", list);
				
				return map;
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		return null;
	}
	/**
	 * 基础设置页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/baseset", method = RequestMethod.GET)
	public String baseset(DateDictionaryInfo datadictionary,Model model){
		DateDictionaryInfo datadic=(DateDictionaryInfo) dateDictionaryInfoService.queryloginfo(datadictionary);
		model.addAttribute("datadic", datadic);
		return "systemmanagement/baseSetting";
	}
	/**
	 * 	基础设置
	 */
	@ResponseBody
	@RequestMapping(value = "/basicset", method = RequestMethod.POST)
	public Object basicset(DateDictionaryInfo obj,HttpServletRequest request, Model model){
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("loginUser");
		String context = "";
		String num=request.getParameter("num");
		String dtype=request.getParameter("dtype");
		
		if(StringUtil.isExisted(dtype, "1")){
			obj.setD_type("1");
			obj.setD_value(num);
		}
		String type=obj.getD_type();
		try {
			if(StringUtil.isExisted(type, "1")){
				
				dateDictionaryInfoService.updateNum(obj);
				
			context = "将跟进字数修改为"+obj.getD_value();
			OperationLogUtil.writeLog(sysLogInfoService,context,userinfo);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map map=new HashMap();
		map.put("code", "ok");
		return map;
	}
}
