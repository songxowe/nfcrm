package com.sd.farmework.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.ReceiveMessageInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SendMessageInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.ReceiveMessageService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.services.util.FileUpload;

@Controller
@Scope("prototype")
@RequestMapping(value="/receivemessage")
public class ReceiveMessageController {
	private static Logger logger = Logger.getLogger(ReceiveMessageController.class);
	@Autowired
	private ReceiveMessageService receiveMessageService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	
	/**
	 * 收件列表
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/receiveMessageList")
	public String receiveMessageList(ReceiveMessageInfo obj,Model model,
			HttpServletRequest request) {
		
		try {
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			obj.setEmployee(user.getUser_name());
			List<BaseInfo> list=receiveMessageService.queryList(obj);
			model.addAttribute("list", list);
			obj.setTotalCount(receiveMessageService.queryCount(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);
			//取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo)request.getSession().getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息："+sessionRole.toString()+"\t 功能id："+function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "informationcenter/inboxlist";
	}
	
	/**
	 * 收件提醒
	 */
	@ResponseBody
	@RequestMapping(value = "/Sendlogin", method = RequestMethod.GET)
	public Object sendLogin(HttpServletRequest request, HttpServletResponse response){
		//查询登录人id
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("loginUser");
		List<SendMessageInfo> list=receiveMessageService.queryCountBySendlogin(userinfo.getUser_id());
		//把数据传到页面
		Map map = new HashMap();
		logger.info("未读邮件提醒");
		map.put("code", "000");
		map.put("returnList", list);
		return map;
		}
	
	/**
	 * 发信箱列表
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/outBox")
	public String outBox(SendMessageInfo obj,Model model,
			HttpServletRequest request) {
		try {
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			obj.setSend_person(user.getUser_name());
			List list=receiveMessageService.queryBySendPerson(obj);
			model.addAttribute("list", list);
			obj.setTotalCount(receiveMessageService.queryCountBySendPerson(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);
			//取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo)request.getSession().getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息："+sessionRole.toString()+"\t 功能id："+function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "informationcenter/outboxlist";
	}
	
	/**
	 * 进入发信箱
	 * @param receive
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/outBoxUI", method = RequestMethod.GET)
	public String outBoxUI(ReceiveMessageInfo obj,Model model,
			HttpServletRequest request) {
		
		return "informationcenter/outbox";
	}
	/**
	 * 选择用户
	 * @param receive
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(ReceiveMessageInfo obj,Model model,
			HttpServletRequest request) {
		
		return "informationcenter/contacts";
	}
	/**
	 * 发信箱
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addOutBox", method = RequestMethod.POST)
	public Object addOutBox(SendMessageInfo obj,ReceiveMessageInfo obj1,Model model,
			HttpServletRequest request) {
		try {
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			String context = "";
			String[] retStr=request.getParameter("retStr").split(",");//收件人
			String[] lidStr=request.getParameter("lidStr").split(",");//收件人id
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			List<String> retList=new ArrayList<String>();
			List<String> lidList=new ArrayList<String>();
			for(int i=0;i<retStr.length;i++){
				retList.add(i, retStr[i]);
			}
			for(int i=0;i<lidStr.length;i++){
				lidList.add(i, lidStr[i]);
			}
			for(int i=0;i<retList.size();i++){
				SendMessageInfo send=new SendMessageInfo();
				send.setRecipient(retList.get(i));
				send.setRecipient_id(lidList.get(i));
				send.setSend_person(user.getUser_name());
				send.setTitle(title);
				send.setContext(content);
				send.setCreateUserId(user.getEmployee_id());
				receiveMessageService.add(send);
			}
			for(int i=0;i<retList.size();i++){
				ReceiveMessageInfo rece=new ReceiveMessageInfo();
				rece.setEmployee_no(lidList.get(i));
				rece.setEmployee(retList.get(i));
				rece.setRecipient_no(user.getUser_id());
				rece.setRecipient(user.getUser_name());
				rece.setPos_name(title);
				rece.setContext(content);
				rece.setStatus("0");
				rece.setCreateUserId(user.getEmployee_id());
				receiveMessageService.addRecMsg(rece);
				
				context = "对员工"+rece.getEmployee()+"发了一条短消息";
				OperationLogUtil.writeLog(sysLogInfoService,context,user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map rspMap = new HashMap();
		rspMap.put("code", "ok");
		return rspMap;
	}
	/**
	 * 收件详情
	 * @param receive
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inboxDetail", method = RequestMethod.GET)
	public String inboxDetail(ReceiveMessageInfo obj,Model model) {
		try {
			List list=receiveMessageService.queryCheckDesig(obj);
			model.addAttribute("inboxlist", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "informationcenter/inboxdetail";
	}
	/**
	 * 发件详情
	 * @param receive
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/outboxDetail", method = RequestMethod.GET)
	public String outboxDetail(SendMessageInfo obj,Model model) {
		try {
			List list=receiveMessageService.querySendList(obj);
			model.addAttribute("outboxlist", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "informationcenter/outboxdetail";
	}
	/**
	 * 改变状态
	 * @param receive
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeState", method = RequestMethod.POST)
	public Object changeState(ReceiveMessageInfo obj,Model model,
			HttpServletRequest request) {
		try {
			String rid=request.getParameter("rid");
			receiveMessageService.changeState(rid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map map=new HashMap();
		map.put("code", "ok");
		return map;
	}
	
	
}
