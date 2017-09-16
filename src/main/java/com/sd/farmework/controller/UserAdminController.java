package com.sd.farmework.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.MD5Util;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SysLogInfo;
import com.sd.farmework.pojo.TeamOrganization;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.EmployeeInfoService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.TeamOrganizationService;
import com.sd.farmework.service.UserAdminService;

/**
 *    * 用户管理  * @author 作者 2016-11-16  
 */
@Controller
@RequestMapping(value = "/useradmin")
public class UserAdminController {
	private static Logger logger = Logger.getLogger(UserInfoController.class);
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private TeamOrganizationService teamservice;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private SysLogInfoService sysLogInfoService;

	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserList", method = RequestMethod.GET)
	public String queryUserList(UserAdmin user, TeamOrganization teamObj,
			Model model, HttpServletRequest request) throws Exception {

		List<BaseInfo> list = this.userAdminService.queryList(user);
		List<BaseInfo> teamList = teamservice.queryAllList(teamObj);

		List respList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			UserAdmin uObj = (UserAdmin) list.get(i);
			for (int j = 0; j < teamList.size(); j++) {
				TeamOrganization tObj = (TeamOrganization) teamList.get(j);
				if (uObj.getUser_group().equals(tObj.getTeam_id())) {
					uObj.setParentId(tObj.getParent_function_id());
					respList.add(uObj);
				}
			}
		}

