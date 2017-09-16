package com.sd.farmework.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.SendSMSYes;
import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.ApplicationConfig;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SmSInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.CustomerListService;
import com.sd.farmework.service.SmSInfoService;
import com.sd.farmework.service.SysLogInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/sms")
public class SmSInfoController {
	private static Logger logger = Logger.getLogger(SmSInfoController.class);
	@Autowired
	private SmSInfoService smSInfoService;
	@Autowired
	private CustomerListService customerInfoService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	private ApplicationConfig applicationConfig;

	/**
	 * 短信消息列表
	 * 
	 * @param sms
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/smsList")
	public String smsList(SmSInfo obj, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("SmsInfoController smsList:start");
		try {
			if (StringUtil.isNotNullOrBlank(obj.getCustomer_name())) {
				String tmpStr = new String(obj.getCustomer_name().getBytes(
						"ISO-8859-1"), "utf-8");
				obj.setCustomer_name(tmpStr);
			}
			if (StringUtil.isNotNullOrBlank(obj.getService_person())) {
				String tmpStr = new String(obj.getService_person().getBytes(
						"ISO-8859-1"), "utf-8");
				obj.setService_person(tmpStr);
			}
			HttpSession session = request.getSession();
			UserInfo user = (UserInfo) session.getAttribute("loginUser");
			obj.setService_person(user.getUser_name());
			logger.info("分页查询开始");
			List list = smSInfoService.queryList(obj);
			logger.info("分页查询结束");
			model.addAttribute("smslist", list);
			obj.setTotalCount(smSInfoService.queryCount(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);

			// 取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo) request.getSession()
					.getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息：" + sessionRole.toString() + "\t 功能id："
					+ function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "customerinformation/smslist";
	}

	/**
	 * 发送短信消息页面
	 * 
	 * @param sms
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addSmsUI", method = RequestMethod.GET)
	public String addSmsUI(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "customerinformation/addsms";
	}

	/**
	 * 发送短信消息页面
	 * 
	 * @param sms
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addSmS", method = RequestMethod.POST)
	public Object addSmS(SmSInfo obj, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		String context = "";
		String[] cusid = request.getParameter("cusidStr").split(",");
		String[] cusname = request.getParameter("cusnameStr").split(",");
		String topic = request.getParameter("topic");
		String content = request.getParameter("content");
		List<String> cusidlist = new ArrayList<String>();
		List<String> cusnamelist = new ArrayList<String>();
		try {
			if (StringUtil.isNotNullOrBlank(cusid)
					|| StringUtil.isNotNullOrBlank(cusname)) {
				for (int i = 0; i < cusid.length; i++) {
					cusidlist.add(i, cusid[i]);
				}
				for (int i = 0; i < cusname.length; i++) {
					cusnamelist.add(i, cusname[i]);
				}
				for (int i = 0; i < cusidlist.size(); i++) {
					SmSInfo sms = new SmSInfo();
					sms.setCustomer_id(cusidlist.get(i));
					sms.setCustomer_name(cusnamelist.get(i));
					sms.setService_no(user.getUser_no());
					sms.setService_person(user.getUser_name());
					sms.setTopic(topic);
					sms.setContext(content);
					sms.setCreateUserId(user.getEmployee_id());
					CustomerInfo customer = new CustomerInfo();
					customer.setCustomer_id(cusidlist.get(i));
					CustomerInfo customerInfo = this.customerInfoService
							.queryById1(customer);
					SendSMSYes.send(customerInfo.getPhone(), content,
							this.applicationConfig);
					smSInfoService.add(sms);

					context = "对客户" + sms.getCustomer_name() + "发了一条短信";
					OperationLogUtil.writeLog(sysLogInfoService, context, user);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map map = new HashMap();
		map.put("code", "ok");
		return map;
	}

	/**
	 * 查询当前用户的相关员工
	 * 
	 * @param sms
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selectUserCus", method = RequestMethod.POST)
	public Object selectUserCus(SmSInfo obj, Model model,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		List list = (List) customerInfoService.queryByUser(user.getUser_name());
		Map<String, List> map = new HashMap<String, List>();
		map.put("list", list);
		return map;
	}

	/**
	 * 短信详情
	 * 
	 * @param sms
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/smsDtail", method = RequestMethod.GET)
	public String smsDtail(SmSInfo obj, Model model) {
		try {
			SmSInfo sms = (SmSInfo) smSInfoService.queryById(obj);
			model.addAttribute("sms", sms);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "customerinformation/smsdetil";
	}

	/**
	 * 选择联系人页面
	 * 
	 * @param sms
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(SmSInfo obj, Model model) {

		return "customerinformation/contacts";
	}

	/**
	 * 查询联系人
	 * 
	 * @param sms
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryCustomerByIdName", method = RequestMethod.POST)
	public String queryCustomer(SmSInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		try {
			List list = (List) customerInfoService.queryByUser(user
					.getEmployee_id());
			model.addAttribute("list", list);
			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
