package com.sd.farmework.controller;

import java.io.PrintWriter;

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
import com.sd.farmework.common.MD5Util;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.UserInfoService;


/**
 * 用户定制通用接口
 * @author 延小三
 * 2016-09-05
 * 
 */

@Controller
@Scope("prototype")
@RequestMapping(value="/admin")
public class UserInfoController {
	private static Logger logger = Logger.getLogger(UserInfoController.class);
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SysLogInfoService sysLogInfoService;

	
	
	 
	
	/**
	 * 添加用户页面
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUserPanel", method = RequestMethod.GET)
	public String  addUserPanel(UserInfo user,Model model){
		return "sysmanage/sysuser/add";
	}
	 
	

	
	 
	 
	
	 
	/**
	 * 修改密码
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateUserpwd", method = RequestMethod.GET)
	public void  updateUserpwd(UserInfo user,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("UserInfoController addUser:start");
		PrintWriter out = response.getWriter();
		MD5Util getMD5 = new MD5Util();
		String oldpwd=getMD5.GetMD5Code(request.getParameter("oldpwd"));
		String newpwd=getMD5.GetMD5Code(request.getParameter("user_pwd"));
		String newpwd2=getMD5.GetMD5Code(request.getParameter("newpwd2"));
		UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
		String context = "";
		user.setUser_id(loginUser.getUser_id());
		user=(UserInfo) this.userInfoService.queryall(user);
		String userpwd=user.getUser_pwd();
//		System.out.println("老密码"+userpwd+"获取的老密码"+oldpwd+"新密码"+newpwd+"二次新密码"+newpwd2);
		if(userpwd.equals(oldpwd)){
			if(newpwd.equals(newpwd2)){
				if(oldpwd.equals(newpwd)){
					out.write("repeatOpNp");
				}else{
					user.setUser_pwd(newpwd);
					this.userInfoService.updatepwd(user);
					context = "变更了密码";
					OperationLogUtil.writeLog(sysLogInfoService,context,user);
					out.write("success");
				}
			}else{
				out.write("repeat");
			}
		}else{
			out.write("passerror");
		}
	}
	 
}