		List retList = new ArrayList();
		for (int i = 0; i < respList.size(); i++) {
			UserAdmin uObj = (UserAdmin) respList.get(i);
			for (int j = 0; j < teamList.size(); j++) {
				TeamOrganization tObj = (TeamOrganization) teamList.get(j);
				if (uObj.getParentId().equals(tObj.getTeam_id())) {
					uObj.setParentName(tObj.getTeam_name());
					retList.add(uObj);
				}
			}
		}

		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);

		user.setTotalCount(userAdminService.queryCount(user));
		model.addAttribute("totalCount", user.getPageCount());
		model.addAttribute("currPage", user.getCurrPage());
		model.addAttribute("obj", user);
		model.addAttribute("userList", retList);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		return "systemmanagement/userlist";
	}

	/**
	 * 根据id查询
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserByid", method = RequestMethod.GET)
	public String queryById(UserAdmin user, Model model,
			HttpServletRequest request) throws Exception {
		UserAdmin userObj = (UserAdmin) this.userAdminService.queryById(user);
		TeamOrganization team = new TeamOrganization();
		team.setTeam_id(userObj.getUser_group());
		team = (TeamOrganization) this.teamservice.queryById(team);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("teamObj", team);
		model.addAttribute("oneofuserMsg", userObj);

		return "systemmanagement/updateUser";
	}

	/**
	 * 根据分组显示部门
	 * 
	 * @param user
	 * @param model
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/queryparent", method = RequestMethod.GET)
	public Object queryparent(TeamOrganization team, Model model)
			throws Exception {
		team = (TeamOrganization) this.teamservice.queryById(team);
		System.out.println(team);
		Map map = new HashMap();
		map.put("group", team);
		return map;
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public void updateUser(UserAdmin user, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		user.setLastUpdateUserId(userinfo.getUser_id());
		user.setLastUpdateUserName(userinfo.getUser_name());
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String context = "对" + user.getUser_name() + "员工进行信息修改";
		SysLogInfo sysLogInfo = new SysLogInfo();
		sysLogInfo.setOperate_person_id(sessionRole.getRole_id());
		sysLogInfo.setOperate_person_name(sessionRole.getRole_name());
		sysLogInfo.setFunction_name(context);
		sysLogInfoService.add(sysLogInfo);
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		this.userAdminService.update(user);
	}

	/**
	 * 删除
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(UserAdmin user, Model model,
			HttpServletRequest request) throws Exception {
		this.userAdminService.delete(user);
		return queryUserList(user, null, model, request);
	}

	/**
	 * 进入添加用户页面
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/addUserMsgUI", method = RequestMethod.GET)
	public String addUserMsgUI(UserAdmin user, EmployeeInfo obj, Model model)
			throws Exception {
		if (StringUtil.isNotNullOrBlank(obj.getLevel_id())) {
			EmployeeInfo list = (EmployeeInfo) employeeInfoService
					.queryById(obj);
			model.addAttribute("temp", list);
			return "systemmanagement/addUser";
		} else {
			EmployeeInfo list = new EmployeeInfo();
			model.addAttribute("temp", list);
			return "systemmanagement/addUser";
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param model
	 * @author 秦波
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addUserMsg", method = RequestMethod.POST)
	public void addUserMsg(UserAdmin user, Model model,
			HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		MD5Util getMD5 = new MD5Util();

		String level_id = StringUtil.prefixString(
				request.getParameter("level_id"), 11);
		user.setEmployee_id(level_id);
		user.setCreateUserId(userinfo.getUser_id());
		user.setCreateUserName(userinfo.getUser_name());
		user.setStatus("Y");
		String pwd = user.getUser_pwd();
		user.setUser_pwd(getMD5.GetMD5Code(pwd));
		this.userAdminService.add(user);
	}

	/**
	 * 查询部门
	 * 
	 * @param user
	 * @param model
	 * @author 秦波
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryteam", method = RequestMethod.GET)
	@ResponseBody
	public Object teamlist(TeamOrganization team, Model model,
			HttpServletRequest request) throws Exception {
		String tag = request.getParameter("tag");
		if (StringUtil.isNotNullOrBlank(tag)) {
			team.setParent_function_id(tag);
		} else {
			team.setParent_function_id("00000000001");
		}
		List<BaseInfo> allteam = this.teamservice.queryList(team);
		Map map = new HashMap();
		map.put("teamlist", allteam);
		return map;
	}

	/**
	 * 分页查询用户
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/queryUserInfo", method = RequestMethod.GET)
	public String queryUserInfo(UserAdmin obj, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("UserAdminController queryUserInfo:start");

		try {
			logger.info("分页查询开始");
			List userList = userAdminService.queryUserInfo(obj);
			logger.info("分页查询结束");
			List<BaseInfo> teamList = teamservice.queryAllList(null);

			List respList = new ArrayList();
			for (int i = 0; i < userList.size(); i++) {
				UserAdmin temp = (UserAdmin) userList.get(i);
				for (int j = 0; j < teamList.size(); j++) {
					TeamOrganization tObj = (TeamOrganization) teamList.get(j);
					if (temp.getUser_group().equals(tObj.getTeam_id())) {
						temp.setGroupName(tObj.getTeam_name());
						respList.add(temp);
					}
				}
			}
			logger.info("处理后的用户数据：" + respList.toString());
			// 取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo) request.getSession()
					.getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息：" + sessionRole.toString() + "\t 功能id："
					+ function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
			model.addAttribute("userList", respList);
			obj.setTotalCount(userAdminService.queryCount(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "systemmanagement/permissionslist";
	}

	/**
	 * 查询id和名称
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserIDAndName", method = RequestMethod.POST)
	public Object queryUserIDAndName(UserAdmin obj, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("UserAdminController queryUserIDAndName:start");
		try {
			HttpSession session = request.getSession();
			UserInfo user = (UserInfo) session.getAttribute("loginUser");
			obj.setUser_name(user.getUser_name());
			List list = userAdminService.queryUserIdAndName(obj);
			model.addAttribute("list", list);
			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 根据employeeid去查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserByEmployeeId", method = RequestMethod.POST)
	public Object queryUserByEmployeeId(UserAdmin obj, Model model,
			HttpServletRequest request) {
		Map map = new HashMap();
		logger.info("UserAdminController queryUserByEmployeeId:start");
		String employeeid = request.getParameter("id");
		obj.setEmployee_id(StringUtil.prefixString(employeeid, 11));
		UserAdmin user = userAdminService.queryByEmployeeId(obj
				.getEmployee_id());
		if (StringUtil.isNullOrBlank(user)) {
			map.put("code", "001");
		} else {
			map.put("code", "000");
		}

		return map;
	}
}
