package com.sd.farmework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.TemplateContentInfo;
import com.sd.farmework.pojo.TemplateInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.service.TemplateInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value="/template")
public class TemplateInfoController {
	private static Logger logger = Logger.getLogger(TemplateInfoController.class);
	@Autowired
	private TemplateInfoService templateInfoService;
	@Autowired
	private SysLogInfoService sysLogInfoService;
	/**
	 * 查询模板信息
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/selectTemplate", method = RequestMethod.GET)
	public String selectTemplate(TemplateInfo obj,Model model,HttpServletRequest request) throws Exception{
		
		if(StringUtil.isNotNullOrBlank(obj.getTemplate_name())){
				String tmpStr = new String(obj.getTemplate_name().getBytes("ISO-8859-1"),"utf-8");
				
				obj.setTemplate_name(tmpStr);
		}
		List templatelist=templateInfoService.queryList(obj);
		model.addAttribute("template", templatelist);
		obj.setTotalCount(templateInfoService.queryCount(obj));
		model.addAttribute("totalCount", obj.getPageCount());
		model.addAttribute("currPage", obj.getCurrPage());
		model.addAttribute("obj", obj);
		//取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo)request.getSession().getAttribute("roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息："+sessionRole.toString()+"\t 功能id："+function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		return "template/templatelist";
	}
	/**
	 * 查询供添加项目时使用的模板的名称
	 * @param obj
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selectTemplate2", method = RequestMethod.GET)
	public Object selectTemplate2(TemplateInfo obj,Model model,HttpServletResponse response) throws Exception{
		List<BaseInfo> templatelist=templateInfoService.queryALL(obj);
		List<Object> newlist=new ArrayList<Object>();
		for(int i=0;i<templatelist.size();i++){
			obj=(TemplateInfo) templatelist.get(i);
			newlist.add(obj);
		}
		Map<String,List> map1=new HashMap<String,List>();
		map1.put("mylist",newlist);
		return map1;
	}

	/**
	 * 添加模板页面
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTemplateUI", method = RequestMethod.GET)
	public String addTemplateUI(TemplateInfo obj,Model model) throws Exception{
		
		return "template/addtemp";
	}
	/**
	 * 添加模板内容操作
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
	@ResponseBody
	public Object addTemplate(TemplateInfo obj,Model model,
			HttpServletRequest request) throws Exception{
		
		String tempname=request.getParameter("tempname");
		String keyStr = request.getParameter("keyStr");
		String valStr = request.getParameter("valStr");
		String keytId = request.getParameter("keytIdstr");
		String valtId = request.getParameter("valtIdstr");
		String context = "";
		try {
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			obj.setTemplate_name(tempname);
			obj.setCreateUserId(user.getEmployee_id());
			templateInfoService.addTempRooback(obj, keyStr, valStr,keytId,valtId);
			
			context = "新增了一个模板为"+obj.getTemplate_name();
			OperationLogUtil.writeLog(sysLogInfoService,context,user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map rspMap = new HashMap();
		rspMap.put("code", "000");
		return rspMap;
	}
	
	/**
	 * 编辑模板内容页面
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateTemplateUI", method = RequestMethod.GET)
	public String updateTemplateUI(TemplateInfo temp,TemplateContentInfo obj,Model model) throws Exception{
		List<TemplateContentInfo> list= templateInfoService.queryMulti(obj);
		TemplateInfo tempinfo= (TemplateInfo) templateInfoService.queryById(obj);
		model.addAttribute("temp", tempinfo);
		model.addAttribute("templist", list);
		return "template/updateaddtemp";
	}
	
	/**
	 * 编辑模板内容操作
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateTemplate", method = RequestMethod.POST)
	public String updateTemplate(TemplateInfo obj,TemplateContentInfo obj1,Model model,
			HttpServletRequest request) {
		String context = "";
		logger.info("TemplateInfoController updateTemplate:start");
		try {
			/*------ start: ------*/
			templateInfoService.update(obj);
			HttpSession session= request.getSession();
			UserInfo user=(UserInfo) session.getAttribute("loginUser");
			List<String> keyList = new ArrayList<String>();
			List<String> valList = new ArrayList<String>();
			
			context = "修改了"+obj.getTemplate_name()+"的模板信息";
			OperationLogUtil.writeLog(sysLogInfoService,context,user);
			
			if(StringUtil.isNullOrBlank(obj1.getT_content_id())){
				String[] strKey=obj1.getKey_name().split(",");
				String[] strVal=obj1.getValue().split(",");
				for (int i = 0; i < strKey.length; i++) {
					keyList.add(i, strKey[i]);
				}
				for (int i = 0; i < strVal.length; i++) {
					valList.add(i, strVal[i]);
				}
				if(strKey.length > strVal.length){
					int count = strKey.length - strVal.length;
					for (int i = 1; i <= count; i++) {
						valList.add(valList.size(),"");
					}
				}else{
					int count = strVal.length - strKey.length;
					for (int i = 1; i <= count; i++) {
						keyList.add(keyList.size(),"");
					}
				}
				for(int i=0;i<keyList.size();i++){
						TemplateContentInfo temp=new TemplateContentInfo();
						temp.setKey_name(keyList.get(i));
						temp.setValue(valList.get(i));
						temp.setT_id(obj.getT_id());
						temp.setTemplate_name(obj.getTemplate_name());
						temp.setCreateUserId(user.getUser_id());
						templateInfoService.addTempContent(temp);
				}
			}else{
				String[] tCid=obj1.getT_content_id().split(",");
				for(int i=0;i<tCid.length;i++){
					TemplateContentInfo temp=new TemplateContentInfo();
					temp.setT_content_id(tCid[i]);
					templateInfoService.deleteTempContent(temp);
				}
				String[] strKey=obj1.getKey_name().split(",");
				String[] strVal=obj1.getValue().split(",");
				for (int i = 0; i < strKey.length; i++) {
					keyList.add(i, strKey[i]);
				}
				for (int i = 0; i < strVal.length; i++) {
					valList.add(i, strVal[i]);
				}
				if(strKey.length > strVal.length){
					int count = strKey.length - strVal.length;
					for (int i = 1; i <= count; i++) {
						valList.add(valList.size(),"");
					}
				}else{
					int count = strVal.length - strKey.length;
					for (int i = 1; i <= count; i++) {
						keyList.add(keyList.size(),"");
					}
				}
				for(int i=0;i<keyList.size();i++){
					TemplateContentInfo temp=new TemplateContentInfo();
					temp.setKey_name(keyList.get(i));
					temp.setValue(valList.get(i));
					temp.setT_id(obj.getT_id());
					temp.setTemplate_name(obj.getTemplate_name());
					temp.setCreateUserId(user.getUser_id());
					templateInfoService.addTempContent(temp);
				}
			}
			/*-------- end: -------*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:selectTemplate.do";
	}
	
	/**
	 * 编辑模板内容页面
	 * @param template
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/templateDetail", method = RequestMethod.GET)
	public String templateDetail(TemplateContentInfo obj,Model model) throws Exception{
		List temp= (List) templateInfoService.queryMulti(obj);
		model.addAttribute("temp", temp);
		return "template/templatedetail";
	}
}
