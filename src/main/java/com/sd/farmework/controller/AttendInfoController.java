package com.sd.farmework.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.pojo.AttendInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.AttendDetailInfoService;
import com.sd.farmework.service.AttendInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/attend")
public class AttendInfoController {
	private static Logger logger = Logger.getLogger(AttendInfoController.class);
	@Autowired
	private AttendInfoService attendInfoService;
	@Autowired
	private AttendDetailInfoService attendDetailInfoService;

	/**
	 * 签到
	 * */

	@RequestMapping(value = "/havesige", method = RequestMethod.GET)
	public void havacome(AttendInfo attend, AttendDetailInfo attenddetail,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获得登录账号信息
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 创建输出对象
		PrintWriter out = response.getWriter();
		Date date = new Date();
		// 将当前时间转成yyyy-MM-dd 格式
		String s = DateUtil.getDateString(date);
		// 设置主表打卡时间
		attend.setPunch_time(s);
		attend.setEmployee_id(userinfo.getUser_id()); // 将用户ID给员工
		attend.setEmployee_name(userinfo.getUser_name());
		attend.setEmployee_no(userinfo.getUser_no()); // 将用户编号给员工编号

		attend.setCreateUserId(userinfo.getUser_id());
		attend.setCreateUserName(userinfo.getCreateUserName());

		// 设置子表打卡时间
		attenddetail.setSign_time(s);
		attenddetail.setEmployee_no(userinfo.getUser_no());
		attenddetail.setEmployee_name(userinfo.getUser_name());
		attenddetail.setStatus("Y");
		attenddetail.setEmployee_id(userinfo.getUser_id());

		attenddetail.setCreateUserId(userinfo.getUser_id());
		attenddetail.setCreateUserName(userinfo.getUser_name());
		// 获得当日打卡次数
		int number = attendInfoService.attendnumer(attenddetail);
		// 获得主表中当前用户的打卡次数
		int recount = attendInfoService.attendrecord(attend);
		System.out.println(number + "主表");
		System.out.println(recount);

		// 逻辑关系 解释 先判断 子表中 今天是否 如果有时间数据 提示 今天已经签到
		// 如果 没有数据 在判断主表中有没有数据 ，如果没有 ，则都进行插入输入
		// 否则 子表插入 主表更新 punchtime 属性

		int hours = date.getHours();

		if (number == 0) {
			if (hours < 9 || hours > 18) {
				out.write("overtime");
			} else {
				if (recount < 1) { // 如果子表和主表当天都没有插入过数据那么同时插入 数据
					attendInfoService.insert(attend);
					attendInfoService.inserttodetail(attenddetail);
				} else {// 主表中有数据的话只插入子表 因为到了第二天 子表number为0 主表里还有数据
					attendInfoService.inserttodetail(attenddetail);

					// 更新主表
					attendInfoService.updatepunchtime(attend);
				}
				out.write("success");
			}
		} else {
			out.write("fail");
		}

	}

	/**
	 * 
	 * 签退
	 * 
	 * */

	@RequestMapping(value = "/updatetime", method = RequestMethod.GET)
	public void updatetime(AttendDetailInfo attenddetail, AttendInfo attend,
			Model model, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser"); // 获得登录信息
		PrintWriter out = response.getWriter();
		attend.setEmployee_no(userinfo.getUser_no());
		attenddetail.setEmployee_no(userinfo.getUser_no()); // 设置子表编号

		attenddetail = (AttendDetailInfo) attendInfoService
				.querytorequest(attenddetail); // 通过子表编号获得该编号当天是否打卡

		System.out.println(attenddetail);

		Date date = new Date();
		int hours = date.getHours(); // 后面在优化下代码
		// 逻辑关系， 先判断子表中有没有今天的数据，如果没有就提示 还没有打卡， 有过有了，在判断该条数据中
		// 签退时间sign_back_time是否为空
		// 如果不为空就提示已经签退了， 如果为空 就判断当前时间是否在正常下班时间内， 如果在 就签退成功，且同时更新主表和子表，
		// 否则提示还没到下班时间
		System.out.println(hours);
		System.out.println(attenddetail);
		if (StringUtil.isNotNullOrBlank(attenddetail)) { // 如果子表今天 不为空
			String sign_back_time = attenddetail.getSign_back_time();// 获取打卡时间
			if (!StringUtil.isNotNullOrBlank(sign_back_time)) {
				if (hours >= 9 && hours < 18) {
					out.write("timenotout");
				} else {
					// 给子表数据新增一条离开时间
					attendInfoService.update(attenddetail);
					// 更新主表的打卡时间
					attendInfoService.updatepunchtime(attend);
					out.write("sign_back_success");
				}
			} else {
				out.write("repeat");
			}
		} else {
			out.write("no_sign");
		}
	}

