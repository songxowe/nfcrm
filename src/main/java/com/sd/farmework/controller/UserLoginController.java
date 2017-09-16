package com.sd.farmework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.MD5Util;
import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.pojo.PowerMenu;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserAndRoles;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.UserAndRolesService;
import com.sd.farmework.service.UserInfoService;
import com.sd.farmework.service.UserPermissionService;

/**
 * 用户登录
 * 
 * @author 王超超 2016-10-26
 * 
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class UserLoginController {
	private static Logger logger = Logger.getLogger(UserLoginController.class);
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserAndRolesService userAndRolesService;
	@Autowired
	private UserPermissionService userPermissionService;
	@Autowired
	private PowerService powerService;

	/**
	 * 测试服务是否启动成功
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(Model model) {

		return "login";
	}

	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(HttpSession session, UserInfo user, Model model,
			HttpServletRequest request) {
		logger.info("UserInfoController queryUser:start");
		try {

			// /*校验图形验证码start*/
			// String code = (String) session.getAttribute("code");
			// String codeP=request.getParameter("code");
			// if(StringUtil.isNullOrBlank(codeP)||!codeP.equals(code)){
			//
			//
			// model.addAttribute("codeMsg", "验证码错误");
			//
			// return "login";
			// }
			/* 校验图形验证码end */

			UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
					"loginUser");
			Map sessionMap = (HashMap) request.getSession().getAttribute(
					"menuMap");
			if (userinfo == null && sessionMap == null) {
				UserInfo userout = null;
				RoleInfo rspObj = null;
				MD5Util getMD5 = new MD5Util();
				String oldpwd = getMD5.GetMD5Code(request
						.getParameter("user_pwd"));
				user.setUser_pwd(oldpwd);
				userout = (UserInfo) userInfoService.query(user);
				if (userout == null) {
					userout = new UserInfo();
					model.addAttribute("codeMsg", "用户名或密码错误");
					return "login";
				}

				/* 根据用户id查询所属角色start */
				UserAndRoles userRole = new UserAndRoles();
				userRole.setUser_id(userout.getUser_id());
				List userRoleList = userAndRolesService.queryList(userRole);
				if (userRoleList == null && userRoleList.size() == 0) {
					model.addAttribute("codeMsg", "用户没有角色信息");
					return "login";
				}

				UserAndRoles obj = (UserAndRoles) userRoleList.get(0);// 默认取出第一条数据
				logger.info("初始化默认角色id：" + obj.getRole_id());

				RoleInfo param = new RoleInfo();
				param.setRole_id(obj.getRole_id());
				rspObj = (RoleInfo) userPermissionService.queryById(param);// 根据角色id查询相关的菜单信息
				logger.info("初始化角色名：" + rspObj.getRole_name() + "\t 菜单组："
						+ rspObj.getFunction_ids() + "\t 用户名："
						+ userout.getUser_name());

				Map<String, Object> menuMap = new HashMap<String, Object>();
				PowerMenu objTmp = new PowerMenu();
				objTmp.setParent_function_id("0");
				List<BaseInfo> menuList = powerService.queryList(objTmp);
				if (menuList == null && rspObj == null) {
					logger.info("没有相关记录信息");
					model.addAttribute("codeMsg", "用户没有操作权限");
					return "login";
				}
				PowerMenu power = (PowerMenu) menuList.get(0);
				menuMap.put("id", power.getFunciton_id());
				menuMap.put("text", power.getFunction_name());
				List<Map<String, Object>> childListMap = new ArrayList<Map<String, Object>>();
				getChildNodes(childListMap, menuMap, rspObj);

				logger.info("初始化权限菜单结束");

				/* 根据用户id查询所属角色end */

				model.addAttribute("menuMap", menuMap);
				model.addAttribute("user", userout);
				request.getSession().setAttribute("loginUser", userout);
				request.getSession().setAttribute("menuMap", menuMap);
				request.getSession().setAttribute("roleInfo", rspObj);// 存储角色
			} else {
				model.addAttribute("user", userinfo);
				model.addAttribute("menuMap", sessionMap);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("codeMsg", "登录失败，请稍后再试");
			return "login";
		}
		return "index";
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userLoginOut", method = RequestMethod.GET)
	public String userLoginOut(UserInfo user, Model model,
			HttpServletRequest request) {
		logger.info("UserInfoController loginout");
		try {
			request.getSession().removeAttribute("loginUser");
			request.getSession().removeAttribute("menuMap");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return "redirect:login.do";
	}

	/**
	 * 后台中间面显示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/mianContent")
	public String mianContent(UserInfo user, Model model,
			HttpServletRequest request) {
		logger.info("UserInfoController mianContent:start");

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		model.addAttribute("username", userInfo.getUser_name());
		model.addAttribute("currTime", DateUtil.getCurrentDateString());
		Properties props = getSystemProperties();
		model.addAttribute("hjbb", props.getProperty("java.version"));
		model.addAttribute("azlj", props.getProperty("java.version"));
		model.addAttribute("yxsbb",
				props.getProperty("java.specification.version"));
		model.addAttribute("czxt",
				props.getProperty("os.name") + props.getProperty("os.version"));
		model.addAttribute("ip", getRequestRealIp(request));
		return "main";
	}

	/**
	 * 获取一级菜单
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getChildNodes(
			List<Map<String, Object>> childListMap,
			Map<String, Object> menuMap, RoleInfo obj) throws Exception {
		logger.info("UserInfoController1=" + obj.toString());
		PowerMenu powerPara = new PowerMenu();
		powerPara.setParent_function_id(String.valueOf(menuMap.get("id")));
		List<BaseInfo> childList = powerService.queryList(powerPara);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			PowerMenu function = (PowerMenu) childList.get(i);

			if (obj.getFunction_ids().indexOf(function.getFunciton_id()) > -1) {
				functionMap.put("id", function.getFunciton_id());
				functionMap.put("text", function.getFunction_name());
				functionMap.put("functionStyle", function.getFunction_style());
				list = getChildNodes1(childListMap, functionMap, obj);

				functionMap.put("children", list);
				childListMap.add(functionMap);
			}

		}
		menuMap.put("children", childListMap);
		return childListMap;
	}

	/**
	 * 获取二级菜单
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getChildNodes1(
			List<Map<String, Object>> childListMap,
			Map<String, Object> menuMap, RoleInfo obj) throws Exception {
		logger.info("UserInfoController2=" + obj.toString());
		PowerMenu powerPara = new PowerMenu();
		powerPara.setParent_function_id(String.valueOf(menuMap.get("id")));
		List<BaseInfo> childList = powerService.queryList(powerPara);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < childList.size(); i++) {
			Map<String, Object> functionMap = new HashMap<String, Object>();
			PowerMenu function = (PowerMenu) childList.get(i);

			if (obj.getFunction_ids().indexOf(function.getFunciton_id()) > -1) {
				functionMap.put("id", function.getFunciton_id());
				functionMap.put("text", function.getFunction_name());
				functionMap.put("url", function.getFunction_url());
				list.add(functionMap);
			}

		}
		return list;
	}

	public static Properties getSystemProperties() {
		Properties props = System.getProperties(); // 系统属性
		return props;
	}

	public static String getRequestRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.contains(",")) {
			ip = ip.split(",")[0];
		}

		if (!checkIp(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!checkIp(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!checkIp(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (!checkIp(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static boolean checkIp(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)) {
			return false;
		}
		return true;
	}
}
