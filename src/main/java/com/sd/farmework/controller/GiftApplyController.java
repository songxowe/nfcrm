package com.sd.farmework.controller;

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

import com.sd.farmework.common.ApprovenUtil;
import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.GiftApply;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SysApprovenTask;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.CustomerListService;
import com.sd.farmework.service.GiftApplyService;
import com.sd.farmework.service.SysApprovenFunctionRuleService;
import com.sd.farmework.service.SysApprovenRulePersonService;
import com.sd.farmework.service.SysApprovenTaskService;
import com.sd.farmework.service.SysApprovenTaskedService;
import com.sd.farmework.service.SysLogInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/gift")
public class GiftApplyController {
	private static Logger logger = Logger.getLogger(GiftApplyController.class);
	@Autowired
	private GiftApplyService giftapplyService;
	@Autowired
	private CustomerListService customerinfoservice;
	@Autowired
	private SysApprovenRulePersonService ruleServices;
	@Autowired
	private SysApprovenTaskService sysApprovenTaskService;
	@Autowired
	private SysApprovenTaskedService sysApprovenTaskedService;
	@Autowired
	private SysApprovenFunctionRuleService sysApprovenFunctionRuleService;
	@Autowired
	private SysLogInfoService sysLogInfoService;

	private String approvenRuleId = "0000000002";

	@RequestMapping(value = "/addgiftMsg", method = RequestMethod.POST)
	public String addgiftMsg(GiftApply gift, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		gift.setCreateUserId(userinfo.getUser_id());
		gift.setCreateUserName(userinfo.getUser_name());
		this.giftapplyService.insertall(gift);

		// 审批开始
		// sys_approven_task
		SysApprovenTask sysTaskP = new SysApprovenTask();
		// 任务名称
		sysTaskP.setTaskName(gift.getCustomer_name() + "的礼品申请");
		sysTaskP.setApprovenResult(gift.getDescription());
		// 业务表名
		sysTaskP.setSourceTable("crm_gift_give");
		// 业务表主键名称
		sysTaskP.setSourceTablePkColumnName("gift_id");
		// 业务表主键对应值
		sysTaskP.setSourceTablePkColumnValue(StringUtil.fullNumberByLength(gift
				.getGift_id()));

		// 当前系统登录人编号
		sysTaskP.setCreateUserId(userinfo.getUser_id());
		// 当前系统登录人名称
		sysTaskP.setCreateUserName(userinfo.getUser_name());
		// 审批工具类
		ApprovenUtil appr = new ApprovenUtil();
		// 请假调休审批 0000000001 sys_approven_function_rule

		appr.addApprovenTask(approvenRuleId, sysTaskP, sysApprovenTaskService,
				ruleServices, sysApprovenFunctionRuleService);

		return "redirect:querymylist.do";
	}

	/**
	 * 4、待审批列表
	 * 
	 * @param obj
	 * @param model
	 * @return waitingTestApprovenFunctionList.do?approvenUserId=0000000004
	 * @throws Exception
	 */
	@RequestMapping(value = "/waitingApprovenList")
	public String waitingTestApprovenFunctionList(HttpServletRequest request,
			SysApprovenTask obj, Model model) throws Exception {
		// 通过审批人编号查询当前审批人的待办事项
		// approvenUserId =0000000004 系统当前登录用户sesssion获取
		//
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		obj.setApprovenUserId(userinfo.getUser_id());
		obj.setApprovenRuleId(approvenRuleId);

		obj.setTotalCount(sysApprovenTaskService.queryCount(obj));

		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);

