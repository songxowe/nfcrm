package com.sd.farmework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SysLogInfo;
import com.sd.farmework.service.SysLogInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value="/queryLog")
public class OperationLogController {
	private static Logger logger = Logger.getLogger(OperationLogController.class);
	@Autowired
	private SysLogInfoService sysLogInfoService;
	
	@RequestMapping(value="/getOperatinRecord" )
	public String getOperatinRecord(SysLogInfo obj,Model model,HttpServletRequest request){
		SysLogInfo SysLogInfo = new SysLogInfo();
		List list =null;
		
		try {
			
			int allount = sysLogInfoService.queryCount(obj);
			 obj.setTotalCount(allount);
			list = sysLogInfoService.queryList(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("list", list);
		//取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo)request.getSession().getAttribute("roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息："+sessionRole.toString()+"\t 功能id："+function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		
		return "/operatinRecord/operationList";
		
	}
	
	
}
