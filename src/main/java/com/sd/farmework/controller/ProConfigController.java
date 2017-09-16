package com.sd.farmework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.ProConfig;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.service.ProConfigService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/conf")
public class ProConfigController {
	@Autowired
	private ProConfigService proconfService;
	
	@RequestMapping(value = "/findallconf", method = RequestMethod.POST)
	public void findallconf(ProConfig conf, Model model) throws Exception{
		List<BaseInfo> list=this.proconfService.queryList(conf);	
		model.addAttribute("proconfig", list);
	}

}
