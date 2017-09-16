package com.sd.farmework.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.sd.farmework.common.SendSMSYes;
import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.ApplicationConfig;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.pojo.DateDictionaryInfo;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.LogInfo;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.pojo.ReportedInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.TeamOrganization;
import com.sd.farmework.pojo.TemplateContentInfo;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.CustomerListService;
import com.sd.farmework.service.DateDictionaryInfoService;
import com.sd.farmework.service.LogInfoService;
import com.sd.farmework.service.PubCustomerService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.TeamOrganizationService;
import com.sd.farmework.service.TemplateInfoService;
import com.sd.farmework.service.UserAdminService;

@Controller
@Scope("prototype")
@RequestMapping("/customer")
public class CustomerListController {
	private static final Object Map = null;
	private static Logger logger = Logger
			.getLogger(CustomerListController.class);
	@Autowired
	private CustomerListService customerInfoService;
	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private DateDictionaryInfoService dictionaryService;
	@Autowired
	private PubCustomerService pubCustomerService;
	@Autowired
	private TeamOrganizationService teamService;
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private TemplateInfoService templateInfoService;
	@Autowired
	private DateDictionaryInfoService dateDictionaryInfoService;
	@Autowired
	private ApplicationConfig applicationConfig;
	@Autowired
	private SysLogInfoService sysLogInfoService;

