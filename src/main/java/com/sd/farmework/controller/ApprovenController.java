package com.sd.farmework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.SysApprovenFunction;
import com.sd.farmework.pojo.SysApprovenFunctionRule;
import com.sd.farmework.pojo.SysApprovenRulePerson;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.SysApprovenFunctionRuleService;
import com.sd.farmework.service.SysApprovenFunctionService;
import com.sd.farmework.service.SysApprovenRulePersonService;
import com.sd.farmework.service.UserAdminService;


/**
 * 用户定制通用接口
 * @author 延小三
 * 2016-09-05
 * 
 */

@Controller
@Scope("prototype")
@RequestMapping(value="/admin/approven")
public class ApprovenController {
	private static Logger logger = Logger.getLogger(ApprovenController.class);
	 
	
	@Autowired
	private SysApprovenFunctionRuleService sysApprovenFunctionRuleService;
	
	@Autowired
	private SysApprovenFunctionService sysApprovenFunctionService;
	@Autowired
	private SysApprovenRulePersonService sysApprovenRulePersonService;
	 
	@Autowired
	private UserAdminService userAdminService;
	/**
	 * 添加用户页面
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String  addUserPanel(UserInfo user,Model model){
		return "systemmanagement/approven/index";
	}
	 
	
	

	/**
	 * 查询所有审理业务类型
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/approvenFunctionDataList", method = RequestMethod.POST)
	public String approvenFunctionDataList(SysApprovenFunction obj, Model model,HttpServletResponse response) throws Exception {
		  
		List<BaseInfo> list = this.sysApprovenFunctionService.queryList(obj);
  
 		model.addAttribute("list", list);
 		
 		response.getWriter().write(JSONArray.fromObject(list).toString());
 		
 		
		return null;
	}


	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/approvenFunctionList", method = RequestMethod.GET)
	public String approvenFunctionList(SysApprovenFunction obj, Model model) throws Exception {
		  
		 
		return "systemmanagement/approven/approvenFunctionList";
	}

	 
	
	/**
	 * 查询所有审理业务类型
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/approvenFunctionRuleDataList", method = RequestMethod.POST)
	public String approvenFunctionRuleDataList(SysApprovenFunctionRule obj, Model model,HttpServletResponse response) throws Exception {
		  
		List<BaseInfo> list = this.sysApprovenFunctionRuleService.queryList(obj);
  
 		model.addAttribute("list", list);
 		
 		response.getWriter().write(JSONArray.fromObject(list).toString());
 		
 		
		return null;
	}


	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/approvenFunctionRuleList", method = RequestMethod.GET)
	public String approvenFunctionRuleList(SysApprovenFunctionRule obj, Model model) throws Exception {
		  
		model.addAttribute("approvenFunctionId", obj.getApprovenFunctionId()) ;
		model.addAttribute("approvenFunctionName", obj.getApprovenFunctionName()) ;
		
		return "systemmanagement/approven/approvenFunctionRuleList";
	}

	/**
	 * 查询所有审理业务类型
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody 
	 
 	@RequestMapping(value = "/approvenFunctionRulePersonDataList", method = RequestMethod.POST)
	public String approvenFunctionRulePersonDataList(SysApprovenFunctionRule obj, Model model,HttpServletResponse response) throws Exception {
	 
		 //通过sys_approven_function_rule 表获取编号
		 
 		 
		 List  list= sysApprovenRulePersonService.queryList(obj);
		
  
 		model.addAttribute("list", list);
 		
 		response.getWriter().write(JSONArray.fromObject(list).toString());
 		
 		
		return null;
	}


	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */ 
	@RequestMapping(value = "/approvenFunctionRulePersonList", method = RequestMethod.GET)
	public String approvenFunctionRulePersonList(SysApprovenRulePerson obj, Model model) throws Exception {
		  
		model.addAttribute("approvenRuleId", obj.getApprovenRuleId()) ; 
		model.addAttribute("approvenRuleName", obj.getApprovenRuleName()) ; 
		model.addAttribute("approvenFunctionId", obj.getApprovenFunctionId()) ; 
		model.addAttribute("approvenFunctionName", obj.getApprovenFunctionName()) ; 
		
		return "systemmanagement/approven/approvenFunctionRulePersonList";
	}

	
	/**
	 * 添加用户页面
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTurnTo", method = RequestMethod.GET)
	public String  addTurnTo(Model model,HttpServletRequest request){
		
		String type=request.getParameter("type");
		String pkId =request.getParameter("pkId");
		String  pkName=request.getParameter("pkName");
		String pk2Id =request.getParameter("pk2Id");
		String  pk2Name=request.getParameter("pk2Name");
		model.addAttribute("pkName", pkName);
		model.addAttribute("pkId", pkId);
		model.addAttribute("pk2Name", pk2Name);
		model.addAttribute("pk2Id", pk2Id);
		model.addAttribute("type", type);
		
		if("rule".equals(type)){
			
			return "systemmanagement/approven/addApprovenFunctionRule";
		}else if("person".equals(type)){
			return "systemmanagement/approven/addApprovenFunctionRulePerson";

		}
		return "error";
	}
	 
	 
	 
	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/approvenFunctionRuleAdd", method = RequestMethod.POST)
	public String approvenFunctionRuleAdd(SysApprovenFunctionRule obj, Model model,HttpServletRequest request) throws Exception {
		sysApprovenFunctionRuleService.add(obj);
		 
		model.addAttribute("code","000");
		
		
		return addTurnTo(model,request);
	}
	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/approvenFunctionRulePersonAdd", method = RequestMethod.POST)
	public String approvenFunctionRulePersonAdd(SysApprovenRulePerson obj, Model model,HttpServletRequest request) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		obj.setCreateUserId(userInfo.getUser_id());
		obj.setCreateUserName(userInfo.getCreate_person());
		obj.setApprovenRuleId(StringUtil.fullNumberByLength(obj.getApprovenRuleId()));
		sysApprovenRulePersonService.add(obj);
		 
		model.addAttribute("code","000");
		
		
		return addTurnTo(model,request);
	}
	 
	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody 
	@RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
	public String queryUserList(UserAdmin user, Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		int page=Integer.parseInt(request.getParameter("page"));
		user.setCurrPage(page);
		List<BaseInfo> list = this.userAdminService.queryList(user);
		user.setTotalCount(userAdminService.queryCount(user));
		user.setPageSize(user.getTotalCount());
		model.addAttribute("userList", list);
 		model.addAttribute("list", list);
 		JSONArray array=new JSONArray();
 		JSONObject json=new JSONObject();
 		json.put("total", user.getTotalCount());
 		json.put("rows", array.fromObject(list));
 		response.getWriter().write(json.toString());
		return null;
	}
	/**
	 * 查询所有
	 * 
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/approvenFunctionRulePersonDelete", method = RequestMethod.POST)
	public String approvenFunctionRulePersonDelete(SysApprovenRulePerson obj, Model model,HttpServletRequest request) throws Exception {
		sysApprovenRulePersonService.delete(obj);
		 
		model.addAttribute("code","000");
		
		
		return addTurnTo(model,request);
	}
	
	 
}
