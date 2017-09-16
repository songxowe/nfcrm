package com.sd.farmework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.LeaveInfo;
import com.sd.farmework.pojo.LogInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.LogInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/log")
public class LogInfoController {
	@Autowired
	private LogInfoService logInfoservice;
	private static Logger logger = Logger.getLogger(LogInfoController.class);

	/**
	 * 查询项目的跟进日志
	 * 
	 * @param leave
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/thisLogMsg", method = RequestMethod.GET)
	public Object addleaveMsg(LogInfo log, Model model,HttpServletResponse response,HttpServletRequest request) throws Exception {
		Map map=new HashMap();
		if (StringUtil.isNotNullOrBlank(log.getFollow_pro())) {
			String tmpStr = new String(
					log.getFollow_pro().getBytes("ISO-8859-1"), "utf-8");
			log.setFollow_pro(tmpStr);
		}
		List<BaseInfo> loginfo=this.logInfoservice.queryList(log);
		model.addAttribute("mylog", loginfo);
		map.put("loginfo", loginfo);
		return map;
	}

	/**
	 * 跟进日志详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logDetail", method = RequestMethod.GET)
	public String logDetail(LogInfo log,Model model){
		
		try {
			LogInfo loginfo=(LogInfo) logInfoservice.queryById(log);
			model.addAttribute("loginfo", loginfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "customerinformation/logdetail";
	}
}
