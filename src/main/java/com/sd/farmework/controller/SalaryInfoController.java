package com.sd.farmework.controller;

import java.util.Date;
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

import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.pojo.EmployeeSalary;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.RolesalaryFormula;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.pojo.UserRole;
import com.sd.farmework.service.AttendDetailInfoService;
import com.sd.farmework.service.RoleService;
import com.sd.farmework.service.RolesalaryFormulaService;
import com.sd.farmework.service.SalaryService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.UserInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/salary")
public class SalaryInfoController {
	private static Logger logger = Logger.getLogger(SalaryInfoController.class);
	// 所有角色相关
	@Autowired
	private RoleService roleService;

	// 工资相关
	@Autowired
	private RolesalaryFormulaService rolesalaryFormulaService;
	@Autowired
	private SalaryService salaryService;

	// 日志相关
	@Autowired
	private SysLogInfoService sysLogInfoService;

	// 用户相关
	@Autowired
	private UserInfoService userInfoService;
	// 签到详情相关
	@Autowired
	private AttendDetailInfoService attendDetailInfoService;

	/**
	 * 查询所有角色
	 * 
	 * @author qushuai
	 * @param obj
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryAllRole")
	public String queryAllRole(RoleInfo roleInfo, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List rolelist = (List) this.roleService.queryAllList(roleInfo);
		roleInfo.setTotalCount(this.roleService.queryCount(roleInfo));
		model.addAttribute("totalCount", roleInfo.getPageCount());
		model.addAttribute("currPage", roleInfo.getCurrPage());
		model.addAttribute("rolelist", rolelist);
		model.addAttribute("roleInfo", roleInfo);

		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);

		return "salary/salaryrolelist";
	}

	/**
	 * 根据角色id查询工资信息
	 * 
	 * @author qushuai
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryroleSalaryById", method = RequestMethod.GET)
	public String querybyid(RolesalaryFormula salaryFormula, Model model)
			throws Exception {
		logger.info("RoleController roleSalary:start");
		// 根据role_id查询roleSalary_id
		RolesalaryFormula role = (RolesalaryFormula) rolesalaryFormulaService
				.queryById(salaryFormula);
		// 根据role_id查询角色信息
		RoleInfo roleinfo = (RoleInfo) roleService.queryById(salaryFormula);
		model.addAttribute("roleinfo", roleinfo);
		// 根据roleSalary_id查询角色工资详情
		RolesalaryFormula rolesalary = rolesalaryFormulaService.queryBySId(role
				.getRolesalary_id());
		model.addAttribute("role", rolesalary);
		return "salary/salaryroleMsg";
	}

	/**
	 * 编辑工资信息时的根据id查询
	 * 
	 * @author qushuai
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/roleSalaryUI")
	public String roleSalaryUI(RolesalaryFormula salaryFormula, Model model,
			HttpServletRequest request) throws Exception {
		logger.info("SalaryController roleSalary:start");
		// 根据role_id查询roleSalary_id
		RolesalaryFormula role = (RolesalaryFormula) rolesalaryFormulaService
				.queryById(salaryFormula);
		//
		if (StringUtil.isNotNullOrBlank(role)) {
			RoleInfo roleinfo = (RoleInfo) roleService.queryById(salaryFormula);
			model.addAttribute("roleinfo", roleinfo);
			RolesalaryFormula rolesalary = rolesalaryFormulaService
					.queryBySId(role.getRolesalary_id());
			model.addAttribute("role", rolesalary);
		} else {
			RoleInfo roleinfo = (RoleInfo) roleService.queryById(salaryFormula);
			model.addAttribute("roleinfo", roleinfo);
			RolesalaryFormula rolef = new RolesalaryFormula();
			model.addAttribute("role", rolef);
		}
		return "salary/salaryroleedit";

	}

	/**
	 * 修改角色工资信息(如果为空则是新增,如果不为空则为修改)
	 * 
	 * @author qushuai
	 * @param salaryFormula
	 * @param model
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateroleSalary", method = RequestMethod.POST)
	public void updateroleSalary(RolesalaryFormula salaryFormula, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		if (salaryFormula.getRolesalary_id() == "") {
			salaryFormula.setCreateUserId(userinfo.getUser_id());
			this.rolesalaryFormulaService.add(salaryFormula);
			// 日志
			context = "设置了" + salaryFormula.getRole_name() + "工资参数";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
		} else {
			salaryFormula.setCreateUserId(userinfo.getUser_id());
			this.rolesalaryFormulaService.update(salaryFormula);
			// 日志
			context = "修改了" + salaryFormula.getRole_name() + "工资参数";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
		}
	}

	/**
	 * 查询所有的用户和对应的角色
	 * 
	 * @author qushuai
	 * @param obj
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/userRoleList")
	public String userRoleList(UserRole userRole, Model model,
			HttpServletRequest request) throws Exception {
		// 日志
		logger.info("SalaryController userRoleList:start");

		// 查询所有的用户和对应的角色
		List rolelist = roleService.queryAllUserAndRole(userRole);
		// 设置总条数
		userRole.setTotalCount(roleService.queryCountByUserAndRole(userRole));
		// 保存值
		model.addAttribute("totalCount", userRole.getPageCount());
		model.addAttribute("currPage", userRole.getCurrPage());
		model.addAttribute("userRole", userRole);
		model.addAttribute("rolelist", rolelist);

		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		// 日志
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		return "salary/employeesalarylist";
	}

	/**
	 * 根据员工编辑工资详情页面
	 * 
	 * @author qushuai
	 * @param rol
	 * @param obj
	 * @param attend
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/employeeSalaryUI")
	public String employeeSalaryUI(UserRole rol, UserInfo userinfo,
			AttendDetailInfo attend, Model model, HttpServletRequest request)
			throws Exception {
		logger.info("SalaryController employeeSalaryUI:start");
		// 取出当天年月
		Date date = new Date();
		String time = DateUtil.getDateString(date, "yyyy-MM");
		// 获取当前用户信息
		UserInfo user = (UserInfo) userInfoService.queryById(userinfo);
		model.addAttribute("user", user);
		attend.setEmployee_no(user.getUser_no());
		attend.setSign_time(time);
		// 通过用户编号以及当前时间查询本月签到时间
		List attendlist = attendDetailInfoService.querySignTimeByNo(attend);
		model.addAttribute("attendlist", attendlist);

		// 计算迟到类型及次数
		int count1 = 0;// 迟到10min以内
		int count2 = 0;// 迟到10~60min以内
		int count3 = 0;// 迟到60min以上
		for (int i = 0; i < attendlist.size(); i++) {
			AttendDetailInfo temp = (AttendDetailInfo) attendlist.get(i);
			// 签到时间
			String startTime = temp.getSign_time();
			String tempDate = startTime.substring(0, 11);
			// 上班时间
			tempDate += "09:00:00";
			// 通过工具类计算时间差
			int num = Integer.parseInt(DateUtil.countDate(startTime, tempDate));
			if (num >= 1 && num < 10) {
				count1++;
			} else if (num >= 10 && num < 60) {
				count2++;
			} else {
				count3++;
			}
		}
		// 根据员工编号获取角色信息
		UserRole role = roleService.queryByRoNo(user.getUser_id());
		model.addAttribute("role", role);
		model.addAttribute("count1", count1);
		model.addAttribute("count2", count2);
		model.addAttribute("count3", count3);

		return "salary/employeesalaryroleedit";
	}

	/**
	 * 查询当月员工工资详情
	 * 
	 * @author qushuai
	 * @param salary
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/selectSalary")
	public String selectSalary(EmployeeSalary salary, Model model,
			HttpServletRequest request) throws Exception {
		// 日志
		logger.info("SalaryController selectSalary:start");
		// 取出当天年月
		Date date = new Date();
		String time = DateUtil.getDateString(date, "yyyy-MM");
		salary.setCreateTime(time);
		// 根据员工编号查询员工工资详情
		List list = salaryService.queryByUserNo(salary);
		model.addAttribute("salarylist", list);
		// 分页
		salary.setTotalCount(salaryService.queryCount(salary));
		model.addAttribute("totalCount", salary.getPageCount());
		model.addAttribute("currPage", salary.getCurrPage());
		model.addAttribute("salary", salary);

		return "salary/employeesalarydetail";
	}

	/**
	 * 获取编辑单个员工工资信息计算员工总工资
	 * 
	 * @author qushuai
	 * @param obj
	 * @param model
	 * @param salary
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value = "/employeeSalary")
	public Object employeeSalary(RolesalaryFormula obj, Model model,
			EmployeeSalary salary, HttpServletRequest request) throws Exception {
		// 日志
		logger.info("SalaryController employeeSalary:start");

		int integral = Integer.parseInt(request.getParameter("integral"));// 积分
		if (integral > 0 && integral <= 15) {
			integral = 50;
		} else {
			integral = 100;
		}
		int num = Integer.parseInt(request.getParameter("num"));// 带看组数
		int bill = Integer.parseInt(request.getParameter("bill"));// 开单
		int other = Integer.parseInt(request.getParameter("other"));// 其他
		int late = 0 - (Integer.parseInt(request.getParameter("late")));// 考勤
		int comm = 0;
		int numprice = 0;
		RolesalaryFormula rolesalary = salaryService.queryByRoId(obj);
		if (StringUtil.isNotNullOrBlank(rolesalary)) {
			// 从前台获取开单提成
			int first = Integer.parseInt(obj.getFirst_monad());
			int second = Integer.parseInt(obj.getSecond_monad());
			int third = Integer.parseInt(obj.getThird_monad());
			int fourth = Integer.parseInt(obj.getFourth_monad());
			int fifth = Integer.parseInt(obj.getFifth_monad());
			int sixth = Integer.parseInt(obj.getSixth_monad());
			int seventh = Integer.parseInt(obj.getSeventh_monad());
			int eighth = Integer.parseInt(obj.getEighth_monad());
			int ninth = Integer.parseInt(obj.getNinth_monad());
			int tenth = Integer.parseInt(obj.getTenth_monad());
			int eleventh = Integer.parseInt(obj.getEleventh_monad());
			int elevenmore = Integer.parseInt(obj.getEleven_more());
			// 设置开单提成
			int First = Integer.parseInt(rolesalary.getFirst_monad());
			int Second = Integer.parseInt(rolesalary.getSecond_monad());
			int Third = Integer.parseInt(rolesalary.getThird_monad());
			int Fourth = Integer.parseInt(rolesalary.getFourth_monad());
			int Fifth = Integer.parseInt(rolesalary.getFifth_monad());
			int Sixth = Integer.parseInt(rolesalary.getSixth_monad());
			int Seventh = Integer.parseInt(rolesalary.getSeventh_monad());
			int Eighth = Integer.parseInt(rolesalary.getEighth_monad());
			int Ninth = Integer.parseInt(rolesalary.getNinth_monad());
			int Tenth = Integer.parseInt(rolesalary.getTenth_monad());
			int Eleventh = Integer.parseInt(rolesalary.getEleventh_monad());
			int Elevenmore = Integer.parseInt(rolesalary.getEleven_more());
			// 开单提成
			comm = (first * First + second * Second + third * Third + fourth
					* Fourth + fifth * Fifth + sixth * Sixth + seventh
					* Seventh + eighth * Eighth + ninth * Ninth + tenth * Tenth
					+ eleventh * Eleventh + elevenmore * Elevenmore) * 100;

			// 带看数，计算带看数提成
			int looknum = Integer.parseInt(rolesalary.getTake_look_num());
			if (num - looknum >= 0) {
				numprice = 500;
			} else if (num - looknum < 0) {
				numprice = 500 - (looknum - num) * 100;
			}
			// 计算开单提成
			if (bill > 0 && bill <= 50) {
				bill = Integer.parseInt(rolesalary.getBill_award_first());
			} else if (bill > 50 && bill <= 100) {
				bill = Integer.parseInt(rolesalary.getBill_award_third());
			} else if (bill > 100 && bill <= 200) {
				bill = Integer.parseInt(rolesalary.getBill_award_second());
			} else if (bill > 200 && bill <= 300) {
				bill = Integer.parseInt(rolesalary.getBill_award_fourth());
			} else if (bill > 300 && bill <= 500) {
				bill = Integer.parseInt(rolesalary.getBill_award_fifth());
			} else {
				bill = Integer.parseInt(rolesalary.getBill_award_sixth());
			}

			// 设置员工工资详情
			salary.setBasic_salary(rolesalary.getBasic_salary());
			salary.setSubsidy(rolesalary.getSubsidy());
			salary.setCommission(String.valueOf(comm));
			salary.setIntegral_reward(String.valueOf(integral));
			salary.setTake_look(String.valueOf(numprice));
			salary.setBill_award(String.valueOf(bill));
		}
		salary.setStatus("Y");
		salary.setUser_id(request.getParameter("user_id"));
		salary.setUser_name(request.getParameter("user_name"));
		salary.setAttend(String.valueOf(late));
		salary.setOther(String.valueOf(other));
		int basicsalary = Integer.parseInt(rolesalary.getBasic_salary());
		int subsidy = Integer.parseInt(rolesalary.getSubsidy());
		// 计算工资总数
		int count = basicsalary + subsidy + numprice + late + other;
		salary.setCount(String.valueOf(count));
		// 获取当前用户信息
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 设置创建人
		salary.setCreateUserId(userinfo.getUser_id());
		// 添加员工工资信息
		salaryService.add(salary);
		Map map = new HashMap();
		map.put("code", "ok");
		// 向前台传送状态信息
		return map;
	}

}
