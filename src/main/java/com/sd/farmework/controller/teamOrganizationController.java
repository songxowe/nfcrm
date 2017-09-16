package com.sd.farmework.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.sd.farmework.common.TreeService;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.ApplicationConfig;
import com.sd.farmework.pojo.PowerMenu;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.SysFunctionInfoPower;
import com.sd.farmework.pojo.TeamOrganization;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.pojo.UserAndRoles;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.RoleService;
import com.sd.farmework.service.SysFunctionInfoPowerService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.UserAdminService;
import com.sd.farmework.service.UserAndRolesService;
import com.sd.farmework.service.UserPermissionService;
import com.sd.farmework.service.TeamOrganizationService;

/**
 * 团队管理
 * 
 * @author 秦波 2016-12-7
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/team")
public class teamOrganizationController {
	private static Logger logger = Logger
			.getLogger(teamOrganizationController.class);
	@Autowired
	private TeamOrganizationService teamOrganizationservice;
	@Autowired
	private SysLogInfoService sysLogInfoService;

	/**
	 * 查询所有团队
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAllteamList")
	public String queryAllteamList(TeamOrganization obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController queryAllPermList:start");
		obj.setParent_function_id("00000000001");
		obj.setTotalCount(teamOrganizationservice.queryCount(obj));
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
		List<BaseInfo> team = this.teamOrganizationservice.queryAllteam(obj);
		model.addAttribute("allteam", team);
		return "systemmanagement/teamOrganizationlist";
	}

	/**
	 * 查询一个团队里面的详细的分组
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryteamorganization")
	public String queryteamorganization(TeamOrganization obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController queryteamorganization:start");
		List<BaseInfo> team = this.teamOrganizationservice.queryAllteam(obj);
		String parent_function_id = request.getParameter("parent_function_id");
		obj.setTeam_id(parent_function_id);
		BaseInfo currentTeam = this.teamOrganizationservice.query(obj);
		model.addAttribute("teamdetail", team);
		model.addAttribute("currentTeam", currentTeam);
		return "systemmanagement/teamdetail";
	}

	/**
	 * 修改部门和分组信息
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateteam")
	public void updateteam(TeamOrganization obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		logger.info("RoleController updateteam:start");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		String name = request.getParameter("teamName");
		String desc = request.getParameter("teamDesc");
		String team_id = request.getParameter("teamId");
		String teamStatus = request.getParameter("teamStatus");
		String teamName[] = name.split("##");
		String teamDesc[] = desc.split("##");
		String teamId[] = team_id.split("##");
		String teamstep[] = teamStatus.split("##");
		try {
			for (int i = 0; i < teamName.length; i++) {
				obj.setTeam_name(teamName[i]);
				obj.setTeam_desc(teamDesc[i]);
				obj.setTeam_id(teamId[i]);
				obj.setStatus(teamstep[i]);
				this.teamOrganizationservice.updateteam(obj);
				context = "修改了组织架构" + obj.getTeam_name() + "的信息";
				OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
			}
		} catch (Exception e) {
			logger.info("缺少参数！！");
			e.printStackTrace();
		}
		response.getWriter().write("success");
	}
}