	/**
	 * f分页检索以及模糊查询
	 * 
	 * @param obj
	 * @param model
	 * @param datadictionary
	 * @param loginfo
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCustomerInfo")
	public String queryCustomerInfo(CustomerInfo obj, Model model,
			DateDictionaryInfo datadictionary, LogInfo loginfo,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String tag = request.getParameter("tag");
		if (StringUtil.isExisted(tag, "0")) {
			obj.setCustomer_type("0");
		} else if (StringUtil.isExisted(tag, "3")) {
			obj.setCustomer_type("3");
		} else {
		}
		try {
			obj.setTotalCount(this.customerInfoService.queryCount2(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);
			List<BaseInfo> customerList = customerInfoService.queryList2(obj);
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
				obj = (CustomerInfo) customerList.get(i);
				// System.out.println(obj.toString());
				log.setPro_id(obj.getPro_id());
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
					} else if (obj.getCustomer_type().equals("2")) {
						leftover = "客户已成交请关注后续进展";
					} else {
						leftover = time + "天";
					}
				} else {
					leftover = "0天";
					if (leftover.equals("0天")
							& (obj.getCustomer_type().equals("0") | obj
									.getCustomer_type().equals("3"))) {
						logger.info("带看和私客倒计时过期系统自动执行转为公客");
						if (obj.getCustomer_type().equals("0")) {
							String context = "";
							context = "开发倒计时完成系统将客户" + obj.getCustomer_name()
									+ "带看状态转为了公客";
							OperationLogUtil.writeLog(sysLogInfoService,
									context, userinfo);
							obj.setCustomer_type("1");
							leftover = "开发倒计时过期带看自动转为公客";
							obj.setAdscription("");
							obj.setAdscription_id("");
							this.customerInfoService.upbatch(obj);
						} else {
							String context = "";
							context = "开发倒计时完成系统将客户" + obj.getCustomer_name()
									+ "私客状态转为了公客";
							OperationLogUtil.writeLog(sysLogInfoService,
									context, userinfo);
							obj.setCustomer_type("1");
							leftover = "开发倒计时过期私客自动转为公客";
							obj.setAdscription("");
							obj.setAdscription_id("");
							this.customerInfoService.upbatch(obj);
						}
					}
				}
				if (leftover.equals("0天")) {
					leftover = "开发倒计时完成请继续跟踪";
				}
				obj.setOpen_countdown(leftover);
				// System.out.println(leftover);
			}
			// 取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo) request.getSession()
					.getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息：" + sessionRole.toString() + "\t 功能id："
					+ function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
			model.addAttribute("customerList", customerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtil.isExisted(tag, "0")) {
			return "customerinformation/toseecustomlist";
		} else if (StringUtil.isExisted(tag, "3")) {
			return "customerinformation/privatecustomlist";
		} else {
			return "customerinformation/customerinfolist";
		}
	}

	/**
	 * 更新状态 修改客户类型
	 * 
	 * @throws Exception
	 * 
	 * @date 2016-11-17
	 * @Author 秦波
	 */
	@ResponseBody
	@RequestMapping(value = "/updatebatch1", method = RequestMethod.GET)
	public void updatebatch(CustomerInfo obj, HttpServletResponse response,
			HttpServletRequest request, Model model) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		UserAdmin userMsg = (UserAdmin) this.userAdminService
				.queryById(userinfo);
		PrintWriter out = response.getWriter();
		String tag = request.getParameter("tag");
		String param = request.getParameter("param");
		String[] rid = param.split("##");
		// 去重复
		List list = Arrays.asList(rid);
		Set set = new HashSet(list);
		String[] date = (String[]) set.toArray(new String[0]);
		String tosee = "";
		String histo = "";
		String publi = "";
		String priva = "";
		String cusId = "";
		String context = "";
		try {

			for (int i = 0; i < date.length; i++) {
				obj.setCustomer_id(date[i]);
				CustomerInfo c = this.customerInfoService.queryType(date[i]);
				obj.setCustomer_type(c.getCustomer_type());
				obj.setLastUpdateTime(c.getLastUpdateTime());
				obj.setLastUpdateUserId(c.getLastUpdateUserId());
				obj.setAdscription_id(c.getAdscription_id());
				obj.setAdscription(c.getAdscription());
				obj.setTransactions_date(c.getTransactions_date());
				if (StringUtil.isExisted(tag, "0")
						&& !c.getCustomer_type().equals("2")) {
					obj.setCustomer_type("0");
					obj.setLastUpdateTime(DateUtil.getDateString(new Date()));
					obj.setLastUpdateUserId(userinfo.getEmployee_id());
					obj.setAdscription_id(userinfo.getEmployee_id());
					obj.setAdscription(userMsg.getUser_name());
					if (StringUtil.isExisted(obj.getCustomer_type(), "0")) {
						tosee = "tosee";
						if (i != date.length - 1) {
							cusId += date[i] + ",";
						} else {
							cusId += date[i];
						}
						if (i == date.length - 1) {

							if (!"".equals(cusId)) {
								CustomerInfo customerInfo = new CustomerInfo();
								customerInfo.setOneString(cusId);
								List<CustomerInfo> customerList = customerInfoService
										.queryByManyCustomerId(customerInfo);
								for (int j = 0; j < customerList.size(); j++) {
									context = "将"
											+ customerList.get(i)
													.getCustomer_name()
											+ "转为带看客户";
									OperationLogUtil.writeLog(
											sysLogInfoService, context,
											userinfo);
								}
							}
						}
					}
				} else if (StringUtil.isExisted(tag, "1")
						&& !c.getCustomer_type().equals("2")) {
					obj.setCustomer_type("1");
					obj.setLastUpdateTime(DateUtil.getDateString(new Date()));
					obj.setLastUpdateUserId(userinfo.getEmployee_id());
					obj.setAdscription_id("");
					obj.setAdscription("");
					if (StringUtil.isExisted(obj.getCustomer_type(), "1")) {
						publi = "public";
						if (i != date.length - 1) {
							cusId += date[i] + ",";
						} else {
							cusId += date[i];
						}
						if (i == date.length - 1) {

							if (!"".equals(cusId)) {
								CustomerInfo customerInfo = new CustomerInfo();
								customerInfo.setOneString(cusId);
								List<CustomerInfo> customerList = customerInfoService
										.queryByManyCustomerId(customerInfo);
								for (int j = 0; j < customerList.size(); j++) {
									context = "将"
											+ customerList.get(i)
													.getCustomer_name()
											+ "转为公共客户";
									OperationLogUtil.writeLog(
											sysLogInfoService, context,
											userinfo);
								}
							}
						}
					}
				} else if (StringUtil.isExisted(tag, "2")
						&& !c.getCustomer_type().equals("2")) {
					obj.setCustomer_type("2");
					obj.setLastUpdateTime(DateUtil.getDateString(new Date()));
					obj.setLastUpdateUserId(userinfo.getEmployee_id());
					obj.setAdscription_id(userinfo.getEmployee_id());
					obj.setAdscription(userMsg.getUser_name());
					obj.setTransactions_date(DateUtil.getDateString(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					if (StringUtil.isExisted(obj.getCustomer_type(), "2")) {
						histo = "history";
						if (i != date.length - 1) {
							cusId += date[i] + ",";
						} else {
							cusId += date[i];
						}
						if (i == date.length - 1) {

							if (!"".equals(cusId)) {
								CustomerInfo customerInfo = new CustomerInfo();
								customerInfo.setOneString(cusId);
								List<CustomerInfo> customerList = customerInfoService
										.queryByManyCustomerId(customerInfo);
								for (int j = 0; j < customerList.size(); j++) {
									context = "将"
											+ customerList.get(i)
													.getCustomer_name()
											+ "转为成交客户";
									OperationLogUtil.writeLog(
											sysLogInfoService, context,
											userinfo);
								}
							}
						}
					}
				} else if (StringUtil.isExisted(tag, "3")
						&& !c.getCustomer_type().equals("2")) {
					obj.setCustomer_type("3");
					obj.setLastUpdateTime(DateUtil.getDateString(new Date()));
					obj.setLastUpdateUserId(userinfo.getEmployee_id());
					obj.setAdscription_id(userinfo.getEmployee_id());
					obj.setAdscription(userMsg.getUser_name());
					if (StringUtil.isExisted(obj.getCustomer_type(), "3")) {
						priva = "private";
						if (i != date.length - 1) {
							cusId += date[i] + ",";
						} else {
							cusId += date[i];
						}
						if (i == date.length - 1) {

							if (!"".equals(cusId)) {
								CustomerInfo customerInfo = new CustomerInfo();
								customerInfo.setOneString(cusId);
								List<CustomerInfo> customerList = customerInfoService
										.queryByManyCustomerId(customerInfo);
								for (int j = 0; j < customerList.size(); j++) {

									context = "将"
											+ customerList.get(i)
													.getCustomer_name()
											+ "转为私有客户";
									OperationLogUtil.writeLog(
											sysLogInfoService, context,
											userinfo);

								}
							}
						}
					}
				}
				this.customerInfoService.upbatch(obj);
			}
			String newstr = tosee + histo + publi + priva;
			String returestr = newstr.substring(0, 5);
			System.out.println(returestr);
			out.write(returestr);
		} catch (Exception e) {
			System.out.println("修改客户类型失败!");
			e.printStackTrace();
			out.write("fail");
		}
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

	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public void report(HttpServletRequest request, CustomerInfo customer,
			HttpServletResponse response, Model model) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			boolean sendMsg = false;
			if (StringUtil.isNotNullOrBlank(request.getParameter("param"))) {
				// 找到用户ID
				String custId = request.getParameter("param");

				String[] custIdArray = custId.split(",");
				if (custIdArray.length == 1) {
					CustomerInfo customerInfo = new CustomerInfo();
					customerInfo.setCustomer_id(custIdArray[0]);
					sendMsg = this.sendMsg(customerInfo, userinfo);
					if (sendMsg) {
						out.write("t");
					} else {
						out.write("f");
					}
				} else {
					List<Boolean> sendResultList = new ArrayList<Boolean>();
					for (int i = 0; i < custIdArray.length; i++) {
						System.out.println(custIdArray[i]);
						CustomerInfo customerInfo = new CustomerInfo();
						customerInfo.setCustomer_id(custIdArray[i]);
						sendMsg = this.sendMsg(customerInfo, userinfo);
						sendResultList.add(sendMsg);
					}
					// 如果有一个以上的号码报备失败,则给提示报备失败
					boolean f = true;
					for (int i = 0; i < sendResultList.size(); i++) {
						if (!sendResultList.get(i)) {
							f = false;
						}
					}
					// 至少有一个报备失败
					if (f) {
						out.write("t");
					} else {
						out.write("ff");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("fail");
			e.printStackTrace();
		} finally {

			out.close();
		}

	}

	public boolean sendMsg(CustomerInfo customerInfo, UserInfo userinfo)
			throws Exception {
		System.out.println("发送信息方法");

		// 用户的ID
		CustomerInfo customInfoquerybyId = customerInfoService
				.queryById1(customerInfo);
		// 用户的电话号码

		String mob = "";
		if (customInfoquerybyId.getPhone() != null) {
			mob = customInfoquerybyId.getPhone();
		} else {
			// 客户电话为空
			return false;
		}

		ProjectInfo proInfo = new ProjectInfo();

		String proId = "";
		// 项目的ID
		if (StringUtil.isNotNullOrBlank(customInfoquerybyId.getPro_id())) {
			proId = customInfoquerybyId.getPro_id();
			proInfo.setPro_id(proId);
		} else {
			// 项目ID为空，该客户没有主推项目
			return false;
		}

		// 根据项目Id查出来的项目信息
		ProjectInfo proJectInfo = (ProjectInfo) this.customerInfoService
				.queryById2(proInfo);
		if (proJectInfo == null) {
			return false;
		}
		// 查询模板
		TemplateContentInfo templateContentInfo = new TemplateContentInfo();

		templateContentInfo.setT_id(proJectInfo.getPro_template_id());
		// 模板信息
		List<TemplateContentInfo> templateContentInfoList = templateInfoService
				.queryContentById(templateContentInfo);

		// 发送信息的数据
		String a = "";
		if (templateContentInfoList != null
				&& templateContentInfoList.size() != 0) {

			for (int j = 0; j < templateContentInfoList.size(); j++) {

				String key_name = templateContentInfoList.get(j).getKey_name();
				String value = templateContentInfoList.get(j).getValue();
				// keyname 对应的ID
				String key_source_id = templateContentInfoList.get(j)
						.getKey_source_id();
				// value 对应的ID
				String value_source_id = templateContentInfoList.get(j)
						.getValue_source_id();

				// 不为空就查字典表中对的字段
				if (StringUtil.isNotNullOrBlank(key_source_id)) {
					// 用in 就可以判断是否有多条信息
					DateDictionaryInfo dateDictionaryInfo = new DateDictionaryInfo();
					dateDictionaryInfo.setD_id_string(key_source_id);
					// 通过sourceid 查字典表
					// 查询得到的字典表中的信息
					List<DateDictionaryInfo> queryByModelInfo = dateDictionaryInfoService
							.queryByModelInfo(dateDictionaryInfo);
					// 查询 通过模板的信息查字典表中的数据
					if (queryByModelInfo != null
							&& queryByModelInfo.size() != 0) {
						for (int i = 0; i < queryByModelInfo.size(); i++) {
							String[] split = null;
							split = key_name.split(",");
							String function_type = queryByModelInfo.get(i)
									.getFunction_type();
							// 字典表中function_tupe 1表示模板信息-客户
							if (function_type.equals("1")) {
								// 表示要查 模板信息- 客户中的表
								String d_value = queryByModelInfo.get(i)
										.getD_value();
								customerInfo.setOneString(d_value);
								// 通过拼接sql 语句查询出来的值
								String queryByOneString = customerInfoService
										.queryByOneString(customerInfo);
								// 表示以逗号分割
								if (i < queryByModelInfo.size() - 1) {
									a += split[i] + ":" + queryByOneString
											+ ",";
								} else {
									a += split[i] + ":" + queryByOneString
											+ ".";
								}
								// 表示 模板信息-员工
							} else if (function_type.equals("2")) {
								// 表示 模板信息-员工
								String d_value = queryByModelInfo.get(i)
										.getD_value();
								String createUserId = customInfoquerybyId
										.getCreateUserId();

								EmployeeInfo employeeInfo = new EmployeeInfo();
								employeeInfo.setLevel_id(createUserId);

								employeeInfo.setOneString(d_value);
								// 通过拼接sql 语句查询出来的值
								String queryByOneStringEmpl = this.customerInfoService
										.queryByOneString2(employeeInfo);
								// 表示以逗号分割
								if (i < queryByModelInfo.size() - 1) {
									a += split[i] + ":" + queryByOneStringEmpl
											+ ",";
								} else {
									a += split[i] + ":" + queryByOneStringEmpl
											+ ".";
								}
								// 表示 模板信息-项目
							} else if (function_type.equals("3")) {
								// 表示 模板信息-项目
								String d_value = queryByModelInfo.get(i)
										.getD_value();
								ProjectInfo projectInfo = new ProjectInfo();
								projectInfo.setOneString(d_value);
								projectInfo.setPro_id(proId);
								String queryByOneString = this.customerInfoService
										.queryByOneString1(projectInfo);
								if (i < queryByModelInfo.size() - 1) {
									a += split[i] + ":" + queryByOneString
											+ ",";
								} else {
									a += split[i] + ":" + queryByOneString
											+ ".";
								}
							}

						}
					}
				} else {
					// 表示source_id为空 第一条信息就是Keyname对应的值
					if (j < templateContentInfoList.size() - 1) {
						a += templateContentInfoList.get(j).getKey_name() + ",";
					} else {
						a += templateContentInfoList.get(j).getKey_name() + ",";
					}
				}
				if (StringUtil.isNotNullOrBlank(value_source_id)) {
					// 用in 就可以判断是否有多条信息
					DateDictionaryInfo dateDictionaryInfo = new DateDictionaryInfo();
					dateDictionaryInfo.setD_id_string(value_source_id);
					// 通过sourceid 查字典表
					// 查询得到的字典表中的信息
					List<DateDictionaryInfo> queryByModelInfo = dateDictionaryInfoService
							.queryByModelInfo(dateDictionaryInfo);

					// 查询 通过模板的信息查字典表中的数据
					if (queryByModelInfo != null
							&& queryByModelInfo.size() != 0) {
						for (int i = 0; i < queryByModelInfo.size(); i++) {
							String[] split = null;

							split = value.split(",");
							String function_type = queryByModelInfo.get(i)
									.getFunction_type();
							// 字典表中function_tupe 1表示模板信息-客户
							if (function_type.equals("1")) {
								// 表示要查 模板信息- 客户中的表
								String d_value = queryByModelInfo.get(i)
										.getD_value();
								customerInfo.setOneString(d_value);
								// 通过拼接sql 语句查询出来的值
								String queryByOneString = customerInfoService
										.queryByOneString(customerInfo);
								// 表示以逗号分割
								if (i < queryByModelInfo.size() - 1) {
									a += split[i] + ":" + queryByOneString
											+ ",";
								} else {
									a += split[i] + ":" + queryByOneString
											+ ".";
								}

								// 表示 模板信息-员工
							} else if (function_type.equals("2")) {
								// 表示 模板信息-员工
								String d_value = queryByModelInfo.get(i)
										.getD_value();
								String createUserId = customInfoquerybyId
										.getCreateUserId();

								EmployeeInfo employeeInfo = new EmployeeInfo();
								employeeInfo.setLevel_id(createUserId);

								employeeInfo.setOneString(d_value);
								// 通过拼接sql 语句查询出来的值
								String queryByOneStringEmpl = this.customerInfoService
										.queryByOneString2(employeeInfo);
								// 表示以逗号分割
								if (i < queryByModelInfo.size() - 1) {
									a += split[i] + ":" + queryByOneStringEmpl
											+ ",";
								} else {
									a += split[i] + ":" + queryByOneStringEmpl
											+ ".";
								}
								// 表示 模板信息-项目
							} else if (function_type.equals("3")) {
								// 表示 模板信息-项目
								String d_value = queryByModelInfo.get(i)
										.getD_value();
								ProjectInfo projectInfo = new ProjectInfo();
								projectInfo.setOneString(d_value);
								projectInfo.setPro_id(proId);
								String queryByOneString = this.customerInfoService
										.queryByOneString1(projectInfo);
								if (i < queryByModelInfo.size() - 1) {
									a += split[i] + ":" + queryByOneString
											+ ",";
								} else {
									a += split[i] + ":" + queryByOneString
											+ ".";
								}

							}
						}
					}
				} else {
					// 表示value_source_id为空 第一条信息就是key对应的值

					if (j < templateContentInfoList.size() - 1) {
						a += templateContentInfoList.get(j).getValue() + ",";
					} else {
						a += templateContentInfoList.get(j).getValue() + ",";
					}

				}
			}
		}

		a = a + "【凝丰房产】";
		System.out.println(a);

		boolean f = SendSMSYes.send(mob, a, applicationConfig);
		if (f) {
			System.out.println("发送成功！");
		} else {
			System.out.println("发送失败！");
		}
		// 添加报备信息
		ReportedInfo reported = new ReportedInfo();
		reported.setT_id(proJectInfo.getPro_template_id());
		reported.setCustomer_id(customerInfo.getCustomer_id());
		reported.setRemark(customInfoquerybyId.getPhone());
		reported.setReported_yw(userinfo.getUser_name());
		reported.setReported_customer(customInfoquerybyId.getCustomer_name());
		reported.setPro_name(customInfoquerybyId.getPro_name());
		reported.setCreateUserId(userinfo.getEmployee_id());
		this.customerInfoService.addReport(reported);

		return f;
	}

	/**
	 * 根据客户id查询客户信息跳转到礼物申请页面
	 * 
	 * @param customer
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCustomertoApplygift", method = RequestMethod.GET)
	public String querytogift(CustomerInfo customer, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		// 根据id查询用户信息
		CustomerInfo customerList = (CustomerInfo) this.customerInfoService
				.queryById1(customer);
		context = "对客户" + customerList.getCustomer_name() + "发起了一条礼品申请";
		OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

		model.addAttribute("customerlist", customerList);
		// 携带客户信息跳转到申请礼物页面
		return "customerinformation/giftApply";
	}

	/**
	 * 查询分组
	 * 
	 * @param obj
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectOrganization", method = RequestMethod.POST)
	public Map<String, Object> selectOrganization(TeamOrganization obj,
			Model model) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List list = teamService.queryOrganization(obj);
			map.put("list", list);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
