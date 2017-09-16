package com.sd.farmework.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.pojo.DateDictionaryInfo;
import com.sd.farmework.pojo.LogInfo;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.pojo.ReportedInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.CustomerListService;
import com.sd.farmework.service.DateDictionaryInfoService;
import com.sd.farmework.service.LogInfoService;
import com.sd.farmework.service.ProjectInfo1Service;
import com.sd.farmework.service.PubCustomerService;
import com.sd.farmework.service.ReportedInfo1Service;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.UserAdminService;
import com.sd.farmework.services.util.FileUpload;

@Controller
@Scope("prototype")
@RequestMapping("/pubcustomer")
public class PubCustomerController {
	private static final Object Map = null;
	private static Logger logger = Logger
			.getLogger(PubCustomerController.class);
	@Autowired
	private CustomerListService customerInfoService;
	@Autowired
	private PubCustomerService pubCustomerService;
	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	@Autowired
	private DateDictionaryInfoService dictionaryService;
	@Autowired
	private ProjectInfo1Service projectInfo1Service;
	@Autowired
	private ReportedInfo1Service reportedInfo1Service;

	/**
	 * 查询倒计时
	 * 
	 * @param obj
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectTime", method = RequestMethod.POST)
	public Object selectTime(DateDictionaryInfo datadictionary,
			LogInfo loginfo, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		Map map = new HashMap();
		datadictionary = (DateDictionaryInfo) this.dictionaryService
				.querycountdown(datadictionary);
		String countdown = datadictionary.getD_value();
		String proid = request.getParameter("pro_id");

		LogInfo log = new LogInfo();
		log.setPro_id(proid);
		log = (LogInfo) this.logInfoService.queryTime(loginfo);

		String last = log.getLastUpdateTime();
		Date lastTime = DateUtil.getDateFromString(last);
		Date date = new Date();
		long nowTime = date.getTime();
		Double C = ((double) (nowTime - lastTime.getTime()) / (double) 86400000);
		if (C - Math.floor(C) > 0) {
			C = Math.floor(C) + 1;
		}
		int T = Integer.parseInt(countdown);
		double time = T - C;
		if (time <= 0) {
			String obj = "开发倒计时过期请继续跟进";
			map.put("TimeDate", obj);
		} else {
			map.put("TimeDate", time);
		}
		return map;

	}

	/**
	 * 分页显示所有公客信息
	 * 
	 * @author Auser醴陵老九
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPub")
	public String findPub(CustomerInfo customerInfo,
			DateDictionaryInfo datadictionary, LogInfo loginfo, Model model,
			HttpServletRequest request) throws Exception {
		customerInfo.setTotalCount(this.pubCustomerService
				.queryCount(customerInfo));
		model.addAttribute("totalCount", customerInfo.getPageCount());
		model.addAttribute("currPage", customerInfo.getCurrPage());
		model.addAttribute("customerInfo", customerInfo);
		List<CustomerInfo> customerList = pubCustomerService
				.queryPubList(customerInfo);
		/* 分别计算客户列表上的客户的开发倒计时 */
		datadictionary = (DateDictionaryInfo) this.dictionaryService
				.querycountdown(datadictionary);
		String countdown = datadictionary.getD_value();
		long nowTime = new Date().getTime();
		int T = Integer.parseInt(countdown);
		String leftover = null;
		LogInfo log = new LogInfo();
		double time = 0;
		for (int i = 0; i < customerList.size(); i++) {
			customerInfo = (CustomerInfo) customerList.get(i);
			// System.out.println(obj.toString());
			log.setPro_id(customerInfo.getPro_id());
			loginfo = (LogInfo) this.logInfoService.queryTime(log);
			if (StringUtil.isNotNullOrBlank(loginfo)) {
				String last = loginfo.getLastUpdateTime();
				Date lastTime = DateUtil.getDateFromString(last);
				Double C = ((double) (nowTime - lastTime.getTime()) / (double) 86400000);
				if (C - Math.floor(C) > 0) {
					C = Math.floor(C) + 1;
				}
				time = T - C;
				if (time < 0) {
					leftover = "0天";
				} else if (customerInfo.getCustomer_type().equals("2")) {
					leftover = "客户已成交请关注后续进展";
				} else {
					leftover = time + "天";
				}
			} else {
				leftover = "0天";
				if (leftover.equals("0天")
						& (customerInfo.getCustomer_type().equals("0") | customerInfo
								.getCustomer_type().equals("3"))) {
					logger.info("带看和私客倒计时过期系统自动执行转为公客");
					if (customerInfo.getCustomer_type().equals("0")) {
						customerInfo.setCustomer_type("1");
						leftover = "开发倒计时过期带看自动转为公客";
						customerInfo.setAdscription("");
						customerInfo.setAdscription_id("");
						this.pubCustomerService.upbatch(customerInfo);
					} else {
						String context = "";
						customerInfo.setCustomer_type("1");
						leftover = "开发倒计时过期私客自动转为公客";
						customerInfo.setAdscription("");
						customerInfo.setAdscription_id("");
						this.pubCustomerService.upbatch(customerInfo);
					}
				}
			}
			if (leftover.equals("0天")) {
				leftover = "开发倒计时完成请继续跟踪";
			}
			customerInfo.setOpen_countdown(leftover);
			// System.out.println(leftover);
		}
		// 取出登录的session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("customerList", customerList);
		return "customerinformation/pubcustomlist";
	}

	/**
	 * 验证客户是否存在
	 * 
	 * @throws UnsupportedEncodingExceptio
	 */
	@RequestMapping(value = "queryCustomerName", method = RequestMethod.POST)
	public void queryCustomerName(CustomerInfo customer,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		customer = new CustomerInfo();
		String cname = request.getParameter("recommend_person");
		System.out.println(cname);
		customer.setCustomer_name(request.getParameter("recommend_person"));
		CustomerInfo customerInfo = pubCustomerService
				.queryCustomerName(customer);
		if (customerInfo == null) {
			try {
				PrintWriter out = response.getWriter();
				out.println(1);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 编辑客户信息
	 * 
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryCustomerById", method = RequestMethod.GET)
	public String querybyid(CustomerInfo customer, CustomerProJInfo obj,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		System.out.println("进来了...");
		CustomerInfo customerList = (CustomerInfo) this.pubCustomerService
				.queryById(customer);
		System.out.println(customerList.getPro_id());
		List loglist = logInfoService.queryByProId(customerList.getPro_id());
		List list = pubCustomerService.queryProNameById(obj);
		context = "修改了客户" + customerList.getCustomer_name() + "的信息";
		OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
		model.addAttribute("customerlist", customerList);
		model.addAttribute("loglist", loglist);
		model.addAttribute("prolist", list);

		return "customerinformation/pubCustomerEdit";
	}

	/**
	 * 修改
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/updateById", method = RequestMethod.POST)
	public Object updateById(CustomerInfo customer, CustomerProJInfo obj,
			Model model, HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String cId = request.getParameter("cId");
		// 主推项目id
		String proid = request.getParameter("proid");
		// 主推项目名称
		String proName = request.getParameter("proName");

		if (StringUtil.isNotNullOrBlank(cId)) {
			// 先把所有的项目状态改为N
			pubCustomerService.updateTypeById(customer.getCustomer_id());
			// 然后将当前的项目状态改为Y
			obj.setC_id(cId);
			pubCustomerService.updateCusProInfo(obj);

			// 将列表显示的项目名修改为当前项目
			customer.setPro_id(proid);
			customer.setPro_name(proName);
			customer.setAdscription(userinfo.getUser_name());
			customer.setLastUpdateUserName(userinfo.getUser_name());
			customer.setLastUpdateUserId(userinfo.getEmployee_id());
			System.out.println(customer.toString());
			this.pubCustomerService.modifyPub3(customer);
		} else {
			// 先删除当前客户所有的项目
			pubCustomerService.delete(customer.getCustomer_id());
			// 再将项目都新增进表中

			// 拿到前台传递的所有的项目id
			String[] pid = request.getParameter("strid").split(",");
			String[] strname = request.getParameter("strname").split(",");
			// 将pid放入list集合中
			List<String> pidlist = new ArrayList<String>();
			List<String> strnamelist = new ArrayList<String>();
			for (int i = 0; i < pid.length; i++) {
				pidlist.add(i, pid[i]);
				strnamelist.add(i, strname[i]);
			}
			for (int i = 0; i < pidlist.size(); i++) {
				obj.setPro_id(pidlist.get(i));
				obj.setCustomer_id(customer.getCustomer_id());
				obj.setPro_name(strnamelist.get(i));
				obj.setPro_type("N");
				obj.setStatus("Y");
				obj.setCreateUserId(userinfo.getEmployee_id());
				obj.setCreateUserName(userinfo.getUser_name());
				pubCustomerService.addCustomerProName(obj);
			}
			// 将主推状态改为Y
			obj.setPro_id(proid);
			obj.setCustomer_id(customer.getCustomer_id());
			pubCustomerService.updateStatus(obj);
			// 将列表显示的项目名修改为当前项目
			customer.setPro_id(proid);
			customer.setPro_name(proName);
			customer.setAdscription(userinfo.getUser_name());
			customer.setLastUpdateUserName(userinfo.getUser_name());
			customer.setLastUpdateUserId(userinfo.getEmployee_id());
			this.pubCustomerService.modifyPub3(customer);
		}

		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("code", "ok");
		return map;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("upload")
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List list = new ArrayList();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			Map map1 = new HashMap<String, String>();
			String str = it.next();
			String remark = multipartRequest.getParameter(str + "Remark");
			String type = multipartRequest.getParameter(str + "Type");
			String filePath = FileUpload.uploadFile(map.get(str), request);

			map1.put("filePath", filePath);
			map1.put("remark", remark);
			map1.put("type", type);
			logger.info("filePath:" + filePath + ",remark=" + remark);
			response.setContentType("text/html;charset=utf8");
			list.add(map1);
		}
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}

	/**
	 * 下载图片
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("download")
	public void download(String fileName, HttpServletResponse response)
			throws IOException {
		OutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			response.setContentType("image/jpeg; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 加载Windows open页面
	 * 
	 * @param obj
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/redirectProPan", method = RequestMethod.GET)
	public String redirectProPan(ProjectInfo obj, Model model) {
		logger.info("PubCustomerController.redirectProPan_obj");

		return "customerinformation/customerByPro";
	}

	/**
	 * 查询项目信息
	 * 
	 * @param obj
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectproname", method = RequestMethod.POST)
	public Object selectproname(ProjectInfo obj, Model model,
			HttpServletResponse response) {
		logger.info("CustomerInfoController.selectproname_obj="
				+ obj.toString());

		try {
			List list = projectInfo1Service.queryPro(obj);
			model.addAttribute("proList", list);
			logger.info("查询所有项目end");

			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 添加客户信息
	 * 
	 * @author Auser醴陵老九
	 * @throws Exception
	 */
	@RequestMapping(value = "addCustomer", method = RequestMethod.POST)
	public String addCustomer(CustomerInfo customerInfo,
			CustomerProJInfo customerProJInfo, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取登录人的信息
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		UserAdmin userMsg = (UserAdmin) this.userAdminService
				.queryById(userinfo);
		String context = "";

		customerInfo.setCreateUserId(userinfo.getEmployee_id());
		customerInfo.setCreateUserName(userMsg.getUser_name());
		customerInfo.setAdscription(userMsg.getUser_name());
		customerInfo.setAdscription_id(userinfo.getEmployee_id());
		customerInfo.setCustomer_type("3");

		this.pubCustomerService.addBatch(customerInfo);
		context = "新增了一个" + customerInfo.getCustomer_name() + "客户";
		OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

		// this.sendMsg(customer);
		ProjectInfo proInfo = new ProjectInfo();
		// 获得用户电话号码
		String mob = customerInfo.getPhone();
		// 获得项目ID
		String proId = customerInfo.getPro_id();

		proInfo.setPro_id(proId);
		// 根据项目Id查出来的项目信息
		ProjectInfo proJectInfo = (ProjectInfo) projectInfo1Service
				.queryById(proInfo);
		// 获取推荐人是谁
		String cname = request.getParameter("recommend_person");
		System.out.println(cname);
		customerInfo = new CustomerInfo();
		customerInfo.setCustomer_name(request.getParameter("recommend_person"));
		// //根据客户名称查询出推荐人数量
		// CustomerInfo customer
		// =pubCustomerService.queryRecommendCount(customerInfo);
		// System.out.println(customer);
		// if(customer==null){
		// System.out.println("aaa");
		// pubCustomerService.upRecommendCount1(customerInfo);
		// pubCustomerService.upRecommendCount(customerInfo);
		// }
		// 推荐人数加1
		pubCustomerService.upRecommendCount(customerInfo);

		// 添加项目到从表
		String[] proname = request.getParameter("submitParam").split("\\|");
		if (StringUtil.isNotNullOrBlank(proname)) {
			for (int i = 0; i < proname.length; i++) {
				if (i % 3 == 0 && i < proname.length - 1) {
					CustomerProJInfo cus = new CustomerProJInfo();
					cus.setPro_id(proname[i + 2]);
					cus.setPro_name(proname[i + 1]);
					cus.setPro_type(proname[i]);
					cus.setCustomer_id(customerInfo.getCustomer_id());
					cus.setCreateUserId(userinfo.getEmployee_id());
					cus.setCreateUserName(userinfo.getUser_name());
					cus.setStatus("Y");
					pubCustomerService.addCustomerProName(cus);
				}

			}
		}
		// 添加报备信息
		ReportedInfo reported = new ReportedInfo();
		reported.setT_id(proJectInfo.getPro_template_id());
		reported.setCustomer_id(customerInfo.getCustomer_id());
		reported.setRemark(customerInfo.getPhone());
		reported.setReported_yw(userinfo.getUser_name());
		reported.setReported_customer(customerInfo.getCustomer_name());
		reported.setPro_name(customerInfo.getPro_name());
		reported.setCreateUserId(userinfo.getEmployee_id());
		this.reportedInfo1Service.add(reported);

		return "redirect:findPub.do";

	}

	/**
	 * 添加客户跟进日志信息
	 * 
	 * @param LogInfo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addlog", method = RequestMethod.POST)
	public Object addlog(LogInfo loginfo, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		loginfo.setCreateUserId(userinfo.getEmployee_id());
		this.logInfoService.add(loginfo);
		Map map = new HashMap();
		map.put("logid", loginfo.getLog_id());
		return map;
	}

	/**
	 * 查询客户日志信息
	 * 
	 * @param LogInfo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectLog", method = RequestMethod.POST)
	public Map<String, List> selectLog(LogInfo obj, Model model,
			HttpServletRequest request) {
		String logid = request.getParameter("logId");
		if (StringUtil.isNotNullOrBlank(logid)) {
			int parseInt = Integer.parseInt(logid);
			obj.setLog_id(parseInt);
		}
		Map<String, List> map;
		try {
			map = new HashMap<String, List>();
			List list = logInfoService.queryLonInfo(obj);
			map.put("list", list);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询客户详情
	 * 
	 * @param customer
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCustomerMsg", method = RequestMethod.GET)
	public String queryMsg(CustomerInfo customer, CustomerProJInfo obj,
			Model model) throws Exception {
		CustomerInfo customerList = (CustomerInfo) this.customerInfoService
				.queryById1(customer);
		List loglist = logInfoService.queryByProId(customerList.getPro_id());
		List<CustomerProJInfo> list = customerInfoService
				.queryCustomerProj(customerList.getCustomer_id());
		// for (CustomerProJInfo customerProJInfo : list) {
		// System.out.println(customerProJInfo.toString());
		// }
		model.addAttribute("prolist", list);
		model.addAttribute("customerlist", customerList);
		model.addAttribute("loglist", loglist);
		return "customerinformation/customerMsg";
	}
}