	/**
	 * fanlifeng 当日考勤 查询
	 * 
	 * */
	@RequestMapping(value = "/findattend")
	public String findattend(AttendInfo attend, Model model,
			HttpServletRequest request) throws Exception {
		attend.setTotalCount(attendInfoService.queryatttendcount(attend)); // 设置总页数
		model.addAttribute("totalCount", attend.getPageCount()); // 存入分页 页面总数
		model.addAttribute("currPage", attend.getCurrPage()); // 从第几页开始
		model.addAttribute("obj", attend);
		List<BaseInfo> list = attendInfoService.queryall(attend); // 查询所有数据

		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		// 获得传过来的菜单ID
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("allAttendInfoMsg", list);

		return "attendmanagement/attendance";
	}

	/**
	 * 查询单个员工考勤详情
	 * */
	@RequestMapping(value = "/findattenddetail", method = RequestMethod.GET)
	public String findattenddetail(AttendDetailInfo attenddetail, Model model,
			HttpServletRequest request) throws Exception {
		attenddetail.setTotalCount(attendInfoService
				.selectdetailcount(attenddetail)); // 设置分页总数

		model.addAttribute("totalCount", attenddetail.getPageCount()); // 保存分页总数
		model.addAttribute("currPage", attenddetail.getCurrPage()); // 保存默认从第1页开始
		model.addAttribute("obj", attenddetail);
		List<BaseInfo> list = attendInfoService.queryallById(attenddetail); // 获取查询结果集
		model.addAttribute("AttendMsgdetail", list); // 存入分页对象
		System.out.println(list);
		return "attendmanagement/attendance_detail";
	}

	/**
	 * 查询当日考勤
	 * 
	 * @param attend
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectDayAttend")
	public String selectDayAttend(AttendInfo obj, HttpServletRequest request,
			Model model) throws Exception {
		logger.info("AttendInfoController selectDayAttend:start");
		logger.info("分页查询开始");
		// 获得当前时间 设置到打卡时间里
		Date date = new Date();
		String time = DateUtil.getDateString(date, "yyyy-MM-dd");
		obj.setPunch_time(time);
		// 获取出勤员工的信息和分页信息
		List<BaseInfo> dayattend = attendInfoService.selectDayAttend(obj);
		logger.info("分页查询结束");
		model.addAttribute("dayattend", dayattend);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		obj.setTotalCount(attendInfoService.queryCount(obj));
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		return "attendmanagement/attendday";
	}

	/**
	 * 查询当月考勤
	 * */
	@RequestMapping(value = "/selectMonthAttend")
	public String selectMonthAttend(AttendDetailInfo attendobj, Model model,
			HttpServletRequest request) throws Exception {
		logger.info("AttendInfoController selectMonthAttend:start");
		logger.info("分页查询开始");
		Date date = new Date();
		// 设置当前月
		String time = DateUtil.getDateString(date, "yyyy-MM");
		attendobj.setSign_time(time);
		List<BaseInfo> dayattend = attendDetailInfoService
				.selectMonthAttend(attendobj); // 查询正常打卡天数和异常打卡天数
		// System.out.println(dayattend);
		logger.info("分页查询结束");
		model.addAttribute("dayattend", dayattend);
		attendobj.setTotalCount(attendDetailInfoService.queryCount(attendobj)); // 设置分页总数
		model.addAttribute("totalCount", attendobj.getPageCount()); // 设置每页显示数量
		model.addAttribute("currPage", attendobj.getCurrPage()); // 从第几页开始
		model.addAttribute("obj", attendobj);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		return "attendmanagement/attendmonth";

	}

	/**
	 * 根据员工查询当月考勤详情
	 * 
	 * */
	@RequestMapping(value = "/selectMonthAttendDetail", method = RequestMethod.GET)
	public String selectMonthAttendDetail(AttendDetailInfo obj, Model model)
			throws Exception {
		logger.info("AttendInfoController selectMonthAttendDetail:start");
		logger.info("分页查询开始");
		Date date = new Date();
		String time = DateUtil.getDateString(date, "yyyy-MM");
		obj.setSign_time(time);

		List<BaseInfo> dayattend = attendDetailInfoService
				.selectMonthAttendDetail(obj);

		logger.info("分页查询结束");

		model.addAttribute("dayattend", dayattend);
		obj.setTotalCount(attendDetailInfoService.queryCountDetail(obj));// 设置总数
		model.addAttribute("totalCount", obj.getPageCount());// 设置分页总数
		model.addAttribute("currPage", obj.getCurrPage());// 从第几页开始
		model.addAttribute("obj", obj);
		return "attendmanagement/attendmonthdetail";
	}

	/**
	 * 查询当日未出勤员工
	 * 
	 * @param obj
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectNoDayAttend", method = RequestMethod.GET)
	public String SelectNoDayAttend(AttendInfo obj, HttpServletRequest request,
			Model model) throws Exception {
		logger.info("AttendInfoController selectNoDayAttend:start");
		logger.info("分页查询开始");
		Date date = new Date();
		String time = DateUtil.getDateString(date, "yyyy-MM-dd");
		obj.setPunch_time(time);
		// 获取未出勤员工的信息和分页信息
		List<BaseInfo> nodayattend = attendInfoService.selectNoDayAttend(obj);
		logger.info("分页查询结束");
		model.addAttribute("dayattend", nodayattend);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		obj.setTotalCount(attendInfoService.queryNoCount(obj));
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		return "attendmanagement/attendance_NoAttDay";

	}
}