		List<BaseInfo> list = sysApprovenTaskService.queryList(obj);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("testList", list);
		return "customerinformation/waitingApprovenList";
	}

	/**
	 * 打开审批操作界面
	 * 
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doApprovenShow", method = RequestMethod.GET)
	public String TestApproven(SysApprovenTask obj, Model model)
			throws Exception {

		SysApprovenTask objP = (SysApprovenTask) sysApprovenTaskService
				.query(obj);
		model.addAttribute("obj", objP);
		return "customerinformation/doApproven";
	}

	/**
	 * 5、执行审批操作
	 * 
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doApprovenSave", method = RequestMethod.POST)
	public String saveTestApproven(CustomerInfo customer, SysApprovenTask obj,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		// 审批功能编号
		try {
			ApprovenUtil appr = new ApprovenUtil();
			// 审核通过

			Map<String, Object> map = appr.doApproven(obj,
					sysApprovenTaskService, approvenRuleId);
			GiftApply giftApply = new GiftApply();
			giftApply.setGift_id(map.get("sourceId").toString());
			List<BaseInfo> sysApprovenTaskList = (List<BaseInfo>) map
					.get("sysApprovenTaskList");

			// 审核不通过
			if ("2".equals(obj.getApprovenResult())) {
				giftApply.setStep("3");
				giftapplyService.update(giftApply);
				context = "不通过礼品审核";
				OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

				for (int i = 0; i < sysApprovenTaskList.size(); i++) {
					sysApprovenTaskService.delete(sysApprovenTaskList.get(i));
				}
			} else {
				if (sysApprovenTaskList == null
						|| sysApprovenTaskList.size() == 0) {
					giftApply.setStep("2");
					giftapplyService.update(giftApply);
					context = "通过了礼品审核";
					OperationLogUtil.writeLog(sysLogInfoService, context,
							userinfo);
					// 写发送短信或者其他业务操作
					GiftApply giftdate = (GiftApply) this.giftapplyService
							.queryById(giftApply);
					customer.setCustomer_id(giftdate.getCustomer_id());
					customer = (CustomerInfo) this.customerinfoservice
							.queryById1(customer);
					String oldgift = customer.getGift_date();
					int giftnum = Integer.parseInt(customer.getGift_count());
					System.out.println("礼品数是" + giftnum);
					String newgift = giftdate.getGift();
					String gift = null;
					if (StringUtil.isNotNullOrBlank(oldgift)) {
						gift = oldgift + "," + newgift;
						giftnum += 1;
					} else {
						gift = newgift;
						giftnum = 1;
					}
					customer.setGift_count(giftnum + "");
					customer.setGift_date(gift);
					this.customerinfoservice.updategift(customer);
				}
			}
			response.getWriter().write("000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("001");
		}

		return null;
	}

	/**
	 * 6、已审批列表
	 * 
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/waitedApprovenList", method = RequestMethod.GET)
	public String waitedTestApprovenFunctionList(HttpServletRequest request,
			SysApprovenTask obj, Model model) throws Exception {
		// 通过审批人编号查询当前审批人的待办事项
		// 通过审批人编号查询当前审批人的待办事项
		// approvenUserId =0000000004 系统当前登录用户sesssion获取
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		obj.setApprovenUserId(userinfo.getUser_id());
		obj.setApprovenRuleId(approvenRuleId);
		List<BaseInfo> list = sysApprovenTaskedService.queryList(obj);
		obj.setTotalCount(sysApprovenTaskedService.queryCount(obj));

		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("testList", list);
		return "customerinformation/waitedApprovenList";
	}

	/**
	 * 7、查看当前审批任务进度
	 * 
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dataApprovenDetailList", method = RequestMethod.GET)
	public String dataApprovenDetailList(SysApprovenTask obj, Model model)
			throws Exception {
		// 通过审批人编号查询当前审批人的待办事项
		obj.setApprovenRuleId(approvenRuleId);
		List<BaseInfo> list = sysApprovenTaskService.queryAll(obj);
		System.out.println(list.toString());

		model.addAttribute("testList", list);
		return "customerinformation/dataApprovenDetailList";
	}

	@RequestMapping(value = "/querymylist")
	public String querymyList(GiftApply obj, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		obj.setCreateUserId(userinfo.getUser_id());
		obj.setTotalCount(giftapplyService.queryCount(obj));
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		List<BaseInfo> list = this.giftapplyService.queryList(obj);
		model.addAttribute("giftList", list);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		return "customerinformation/mygiftlist";
	}

	@RequestMapping(value = "/querymylistbyid", method = RequestMethod.GET)
	public String querymyListbyid(GiftApply obj, Model model,
			HttpServletRequest request) throws Exception {
		obj = (GiftApply) this.giftapplyService.queryById(obj);
		model.addAttribute("giftmsg", obj);
		return "customerinformation/EditGiftApply";
	}
}
