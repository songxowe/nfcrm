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
import com.sd.farmework.pojo.LeaveInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SysApprovenTask;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.LeaveInfoService;
import com.sd.farmework.service.SysApprovenFunctionRuleService;
import com.sd.farmework.service.SysApprovenRulePersonService;
import com.sd.farmework.service.SysApprovenTaskService;
import com.sd.farmework.service.SysApprovenTaskedService;
import com.sd.farmework.service.SysLogInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/leave")
public class LeaveController {
	private static Logger logger = Logger.getLogger(LeaveController.class);
	@Autowired
	private SysLogInfoService sysLogInfoService;
	@Autowired
	private LeaveInfoService leaveService;

	// 审批工具类ApprovenUtil要用到的service属性
	@Autowired
	private SysApprovenRulePersonService ruleServices;
	@Autowired
	private SysApprovenTaskService sysApprovenTaskService;
	@Autowired
	private SysApprovenTaskedService sysApprovenTaskedService;
	@Autowired
	private SysApprovenFunctionRuleService sysApprovenFunctionRuleService;

	// 审批规则表sys_approven_function_rule规则编号
	private String approvenRuleId = "0000000001";

	/**
	 * @author qushuai
	 * @param leave
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/leaveMsg", method = RequestMethod.GET)
	public String leaveMsg(LeaveInfo leave, Model model,
			HttpServletRequest request) throws Exception {
		// 从session中获取已登录用户
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 给实例化leave类赋值
		leave.setEmployee_no(userinfo.getUser_no());
		// 调用查询所有数据条数的方法设置leave继承baseinfo中总数据数值，为前台jqPaginator分页做准备
		leave.setTotalCount(this.leaveService.queryCount(leave));
		model.addAttribute("totalCount", leave.getPageCount());
		// 设置当前页数
		model.addAttribute("currPage", leave.getCurrPage());
		// 储存leave
		model.addAttribute("leave", leave);
		// 调用查询当前登录用户请教调休信息的方法
		List<BaseInfo> list = this.leaveService.queryList(leave);

		// 将查询的请假调休信息放进model传到前台
		model.addAttribute("leavelist", list);

		// 取出登录时的权限session
		// 通过权限确定前台显示if判断显示的数据
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);

		return "attendmanagement/leave_self";
	}

	/**
	 * @author qushuai
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/returntopage", method = RequestMethod.GET)
	public String returntopage(HttpServletRequest request, Model model)
			throws Exception {
		// 此方法只负责携带作用域进行跳转

		// 取出登录时的权限session
		// 通过权限确定前台显示if判断显示的数据
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);

		return "attendmanagement/leave";
	}

	/**
	 * @author qushuai
	 * @param leave
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addleaveMsg", method = RequestMethod.POST)
	public String addleaveMsg(LeaveInfo leave, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		leave.setCreateUserId(userinfo.getUser_id());
		leave.setCreateUserName(userinfo.getUser_name());
		this.leaveService.inserttoleave(leave);

		// 日志
		String context = "";
		String Ptype = null;
		if (StringUtil.isExisted(leave.getStatus(), "1")) {
			Ptype = "请假";
		} else if (StringUtil.isExisted(leave.getStatus(), "2")) {
			Ptype = "调休";
		}
		context = "申请了" + Ptype;
		OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

		// 审批流开始
		// 操作sys_approven_task表
		// 实例化审核信息类
		SysApprovenTask sysTaskP = new SysApprovenTask();
		// 任务名称
		sysTaskP.setTaskName(leave.getEmployee_name() + "请假申请");
		// 请假或者调休原因
		sysTaskP.setApprovenResult(leave.getLeave_reason());
		// 数据来源表
		sysTaskP.setSourceTable("crm_employee_leave");
		// 数据来源表的主键名
		sysTaskP.setSourceTablePkColumnName("leave_id");
		// 数据来源表的主键的值
		sysTaskP.setSourceTablePkColumnValue(StringUtil
				.fullNumberByLength(leave.getLeave_id()));
		// 当前登录人编号
		sysTaskP.setCreateUserId(userinfo.getUser_id());
		// 当前登录人名称
		sysTaskP.setCreateUserName(userinfo.getUser_name());

		// 实例化审批工具类
		ApprovenUtil appr = new ApprovenUtil();
		// 请假调休审批 0000000001 sys_approven_function_rule
		// 调用审批工具类中添加审批的方法
		appr.addApprovenTask(approvenRuleId, sysTaskP, sysApprovenTaskService,
				ruleServices, sysApprovenFunctionRuleService);

		return "redirect:leaveMsg.do";
	}

	/*
	 * libo 请假调休评审后记录
	 */
	@RequestMapping(value = "/waitedApprovenList", method = RequestMethod.GET)
	public String waitedTestApprovenFunctionList(HttpServletRequest request,
			SysApprovenTask obj, Model model) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		obj.setApprovenUserId(userinfo.getUser_id());
		obj.setApprovenRuleId(approvenRuleId);
		List<BaseInfo> list = sysApprovenTaskedService.queryList(obj);
		obj.setTotalCount(sysApprovenTaskedService.queryCount(obj));
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		model.addAttribute("testList", list);

		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		sessionRole.getCreateUserId();
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);

		return "attendmanagement/waitedApprovenList";

	}

	/*
	 * libo 审批操作界面
	 */
	@RequestMapping(value = "/doApprovenShow", method = RequestMethod.GET)
	public String TestApproven(SysApprovenTask obj, Model model)
			throws Exception {
		SysApprovenTask objP = (SysApprovenTask) sysApprovenTaskService
				.query(obj);
		System.out.println("审批操作界面" + obj.toString());
		model.addAttribute("obj", objP);
		return "attendmanagement/doApproven";
	}

	/*
	 * libo 审批
	 */
	@RequestMapping(value = "/doApprovenSave", method = RequestMethod.POST)
	public String doApprovenSave(SysApprovenTask obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		try {
			// 进行审批
			ApprovenUtil appr = new ApprovenUtil();
			Map<String, Object> map = appr.doApproven(obj,
					sysApprovenTaskService, approvenRuleId);

			LeaveInfo leaveInfo = new LeaveInfo();
			leaveInfo.setLeave_id(map.get("sourceId").toString());
			List<BaseInfo> sysApprovenTaskList = (List<BaseInfo>) map
					.get("sysApprovenTaskList");
			// 审核不通过
			for (int i = 0; i < sysApprovenTaskList.size(); i++) {
				System.out.println(sysApprovenTaskList.get(i));
			}
			if ("2".equals(obj.getApprovenResult())) {
				leaveInfo.setStep("3");
				leaveService.update(leaveInfo);
				context = "不通过" + userinfo.getUser_name() + "请假/调休";
				OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
				for (int i = 0; i < sysApprovenTaskList.size(); i++) {
					sysApprovenTaskService.delete(sysApprovenTaskList.get(i));
				}
			} else {
				// 审核通过
				if (sysApprovenTaskList == null
						|| sysApprovenTaskList.size() == 0) {
					leaveInfo.setStep("2");
					leaveService.update(leaveInfo);
					context = "通过" + userinfo.getUser_name() + "请假/调休";
					OperationLogUtil.writeLog(sysLogInfoService, context,
							userinfo);
				}
			}
			response.getWriter().write("000");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("001");
		}
		return "redirect:waitingApprovenList.do";
	}

	// liuli
	@RequestMapping(value = "/waitingApprovenList")
	public String waitingApprovenList(HttpServletRequest request,
			SysApprovenTask obj, Model model) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		obj.setApprovenUserId(userinfo.getUser_id());
		obj.setApprovenRuleId(approvenRuleId);
		List<BaseInfo> list = sysApprovenTaskService.queryList(obj);
		obj.setTotalCount(sysApprovenTaskService.queryCount(obj));
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		model.addAttribute("testList", list);

		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);

		return "attendmanagement/waitingApprovenList";
	}

	/**
	 * 7、查看当前审批任务进度
	 * 
	 * @param obj
	 * @author 秦波
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dataApprovenDetailList", method = RequestMethod.GET)
	public String dataApprovenDetailList(SysApprovenTask obj, Model model)
			throws Exception {
		// 通过审批人编号查询当前审批人的待办事项
		obj.setApprovenRuleId(approvenRuleId);
		List<BaseInfo> list = sysApprovenTaskService.queryAll(obj);

		model.addAttribute("testList", list);
		return "attendmanagement/dataApprovenDetailList";
	}
}
