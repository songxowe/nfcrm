package com.sd.farmework.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.LogInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.CloCustomerService;
import com.sd.farmework.service.LogInfoService;
import com.sd.farmework.service.UserInfoService;
@Controller
@Scope("prototype")
@RequestMapping(value = "/clocustomer")
public class MyTimerTask {
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private LogInfoService logInfoService;						
	@Autowired
	private CloCustomerService clocustomerService;
	/**
	 * 查所有跟进日志的再次跟进时间
	 * 
	 * @param 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getLogindata", method = RequestMethod.GET)
	@ResponseBody
	public Object getLogindata(Model model,HttpServletRequest request) throws Exception {
		
		    List returnList = new ArrayList();
		
			UserInfo user =null;
			if(request.getSession().getAttribute("loginUser")!=null){
				 user = (UserInfo) request.getSession().getAttribute("loginUser");
				 UserInfo userInfo = userInfoService.queryByUserName(user);
				 String employee_id = userInfo.getEmployee_id();
			 
			 LogInfo inputDto = new LogInfo();
			 inputDto.setCreateUserId(employee_id);
			 List<LogInfo> rFtimeList = logInfoService.queryRFtime(inputDto);
			 if(rFtimeList!=null && rFtimeList.size()>0){
				 for (int i = 0; i < rFtimeList.size(); i++) {
					 //获取该用户下所有的更近日志时间
					 LogInfo tempObj = rFtimeList.get(i);
					 // 取当前日期
					 Date now = new Date();
					 Date now_5 = new Date(now.getTime() + 300000); //5分钟后的时间
					 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//可以方便地修改日期格式
					 String nowTime_5 = dateFormat.format(now_5);
					    
					 // 与指定的日期比较
					 if(tempObj.getRepeat_follow().trim().equals(nowTime_5.trim())) {
					        
					 //时间相同 弹个窗是
					 System.out.println("有到期的跟进日志请注意查收");
					 returnList.add(tempObj);
					 }
				  } 
			  }
			 
		      }
		
			     	Map map = new HashMap();
					map.put("code", "000");
					map.put("returnList", returnList);
					return map;
}
	
	/**
	 * 查询客户信息by create_user_id
	 * 
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryCustomerByCUId", method = RequestMethod.GET)
	public String queryCustomerByCUId(CustomerInfo customer, Model model,HttpServletRequest request)
			throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute("loginUser");
		customer.setCreateUserId(user.getEmployee_id());
		System.out.println(user.getEmployee_id());
		List customerList =  this.clocustomerService.queryByCuid(customer);
		//取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo)request.getSession().getAttribute("roleInfo");
		String function_id = request.getParameter("function_id");
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("customerList", customerList);
		return "customerinformation/customerinfolist";
	}
	
}
