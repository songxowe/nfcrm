package com.sd.farmework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sd.farmework.pojo.DateDictionaryInfo;
import com.sd.farmework.service.DateDictionaryInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value="/menu")
public class MenuController {
	@Autowired
	private DateDictionaryInfoService dictionaryService;
	/**
	 * 基本信息 basic
	 * 客户等级 customerlv
	 */
	@RequestMapping(value = "/custlv", method = RequestMethod.GET)
	public String customerlv(Model model){
		
		return "basicinformation/customerlv";
	}
	/**
	 * 系统管理 systemmanagement
	 * 用户管理 user
	 */
	/*@RequestMapping(value = "/usermannager", method = RequestMethod.GET)
	public String usermannager(Model model){
		
		return "systemmanagement/userlist";
	}*/
	/**
	 * 系统管理 systemmanagement
	 * 权限管理 permission
	 */
	@RequestMapping(value = "/permissmanger", method = RequestMethod.GET)
	public String permissmanger(Model model){
		
		return "systemmanagement/permissionslist";
	}
	/**
	 * 角色管理
	 * @return
	 * @author wangchaochao
	 */
	@RequestMapping(value = "/roleManager")
	public String roleManager(Model model){
		
		return "powermanager/roleTemplate";
	}
	
	/**
	 * 员工管理 employee
	 */
	/*@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String employee(Model model){
		
		return "employee/selectEmployee.do";
	}*/
	
	/**
	 * 项目管理 projectmanagement
	 * 项目列表 item
	 */
	@RequestMapping(value = "/projectmat", method = RequestMethod.GET)
	public String projectmanment(Model model){
		
		return "projectmanagement/itemlist";
	}
	
	/**
	 * 客户信息 customerinformation
	 * 客户列表 customerinfo
	 */
	@RequestMapping(value = "/custinfomtion", method = RequestMethod.GET)
	public String customerinfo(Model model){
		
		return "customerinformation/customerinfolist";
	}
	/**
	 * 新增客户信息 customerinformation
	 * 客户列表 customerinfo
	 */
	@RequestMapping(value = "/addcustomerinfo", method = RequestMethod.GET)
	public String addcustomerinfo(DateDictionaryInfo datadictionary,Model model){
		datadictionary= (DateDictionaryInfo)this.dictionaryService.querycountdown(datadictionary);
		model.addAttribute("DataTime", datadictionary);
		return "customerinformation/addcustomerinfo";
	}
	/**
	 * 客户信息 customerinformation
	 * 公共客户 publiccus
	 */
	@RequestMapping(value = "/publiccus", method = RequestMethod.GET)
	public String publiccus(Model model){
		
		return "customerinformation/publiccuslist";
	}
	/**
	 * 客户信息 customerinformation
	 * 历史客户 historcus
	 */
	@RequestMapping(value = "/historcus", method = RequestMethod.GET)
	public String historcus(Model model){
		
		return "customerinformation/historcuslist";
	}
	/**
	 * 客户信息 customerinformation
	 * 二次客户 secondreg
	 */
	@RequestMapping(value = "/secondreg", method = RequestMethod.GET)
	public String secondreg(Model model){
		
		return "customerinformation/secondreglist";
	}
	/**
	 * 客户信息 customerinformation
	 * 带看客户 takelook
	 */
	@RequestMapping(value = "/takelook", method = RequestMethod.GET)
	public String takelook(Model model){
		
		return "customerinformation/takelooklist";
	}
	/**
	 * 客户信息 customerinformation
	 * 短信中心 sms
	 */
	@RequestMapping(value = "/sms", method = RequestMethod.GET)
	public String sms(Model model){
		
		return "customerinformation/smslist";
	}
	/**
	 * 客户信息 customerinformation
	 * 新建短信 addsms
	 */
	@RequestMapping(value = "/addsms", method = RequestMethod.GET)
	public String addsms(Model model){
		
		return "customerinformation/addsms";
	}
	
	/**
	 * 信息报备 informationreported
	 * 报备列表 reportedlist
	 */
	@RequestMapping(value = "/informationrepoted", method = RequestMethod.GET)
	public String informarepoted(Model model){
		
		return "informationreported/reportedlist";
	}
	/**
	 * 审核 auditmanagement
	 * 审核列表 auditlist
	 */
	@RequestMapping(value = "/auditmanagement", method = RequestMethod.GET)
	public String auditmanment(Model model){
		
		return "auditmanagement/auditlist";
	}
	/**
	 * 信息中心 informationcenter
	 * 收信箱 inbox
	 */
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public String inbox(Model model){
		
		return "informationcenter/inboxlist";
	}
	
	/**
	 * 信息中心 informationcenter
	 * 发信箱 outbox
	 */
	@RequestMapping(value = "/outbox", method = RequestMethod.GET)
	public String outbox(Model model){
		
		return "informationcenter/outbox";
	}
	
	/**
	 * 添加二次注册客户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addsecondcus", method = RequestMethod.GET)
	public String addsecondcus(Model model){
		
		return "customerinformation/addsecondclient";
	}
	/**
	 * 添加带看客户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addtoseecus", method = RequestMethod.GET)
	public String addtoseecus(Model model){
		
		return "customerinformation/addtoseeclientMsg";
	}
	/**
	 * 礼品发放申请
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/giftapply", method = RequestMethod.GET)
	public String giftapply(Model model){
		
		return "customerinformation/nopreGiftApply";
	}
	
	
	
	
	
	
	/**
	 * 查看历史客户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hisclientMsg", method = RequestMethod.GET)
	public String hisclientMsg(Model model){
		
		return "customerinformation/hisclientMsg";
	}
	/**
	 * 公客详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pubcusMsg", method = RequestMethod.GET)
	public String pubcusMsg(Model model){
		
		return "customerinformation/pubclientMsg";
	}
	/**
	 * 二次注册客户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/secondcusMsg", method = RequestMethod.GET)
	public String secondcusMsg(Model model){
		
		return "customerinformation/secondclientMsg";
	}
	/**
	 * 带看客户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toseecusMsg", method = RequestMethod.GET)
	public String toseecusMsg(Model model){
		
		return "customerinformation/toseeclientMsg";
	}
	/**
	 * 添加员工信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addEployeeMsg", method = RequestMethod.GET)
	public String addEployeeMsg(Model model){
		
		return "employee/addEployeeMsg";
	}
	/**
	 * 查看员工详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/eployeeDetailMsg", method = RequestMethod.GET)
	public String eployeeDetailMsg(Model model){
		
		return "employee/eployeeDetailMsg";
	}
	/**
	 * 新增项目信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addpro", method = RequestMethod.GET)
	public String addpro(Model model){
		
		return "projectmanagement/addpro";
	}
	/**
	 * 项目详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/prodetail", method = RequestMethod.GET)
	public String prodetail(Model model){
		
		return "projectmanagement/prodetail";
	}
	/**
	 * 添加用户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model){
		
		return "systemmanagement/addUser";
	}
	/**
	 * 基础设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/baseset", method = RequestMethod.GET)
	public String baseset(Model model){
		
		return "systemmanagement/baseSetting";
	}
	/**
	 * 修改密码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
	public String updatePwd(Model model){
		
		return "systemmanagement/updatePwd";
	}
}
