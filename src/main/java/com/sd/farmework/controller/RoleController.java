package com.sd.farmework.controller;

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
 * 新增角色
 * 
 * @author 王超超 2016-11-2
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class RoleController {
	private static Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private UserAndRolesService userAndRolesService;
	@Autowired
	private UserPermissionService userPermissionService;
	@Autowired
	private PowerService powerService;
	@Autowired
	private SysFunctionInfoPowerService sysFunctionInfoPowerService;
	@Autowired
	private TeamOrganizationService teamOrganizationService;
	@Autowired
	private ApplicationConfig applicationConfig;
	@Autowired
	private SysLogInfoService sysLogInfoService;

	/**
	 * 短信发送
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testSend", method = RequestMethod.GET)
	@ResponseBody
	public Object testSend(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response,
			String phone) {
		logger.info("RoleController-------applicationConfig="
				+ applicationConfig.toString());
		Map resultMap = new HashMap();
		SendSMSYes smsSender = new SendSMSYes();
		boolean result = smsSender.send(phone, "您的验证码是：9999【测试您的签名】",
				applicationConfig);
		if (result) {
			resultMap.put("code", "000");
			resultMap.put("msg", "发送成功");
		} else {
			resultMap.put("code", "001");
			resultMap.put("msg", "发送失败");
		}
		return resultMap;
	}

	/**
	 * 新增角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ResponseBody
	public Object addRole(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("RoleController addRole:start");
		Map menuMap = new HashMap();
		try {
			// 获取登录用户信息
			UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
					"loginUser");
			String context = "";
			obj.setCreateUserId(userinfo.getUser_id());
			obj.setCreateUserName(userinfo.getUser_name());
			obj.setLastUpdateUserId(userinfo.getUser_id());
			obj.setLastUpdateUserName(userinfo.getUser_name());
			logger.info("RoleInfo=" + obj.toString());
			roleService.add(obj);

			context = "新增了一个" + obj.getRole_name() + "角色";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

			menuMap.put("code", "000");
			menuMap.put("msg", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			menuMap.put("code", "001");
			menuMap.put("msg", "faild");
			return menuMap;
		}
		return menuMap;
	}

	/**
	 * 查询所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAllPermList")
	public String queryAllPermList(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController queryAllPermList:start");

		try {

			List permList = (List) roleService.queryAllList(obj);
			// model.addAttribute("allPermList",permList);
			// 取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo) request.getSession()
					.getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息：" + sessionRole.toString() + "\t 功能id："
					+ function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
			obj.setTotalCount(roleService.queryCount(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);
			model.addAttribute("allPermList", permList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "systemmanagement/rolelist";
	}

	/**
	 * 个人查询所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPermList", method = RequestMethod.GET)
	public String queryPermList(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController queryPermList:start");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		String user_id = request.getParameter("user_id");
		String flag = request.getParameter("flag");
		if (StringUtil.isNullOrBlank(user_id)) {
			throw new Exception("user_id is null");
		}
		if (StringUtil.isNullOrBlank(flag)) {
			throw new Exception("flag is null");
		}

		try {
			// 查询用户信息
			UserAdmin user = new UserAdmin();
			user.setUser_id(user_id);
			user = (UserAdmin) userAdminService.queryById(user);
			logger.info("查询用户对象返回信息user=" + user.toString());

			List permList = roleService.queryList(obj);
			model.addAttribute("permList", permList);
			model.addAttribute("user", user);

			UserAndRoles userRole = new UserAndRoles();
			userRole.setUser_id(user_id);
			List userRoleList = userAndRolesService.queryList(userRole);
			context = "修改了" + user.getUser_name() + "的权限信息";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

			List rspList = new ArrayList();
			if (userRoleList != null && userRoleList.size() > 0) {

				for (int i = 0; i < userRoleList.size(); i++) {
					UserAndRoles tmp = (UserAndRoles) userRoleList.get(i);
					for (int j = 0; j < permList.size(); j++) {
						RoleInfo temp = (RoleInfo) permList.get(j);
						if (tmp.getRole_id().equals(temp.getRole_id())) {
							if (rspList.contains(temp)) {// 存在对象，则替换对象
								rspList.remove(temp);
								temp.setIsChecked("checked='checked'");
								rspList.add(temp);
							} else {// 不存在，直接add
								temp.setIsChecked("checked='checked'");
								rspList.add(temp);
							}
						} else {
							if (!rspList.contains(temp)) {
								temp.setIsChecked("");
								rspList.add(temp);
							}
						}
					}
				}

			} else {
				for (int i = 0; i < permList.size(); i++) {
					RoleInfo temp = (RoleInfo) permList.get(i);
					temp.setIsChecked("");
					rspList.add(temp);
				}
			}

			model.addAttribute("rspList", rspList);
			if ("query".equals(flag)) {
				return "systemmanagement/roledetail";
			} else if ("update".equals(flag)) {
				return "systemmanagement/updatePermissions";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户添加角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Object addUserRole(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController addUserRole:start");
		String tempItems = request.getParameter("tempItems");
		String userid = request.getParameter("userid");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (StringUtil.isNullOrBlank(tempItems)
				&& StringUtil.isNullOrBlank(userid)) {
			throw new Exception("param is null");
		}

		try {
			logger.info("用户角色关系删除start");
			UserAndRoles input = new UserAndRoles();
			input.setUser_id(userid);
			userAndRolesService.delete(input);
			logger.info("用户角色关系删除end");

			logger.info("用户角色关系增加start");
			String[] str = tempItems.split(",");
			for (int i = 0; i < str.length; i++) {
				input = new UserAndRoles();
				input.setUser_id(userid);
				input.setRole_id(str[i]);
				input.setCreateUserId(userinfo.getUser_id());
				input.setCreateUserName(userinfo.getUser_name());
				input.setLastUpdateUserId(userinfo.getUser_id());
				input.setLastUpdateUserName(userinfo.getUser_name());
				userAndRolesService.add(input);
			}
			logger.info("用户角色关系增加end");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map resMap = new HashMap();
		resMap.put("code", "000");
		return resMap;
	}

	/**
	 * 编辑角色页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/redirectRolePanel", method = RequestMethod.GET)
	public String redirectRolePanel(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController redirectRolePanel:start");
		String role_id = request.getParameter("role_id");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (StringUtil.isNullOrBlank(role_id)) {
			throw new Exception("param is null");
		}

		try {
			obj.setCreateUserId(userinfo.getUser_id());
			obj.setCreateUserName(userinfo.getUser_name());
			RoleInfo rspObj = (RoleInfo) userPermissionService.queryById(obj);
			model.addAttribute("rspObj", rspObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "systemmanagement/updateRole";
	}

	/**
	 * 编辑角色-提交
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/submitUpdateRole", method = RequestMethod.POST)
	public Object submitUpdateRole(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController redirectRolePanel:start obj="
				+ obj.toString());
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		if (StringUtil.isNullOrBlank(obj)) {
			throw new Exception("param is null");
		}

		try {
			obj.setCreateUserId(userinfo.getUser_id());
			obj.setLastUpdateUserId(userinfo.getUser_id());
			obj.setLastUpdateUserName(userinfo.getUser_name());
			userPermissionService.update(obj);
			context = "修改了" + obj.getRole_name() + "的角色信息";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
			logger.info("角色信息变更完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map resMap = new HashMap();
		resMap.put("code", "000");
		return resMap;
	}

	/**
	 * 变更菜单信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMenuInfo", method = RequestMethod.GET)
	public String updateMenuInfo(PowerMenu obj, RoleInfo param, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("RoleController updateMenuInfo:start");
		Map<String, Object> menuMap = new HashMap<String, Object>();
		Map menu1 = new HashMap();
		try {
			obj.setParent_function_id("0");
			List<BaseInfo> menuList = powerService.queryList(obj);
			RoleInfo rspObj = (RoleInfo) userPermissionService.queryById(param);// 查询当前角色所拥有的菜单信息
			if (menuList == null && rspObj == null) {
				logger.info("没有相关记录信息");
				return null;
			}
			PowerMenu power = (PowerMenu) menuList.get(0);
			menuMap.put("id", power.getFunciton_id());
			menuMap.put("text", power.getFunction_name());
			List<Map<String, Object>> childListMap = new ArrayList<Map<String, Object>>();
			getChildNodes(childListMap, menuMap, rspObj);

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(menuMap);
			JSONArray js = JSONArray.fromObject(list);
			System.out.println(js.toString());
			response.getWriter().write(js.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private List<Map<String, Object>> getChildNodes(
			List<Map<String, Object>> childListMap,
			Map<String, Object> menuMap, RoleInfo obj) throws Exception {
		logger.info("RoleInfo1=" + obj.toString());
		PowerMenu powerPara = new PowerMenu();
		powerPara.setParent_function_id(String.valueOf(menuMap.get("id")));
		List<BaseInfo> childList = powerService.queryList(powerPara);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			PowerMenu function = (PowerMenu) childList.get(i);
			functionMap.put("id", function.getFunciton_id());
			functionMap.put("text", function.getFunction_name());
			list = getChildNodes1(childListMap, functionMap, obj);

			functionMap.put("children", list);
			childListMap.add(functionMap);
		}
		menuMap.put("children", childListMap);
		return childListMap;
	}

	private List<Map<String, Object>> getChildNodes1(
			List<Map<String, Object>> childListMap,
			Map<String, Object> menuMap, RoleInfo obj) throws Exception {
		logger.info("RoleInfo2=" + obj.toString());
		PowerMenu powerPara = new PowerMenu();
		powerPara.setParent_function_id(String.valueOf(menuMap.get("id")));
		List<BaseInfo> childList = powerService.queryList(powerPara);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			PowerMenu function = (PowerMenu) childList.get(i);
			functionMap.put("id", function.getFunciton_id());
			functionMap.put("text", function.getFunction_name());
			// 根据角色已有的菜单来控制是否选中复选框
			if (obj.getFunction_ids().indexOf(function.getFunciton_id()) > -1) {
				functionMap.put("checked", "checked");
			}
			list.add(functionMap);
		}
		return list;
	}

	/**
	 * 新增团队机构页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addTeamPanel", method = RequestMethod.GET)
	public String addTeamPanel(TeamOrganization obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController addTeamPanel:start");

		return "systemmanagement/teamOrganization";
	}

	/**
	 * 新增团队机构-提交
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitTeamPanel", method = RequestMethod.POST)
	@ResponseBody
	public Object submitTeamPanel(TeamOrganization obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController submitTeamPanel:start");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (userinfo == null) {
			return null;
		}
		if (StringUtil.isNullOrBlank(obj.getNode_level())) {
			return null;
		}
		if (obj.getNode_level().equals("2")) {
			return null;
		}

		try {
			obj.setNode_level(String.valueOf(Integer.parseInt(obj
					.getNode_level()) + 1));
			obj.setCreateUserId(userinfo.getUser_id());
			obj.setCreateUserName(userinfo.getUser_name());
			obj.setLastUpdateUserId(userinfo.getUser_id());
			obj.setLastUpdateUserName(userinfo.getUser_name());

			teamOrganizationService.add(obj);
			logger.info("机构新增成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map rspMap = new HashMap();
		rspMap.put("code", "000");
		return rspMap;
	}

	/**
	 * 查询团队机构
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTeamPanel", method = RequestMethod.GET)
	public String queryTeamPanel(TeamOrganization obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController queryTeamPanel:start");
		Map<String, Object> teamMap = new HashMap<String, Object>();
		try {
			obj.setParent_function_id("0");
			List<BaseInfo> teamList = teamOrganizationService.queryList(obj);
			if (teamList == null) {
				logger.info("没有相关记录信息");
				return null;
			}
			TeamOrganization teamObj = (TeamOrganization) teamList.get(0);
			teamMap.put("id", teamObj.getTeam_id());
			teamMap.put("text", teamObj.getTeam_name());
			teamMap.put("node_level", teamObj.getNode_level());
			List<Map<String, Object>> childListMap = new ArrayList<Map<String, Object>>();
			getTeamChildNodes(childListMap, teamMap);

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.add(teamMap);
			JSONArray js = JSONArray.fromObject(list);
			System.out.println(js.toString());

			response.getWriter().write(js.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private List<Map<String, Object>> getTeamChildNodes(
			List<Map<String, Object>> childListMap, Map<String, Object> teamMap)
			throws Exception {

		TeamOrganization teamPara = new TeamOrganization();
		teamPara.setParent_function_id(String.valueOf(teamMap.get("id")));
		List<BaseInfo> childList = teamOrganizationService.queryList(teamPara);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			TeamOrganization function = (TeamOrganization) childList.get(i);
			functionMap.put("id", function.getTeam_id());
			functionMap.put("text", function.getTeam_name());
			functionMap.put("node_level", function.getNode_level());
			list = getTeamChildNodes1(childListMap, functionMap);

			functionMap.put("children", list);
			childListMap.add(functionMap);

		}
		teamMap.put("children", childListMap);
		return childListMap;
	}

	/**
	 * 
	 * @param childListMap
	 * @param teamMap
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getTeamChildNodes1(
			List<Map<String, Object>> childListMap, Map<String, Object> teamMap)
			throws Exception {

		try {
			TeamOrganization teamPara = new TeamOrganization();
			teamPara.setParent_function_id(String.valueOf(teamMap.get("id")));
			List<BaseInfo> childList = teamOrganizationService
					.queryList(teamPara);

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < childList.size(); i++) {
				Map<String, Object> functionMap = new HashMap<String, Object>();
				TeamOrganization function = (TeamOrganization) childList.get(i);
				functionMap.put("id", function.getTeam_id());
				functionMap.put("text", function.getTeam_name());
				functionMap.put("node_level", function.getNode_level());
				// childListMap.add(functionMap);

				list.add(functionMap);

			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑操作按钮页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/redirectOperatePanel", method = RequestMethod.GET)
	public String redirectOperatePanel(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController redirectRolePanel:start");
		String role_id = request.getParameter("role_id");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		if (StringUtil.isNullOrBlank(role_id)) {
			throw new Exception("param is null");
		}

		try {
			RoleInfo rspObj = (RoleInfo) userPermissionService.queryById(obj);
			context = "修改了" + rspObj.getRole_name() + "操作权限";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
			model.addAttribute("rspObj", rspObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "systemmanagement/updateOperate";
	}

	/**
	 * 编辑操作按钮页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/submitOperateButton", method = RequestMethod.POST)
	public Object submitOperateButton(RoleInfo obj, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("RoleController submitOperateButton:start");
		String nodeIds = request.getParameter("nodeIds");
		String role_id = request.getParameter("role_id");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		if (StringUtil.isNullOrBlank(role_id)
				&& StringUtil.isNullOrBlank(nodeIds)) {
			throw new Exception("param is null");
		}
		try {
			obj.setRole_id(role_id);
			obj.setFunction_operate_ids(nodeIds);
			obj.setLastUpdateUserId(userinfo.getUser_id());
			obj.setLastUpdateUserName(userinfo.getUser_name());
			logger.info("新增操作按钮入参：" + obj.toString());
			roleService.update(obj);
			logger.info("新增操作按钮入参success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map rspMap = new HashMap();
		rspMap.put("code", "000");
		return rspMap;
	}

	/**
	 * 获取操作按钮信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateOperate", method = RequestMethod.GET)
	public String updateOperate(SysFunctionInfoPower obj, RoleInfo param,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("RoleController updateMenuInfo:start");
		try {
			List<BaseInfo> menuList = sysFunctionInfoPowerService
					.queryList(obj);
			RoleInfo rspObj = (RoleInfo) userPermissionService.queryById(param);// 查询当前角色所拥有的菜单信息
			if (menuList == null && rspObj == null) {
				logger.info("没有相关记录信息");
				return null;
			}

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			// 先取出根节点
			for (int i = 0; i < menuList.size(); i++) {
				SysFunctionInfoPower powerinfo = (SysFunctionInfoPower) menuList
						.get(i);
				if ("0".equals(powerinfo.getParentFunctionId())) {
					Map<String, Object> map = new HashMap();
					map.put("PARENT_ID", powerinfo.getParentFunctionId());
					map.put("ID", powerinfo.getFuncitonId());
					map.put("NAME", powerinfo.getFunctionName());
					list.add(map);
					break;
				}
			}
			logger.info("根节点信息:" + list.toString());
			// 取子菜单节点
			for (int i = 0; i < menuList.size(); i++) {
				SysFunctionInfoPower powerinfo = (SysFunctionInfoPower) menuList
						.get(i);
				if (rspObj.getFunction_ids().indexOf(powerinfo.getFuncitonId()) >= 0) {
					Map<String, Object> map = new HashMap();
					map.put("PARENT_ID", powerinfo.getParentFunctionId());
					map.put("ID", powerinfo.getFuncitonId());
					map.put("NAME", powerinfo.getFunctionName());
					if (!list.contains(map)) {
						list.add(map);
					}
				}
			}
			logger.info("子菜单信息:" + list.toString());

			// 取子菜单下的按钮
			List buttonlist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				for (int j = 0; j < menuList.size(); j++) {
					SysFunctionInfoPower powerinfo = (SysFunctionInfoPower) menuList
							.get(j);
					if (powerinfo.getFunctionDesc().indexOf("按钮") >= 0) {
						if (map.get("ID").equals(
								powerinfo.getParentFunctionId())) {
							buttonlist.add(powerinfo);
						}
					}
				}
			}
			logger.info("子菜单下按钮信息:" + buttonlist.toString());
			// 组合子菜单和按钮
			for (int i = 0; i < buttonlist.size(); i++) {
				SysFunctionInfoPower powerinfo = (SysFunctionInfoPower) buttonlist
						.get(i);
				Map<String, Object> map = new HashMap();
				map.put("PARENT_ID", powerinfo.getParentFunctionId());
				map.put("ID", powerinfo.getFuncitonId());
				map.put("NAME", powerinfo.getFunctionName());
				list.add(map);
			}

			logger.info("返回size：" + list.size());
			logger.info("查询权限按钮返回信息：" + list.toString());
			TreeService s = new TreeService();
			List<Map<String, Object>> result = s.getTreeData(list, "0", rspObj);
			logger.info("处理后的信息：" + result.toString());

			JSONArray json = JSONArray.fromObject(result);
			System.out.println(json.toString());
			logger.info("返回json：" + json.toString());
			response.getWriter().write(json.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	// functionMap.put("checked", "checked");

}
