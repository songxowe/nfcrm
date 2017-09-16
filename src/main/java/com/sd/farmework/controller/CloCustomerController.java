package com.sd.farmework.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.CustomerProJInfo;
import com.sd.farmework.pojo.DateDictionaryInfo;
import com.sd.farmework.pojo.LogInfo;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.CloCustomerService;
import com.sd.farmework.service.CustomerListService;
import com.sd.farmework.service.DateDictionaryInfoService;
import com.sd.farmework.service.LogInfoService;
import com.sd.farmework.service.ProjectInfoService;
import com.sd.farmework.service.PubCustomerService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.services.util.FileUpload;

@Controller
@Scope("prototype")
@RequestMapping(value = "/clocustomer")
public class CloCustomerController {

	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private CustomerListService customerInfoService;
	@Autowired
	private ProjectInfoService projectInfoService;
	@Autowired
	private DateDictionaryInfoService dictionaryService;
	@Autowired
	private CloCustomerService clocustomerService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	@Autowired
	private PubCustomerService pubCustomerService;
	private static Logger logger = Logger
			.getLogger(CloCustomerController.class);

	/**
	 * 带分页和检索的查询方法 查询根据类型所有客户信息
	 * 
	 * @date 2016-12-27
	 * @Author LIAO
	 */
	@RequestMapping(value = "/queryCloCustomer")
	public String queryCloCustomer(CustomerInfo obj, Model model,
			DateDictionaryInfo datadictionary, LogInfo loginfo,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			obj.setTotalCount(this.clocustomerService.queryCount(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);
			List<CustomerInfo> customerList = clocustomerService.queryList(obj);
			for (CustomerInfo customerInfo : customerList) {
				System.out.println(customerInfo.toString());
			}
			// 取出登录时的权限session
			RoleInfo sessionRole = (RoleInfo) request.getSession()
					.getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息：" + sessionRole.toString() + "\t 功能id："
					+ function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
			model.addAttribute("customerList", customerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "customerinformation/historcustomlist";
	}

	/*----------------------------------------end------------------------------------------*/

	/**
	 * 加载Windows open页面
	 */
	@RequestMapping(value = "/redirectProPan", method = RequestMethod.GET)
	public String redirectProPan(ProjectInfo obj, Model model) {
		logger.info("CloCustomerController.redirectProPan_obj");
		return "customerinformation/customerByPro1";
	}

	/**
	 * 查询项目信息
	 * 
	 * @param obj
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectproname", method = RequestMethod.POST)
	public Object selectproname(ProjectInfo obj, Model model,
			HttpServletResponse response) {
		logger.info("CustomerInfoController.selectproname_obj="
				+ obj.toString());
		try {
			List<ProjectInfo> list = projectInfoService.queryPro(obj);
			model.addAttribute("proList", list);
			logger.info("查询所有项目end");

			response.getWriter().write(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加客户跟进日志信息
	 * 
	 * @param LogInfo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addlog", method = RequestMethod.POST)
	public Object addlog(LogInfo loginfo, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		loginfo.setCreateUserId(userinfo.getEmployee_id());
		System.out.println(userinfo.getEmployee_id());
		this.logInfoService.add(loginfo);
		Map map = new HashMap();
		map.put("logid", loginfo.getLog_id());
		return map;
	}

	/**
	 * 查询客户日志信息
	 * 
	 * @param LogInfo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectLog", method = RequestMethod.POST)
	public Map<String, List> selectLog(LogInfo obj, Model model,
			HttpServletRequest request) {
		String logid = request.getParameter("logId");
		if (StringUtil.isNotNullOrBlank(logid)) {
			int parseInt = Integer.parseInt(logid);
			obj.setLog_id(parseInt);
		}
		Map<String, List> map;
		try {
			map = new HashMap<String, List>();
			List list = logInfoService.queryLonInfo(obj);
			map.put("list", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("upload")
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List list = new ArrayList();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			Map map1 = new HashMap<String, String>();
			String str = it.next();
			String remark = multipartRequest.getParameter(str + "Remark");
			String type = multipartRequest.getParameter(str + "Type");
			String filePath = FileUpload.uploadFile(map.get(str), request);
			// remark:null
			// type:null
			// download.do?fileName=1483412021230ps5656b.jpg
			// System.out.println("remark:"+remark);
			// System.out.println("type:"+type);
			// System.out.println(filePath);
			map1.put("filePath", filePath);
			map1.put("remark", remark);
			map1.put("type", type);
			logger.info("filePath:" + filePath + ",remark=" + remark);
			response.setContentType("text/html;charset=utf8");
			list.add(map1);
		}
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}

	/**
	 * 查询跟进日志字数
	 * 
	 * @param obj
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryloginfo", method = RequestMethod.POST)
	public Object queryloginfo(DateDictionaryInfo datadictionary, Model model,
			HttpServletResponse response, HttpServletRequest request) {
		Map map = new HashMap();
		DateDictionaryInfo datadictionary1 = (DateDictionaryInfo) this.dictionaryService
				.queryloginfo(datadictionary);
		map.put("log", datadictionary1.getD_value());
		return map;
	}

	/**
	 * 下载图片
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("download")
	public void download(String fileName, HttpServletResponse response)
			throws IOException {
		OutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			response.setContentType("image/jpeg; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 编辑客户信息
	 * 
	 * @param customer
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryCloCustomerById", method = RequestMethod.GET)
	public String querybyid(CustomerInfo customer, CustomerProJInfo obj,
			Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		System.out.println("进来了...");
		CustomerInfo customerList = (CustomerInfo) this.pubCustomerService
				.queryById(customer);
		System.out.println(customerList.getPro_id());
		List loglist = logInfoService.queryByProId(customerList.getPro_id());
		List list = pubCustomerService.queryProNameById(obj);
		context = "修改了客户" + customerList.getCustomer_name() + "的信息";
		OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
		model.addAttribute("customerlist", customerList);
		model.addAttribute("loglist", loglist);
		model.addAttribute("prolist", list);

		return "customerinformation/clocustomerEdit";
	}

	/**
	 * 修改
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/updateById", method = RequestMethod.POST)
	public Object updateById(CustomerInfo customer, CustomerProJInfo obj,
			Model model, HttpServletRequest request) throws Exception {
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String cId = request.getParameter("cId");
		// 主推项目id
		String proid = request.getParameter("proid");
		// 主推项目名称
		String proName = request.getParameter("proName");

		if (StringUtil.isNotNullOrBlank(cId)) {
			// 先把所有的项目状态改为N
			pubCustomerService.updateTypeById(customer.getCustomer_id());
			// 然后将当前的项目状态改为Y
			obj.setC_id(cId);
			pubCustomerService.updateCusProInfo(obj);

			// 将列表显示的项目名修改为当前项目
			customer.setPro_id(proid);
			customer.setPro_name(proName);
			customer.setAdscription(userinfo.getUser_name());
			customer.setLastUpdateUserName(userinfo.getUser_name());
			customer.setLastUpdateUserId(userinfo.getEmployee_id());
			System.out.println(customer.toString());
			this.pubCustomerService.modifyPub3(customer);
		} else {
			// 先删除当前客户所有的项目
			pubCustomerService.delete(customer.getCustomer_id());
			// 再将项目都新增进表中

			// 拿到前台传递的所有的项目id
			String[] pid = request.getParameter("strid").split(",");
			String[] strname = request.getParameter("strname").split(",");
			// 将pid放入list集合中
			List<String> pidlist = new ArrayList<String>();
			List<String> strnamelist = new ArrayList<String>();
			for (int i = 0; i < pid.length; i++) {
				pidlist.add(i, pid[i]);
				strnamelist.add(i, strname[i]);
			}
			for (int i = 0; i < pidlist.size(); i++) {
				obj.setPro_id(pidlist.get(i));
				obj.setCustomer_id(customer.getCustomer_id());
				obj.setPro_name(strnamelist.get(i));
				obj.setPro_type("N");
				obj.setStatus("Y");
				obj.setCreateUserId(userinfo.getEmployee_id());
				obj.setCreateUserName(userinfo.getUser_name());
				pubCustomerService.addCustomerProName(obj);
			}
			// 将主推状态改为Y
			obj.setPro_id(proid);
			obj.setCustomer_id(customer.getCustomer_id());
			pubCustomerService.updateStatus(obj);
			// 将列表显示的项目名修改为当前项目
			customer.setPro_id(proid);
			customer.setPro_name(proName);
			customer.setAdscription(userinfo.getUser_name());
			customer.setLastUpdateUserName(userinfo.getUser_name());
			customer.setLastUpdateUserId(userinfo.getEmployee_id());
			this.pubCustomerService.modifyPub3(customer);
		}

		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("code", "ok");
		return map;
	}

	/**
	 * 查询客户详情
	 * 
	 * @param customer
	 * @param obj
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCustomerMsg", method = RequestMethod.GET)
	public String queryMsg(CustomerInfo customer, CustomerProJInfo obj,
			Model model) throws Exception {
		CustomerInfo customerList = (CustomerInfo) this.customerInfoService
				.queryById1(customer);
		List loglist = logInfoService.queryByProId(customerList.getPro_id());
		List<CustomerProJInfo> list = customerInfoService
				.queryCustomerProj(customerList.getCustomer_id());
		// for (CustomerProJInfo customerProJInfo : list) {
		// System.out.println(customerProJInfo.toString());
		// }
		model.addAttribute("prolist", list);
		model.addAttribute("customerlist", customerList);
		model.addAttribute("loglist", loglist);
		return "customerinformation/customerMsg";
	}
}
