package com.sd.farmework.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.EmployeeInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserAdmin;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.EmployeeInfoService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.UserAdminService;
import com.sd.farmework.services.util.FileUpload;

@Controller
@Scope("prototype")
@RequestMapping(value="/employee")
public class EmployeeInfoController {
	private static Logger logger = Logger.getLogger(EmployeeInfoController.class);
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	@Autowired
	private UserAdminService userAdminService;
	
	/**
	 * 查询员工
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectEmployee", method = RequestMethod.POST)
	public Map<String,Object>  selectEmployee(EmployeeInfo obj,Model model) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List list=employeeInfoService.queryList(obj);
			map.put("list", list);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加员工
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public Object  addEmployee(EmployeeInfo obj,Model model,
			HttpServletRequest request) throws Exception{
		HttpSession session= request.getSession();
		UserInfo user=(UserInfo) session.getAttribute("loginUser");
		String context = "";
		obj.setCreateUserId(user.getEmployee_id());
		obj.setCreateUserName(user.getUser_name());
		obj.setLastUpdateUserId(user.getEmployee_id());
		obj.setLastUpdateUserName(user.getUser_name());
		employeeInfoService.add(obj);
		context = "新增了一个"+obj.getCustomer_level()+"员工";
		OperationLogUtil.writeLog(sysLogInfoService,context,user);
		//需要保存的格式
		/*download.do?fileName=214823.jpg*/
		//拿到的格式c:\fakepath\214823.jpg
		Map map=new HashMap();
		map.put("code", "000");
		return map;
	}
	/**
	 * 修改员工页面
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateEmployeeUI", method = RequestMethod.GET)
	public String updateEmployeeUI(EmployeeInfo employee,Model model
			) throws Exception{
		
		BaseInfo employeeinfo=employeeInfoService.queryById(employee);
		model.addAttribute("employeeinfo", employeeinfo);
		return "employee/updateeployeeMsg";
	}
	/**
	 * 修改员工
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public Object updateEmployee(EmployeeInfo employee,Model model,
			HttpServletRequest request){
		String context = "";
		try {
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			employee.setCreateUserId(user.getEmployee_id());
			employeeInfoService.update(employee);
			
			context = "修改了员工"+employee.getCustomer_level()+"的信息";
			OperationLogUtil.writeLog(sysLogInfoService,context,user);
			//修改用户表中的名字
			UserAdmin users=new UserAdmin();
			users.setEmployee_id(employee.getLevel_id());
			users.setUser_name(employee.getCustomer_level());
			users.setLastUpdateUserId(user.getEmployee_id());
			users.setLastUpdateUserName(user.getUser_name());
			userAdminService.updateByEId(users);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map map=new HashMap();
		map.put("code", "ok");
		return map;
	}
	
	/**
	 * 分页查询
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryEmployeeInfo")
	public String queryEmployeeInfo(EmployeeInfo obj, Model model,
			HttpServletRequest request,HttpServletResponse response) {
		
		logger.info("EmployeeInfoController queryEmployeeInfo:start");
		try {
			logger.info("分页查询开始");
			Date date = new Date();
			String time = DateUtil.getDateString(date, "yyyy-MM-dd HH:mm:ss");
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			obj.setCreateTime(time);
			obj.setCreateUserId(user.getEmployee_id());
			List employeeList = employeeInfoService.queryEmployeeInfo(obj);
			logger.info("分页查询结束");

			//取出登录时的权限session
					RoleInfo sessionRole = (RoleInfo)request.getSession().getAttribute("roleInfo");
					String function_id = request.getParameter("function_id");
					logger.info("权限信息："+sessionRole.toString()+"\t 功能id："+function_id);
					model.addAttribute("sessionRole", sessionRole);
					model.addAttribute("function_id", function_id);
			model.addAttribute("employeeList",employeeList);
			obj.setTotalCount(employeeInfoService.queryCount(obj));
			
 			model.addAttribute("totalCount", obj.getPageCount());
 			
 			model.addAttribute("currPage", obj.getCurrPage());
 			model.addAttribute("obj", obj); 			
 			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "employee/employeelist";
	}
	
	/**
	 * 修改员工页面
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employeeDetailUI", method = RequestMethod.GET)
	public String employeeDetailUI(EmployeeInfo employee,Model model) throws Exception{
		
		BaseInfo employeeinfo=employeeInfoService.queryById(employee);
		model.addAttribute("employeeinfo", employeeinfo);
		return "employee/eployeeDetailMsg";
	}
	/**
	 * 查询状态
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryStatus", method = RequestMethod.GET)
	public String queryStatus(EmployeeInfo employee,Model model) throws Exception{
		
		BaseInfo employeeinfo=employeeInfoService.queryById(employee);
		model.addAttribute("employeeinfo", employeeinfo);
		return "employee/eployeeDetailMsg";
	}
	
	/**
	 * 转试用状态
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeTryOut", method = RequestMethod.POST)
	public Object changeTryOut(EmployeeInfo employee,
			HttpServletRequest request) throws Exception{
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("loginUser");
		String context = "";
		Map map=new HashMap();
		String[] strid=request.getParameter("strid").split(",");
		if(!employee.getStatus().equals("2") && !employee.getStatus().equals("1")){
		for(int i=0;i<strid.length;i++){
			EmployeeInfo emp=new EmployeeInfo();
			emp.setLevel_id(strid[i]);
			employeeInfoService.changeTryOut(emp);			
		
			EmployeeInfo employeeInfo=(EmployeeInfo) employeeInfoService.queryById(emp);
			context = "将"+employeeInfo.getCustomer_level()+"转为试用员工";
			OperationLogUtil.writeLog(sysLogInfoService,context,userinfo);
		}
		}
		map.put("code", "ok");
		return map;
	}
	
	/**
	 * 转正式状态
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeForMal", method = RequestMethod.POST)
	public Object changeForMal(EmployeeInfo employee,
			HttpServletRequest request) throws Exception{
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("loginUser");
		String context = "";
		
		String[] strid=request.getParameter("strid").split(",");
		for(int i=0;i<strid.length;i++){
			EmployeeInfo emp=new EmployeeInfo();
			emp.setLevel_id(strid[i]);
			employeeInfoService.changeForMal(emp);
			
			EmployeeInfo employeeInfo=(EmployeeInfo) employeeInfoService.queryById(emp);
			context = "将"+employeeInfo.getCustomer_level()+"转为正式员工";
			OperationLogUtil.writeLog(sysLogInfoService,context,userinfo);
		}
		Map map=new HashMap();
		map.put("code", "ok");
		return map;
	}
	/**
	 * 转离职状态
	 * @param employee
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeLeav", method = RequestMethod.POST)
	public Object changeLeav(EmployeeInfo employee,
			HttpServletRequest request) throws Exception{
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute("loginUser");
		String context = "";
		
		String[] strid=request.getParameter("strid").split(",");
		for(int i=0;i<strid.length;i++){
			EmployeeInfo emp=new EmployeeInfo();
			emp.setLevel_id(strid[i]);
			employeeInfoService.changeLeav(emp);
			
			EmployeeInfo employeeInfo=(EmployeeInfo) employeeInfoService.queryById(emp);
			context = "将"+employeeInfo.getCustomer_level()+"转为离职员工";
			OperationLogUtil.writeLog(sysLogInfoService,context,userinfo);
		}
		Map map=new HashMap();
		map.put("code", "ok");
		return map;
	}
	
	 @RequestMapping("upload")
	 public void upload( HttpServletRequest request, HttpServletResponse response,
			 EmployeeInfo emp) throws IOException {
	        
	    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	    	Map<String, MultipartFile> map=   multipartRequest.getFileMap();
	    	List list  =new ArrayList();
	    	Iterator<String> it= map.keySet().iterator();
	     	while (it.hasNext()) {
	     		Map map1 =new HashMap<String,String>();
	    		String str=it.next();
	    		String remark=multipartRequest.getParameter(str+"remark");
	    		String type=multipartRequest.getParameter(str+"type");
	    		String filePath = FileUpload.uploadFile(map.get(str), request);
	    		map1.put("filePath", filePath);
	    		map1.put("remark", remark);
	    		map1.put("type", type);
	    		logger.info("filePath:" + filePath+",remark="+remark);
	    		response.setContentType("text/html;charset=utf8");
	    		list.add( map1);
			}
	     	
			response.getWriter().write(JSONArray.fromObject(list).toString());
	    }
	 
	 @RequestMapping("download")
	 public void download(String fileName, HttpServletResponse response) throws IOException {
	        OutputStream os = response.getOutputStream();
	        try {
	            response.reset();
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	            response.setContentType("image/jpeg; charset=utf-8");
	            os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));
	            os.flush();
	        } finally {
	            if (os != null) {
	                os.close();
	            }
	        }
	    }
}
