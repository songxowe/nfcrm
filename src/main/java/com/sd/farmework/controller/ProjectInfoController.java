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

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sd.farmework.common.util.DateUtil;
import com.sd.farmework.common.util.OperationLogUtil;
import com.sd.farmework.common.util.StringUtil;
import com.sd.farmework.pojo.ProConfig;
import com.sd.farmework.pojo.ProjectImg;
import com.sd.farmework.pojo.ProjectInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.ProConfigService;
import com.sd.farmework.service.ProImgService;
import com.sd.farmework.service.ProjectInfoService;
import com.sd.farmework.service.SysLogInfoService;
import com.sd.farmework.services.util.FileUpload;

@Controller
@Scope("prototype")
@RequestMapping(value = "/proj")
public class ProjectInfoController {
	// 日志工具
	private static Logger logger = Logger
			.getLogger(ProjectInfoController.class);
	@Autowired
	private ProjectInfoService proInfoService;

	@Autowired
	private SysLogInfoService sysLogInfoService;

	@Autowired
	private ProConfigService proconfService;
	@Autowired
	private ProImgService proImgService;

	// 主页面用到的方法********************************************************************************
	/**
	 * 查所有项目
	 * 
	 * @param pro
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallpro")
	public String selectpro(ProjectInfo pro, Model model,
			HttpServletRequest request) throws Exception {

		pro.setTotalCount(proInfoService.queryCount(pro));
		model.addAttribute("totalCount", pro.getPageCount());
		model.addAttribute("currPage", pro.getCurrPage());

		model.addAttribute("obj", pro);
		List<BaseInfo> list = this.proInfoService.queryList(pro);
		// 取出登录时的权限session
		RoleInfo sessionRole = (RoleInfo) request.getSession().getAttribute(
				"roleInfo");
		String function_id = request.getParameter("function_id");
		logger.info("权限信息：" + sessionRole.toString() + "\t 功能id：" + function_id);
		model.addAttribute("sessionRole", sessionRole);
		model.addAttribute("function_id", function_id);
		model.addAttribute("prolist", list);
		return "projectmanagement/itemlist";
	}

	/**
	 * 
	 * 修改状态
	 * 
	 */
	static int i = 0;

	@RequestMapping(value = "/changestatus", method = RequestMethod.GET)
	public String changestatus(ProjectInfo pro, Model model,
			HttpServletRequest request) throws Exception {
		// 获取登录信息
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		// 创建空字符串来接收修改的状态值
		String context = "";
		pro = (ProjectInfo) this.proInfoService.queryById(pro);
		String status = pro.getPro_status();
		if (status.equals("1")) {
			pro.setPro_status("0");
			context = "将" + pro.getPro_name() + "项目开始";
		} else {
			pro.setPro_status("1");
			context = "将" + pro.getPro_name() + "项目结束";
		}
		this.proInfoService.updatestatus(pro);
		OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

		return "redirect:selectallpro.do";

	}

	// 查看单个所用到的方法*******************************************************************************
	/*---------------start-----------------*/
	/**
	 * 查一条详细记录
	 * 
	 * @param pro
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/proMsgdetail", method = RequestMethod.GET)
	public String prodetail(ProjectInfo pro, ProjectImg proimg,
			HttpServletRequest request, ProConfig proc, Model model)
			throws Exception {

		// 先跟id查单个项目projectinfo
		pro = (ProjectInfo) this.proInfoService.queryById(pro);
		model.addAttribute("prodetail", pro);
		// 创建几个集合用来接收图片 设施
		List<String> conf1list = new ArrayList<String>();
		List<String> conf2list = new ArrayList<String>();
		List<String> conf3list = new ArrayList<String>();
		List<Object> img1list = new ArrayList<Object>();
		List<Object> img2list = new ArrayList<Object>();
		List<Object> img3list = new ArrayList<Object>();
		List<Object> img4list = new ArrayList<Object>();
		// 查出属于这个项目的所有设施proConfig
		List<BaseInfo> list = this.proconfService.queryList(proc);
		// 如果不为空就便利对比类型的值 根据类型不同存入不同的集合中
		if (!StringUtil.isNullOrBlank(list)) {
			for (int i = 0; i < list.size(); i++) {
				proc = (ProConfig) list.get(i);
				if (proc.getConf_type().equals("facilities")) {
					conf1list.add(proc.getConf_name());
				} else if (proc.getConf_type().equals("operate")) {
					conf2list.add(proc.getConf_name());
				} else if (proc.getConf_type().equals("workstep")) {
					conf3list.add(proc.getConf_name());
				} else {
					System.out.println("都不是");
				}
			}
		}
		List<BaseInfo> ImgMsg = this.proImgService.queryList(proimg);
		// StringUtil.isNullOrBlank 字符串是否为空
		if (!StringUtil.isNullOrBlank(ImgMsg)) {
			for (int i = 0; i < ImgMsg.size(); i++) {
				proimg = (ProjectImg) ImgMsg.get(i);
				if (proimg.getImg_type().equals("indoor")) {
					img1list.add(proimg);
				} else if (proimg.getImg_type().equals("tile")) {
					img2list.add(proimg);
				} else if (proimg.getImg_type().equals("show")) {
					img3list.add(proimg);
				} else if (proimg.getImg_type().equals("envir")) {
					img4list.add(proimg);
				} else {
					System.out.println("都不是");
				}
			}
		}
		// 将处理得到的数据存进集合中去也没便利出来
		model.addAttribute("conf1", conf1list);
		model.addAttribute("conf2", conf2list);
		model.addAttribute("conf3", conf3list);
		model.addAttribute("img1", img1list);
		model.addAttribute("img2", img2list);
		model.addAttribute("img3", img3list);
		model.addAttribute("img4", img4list);
		return "projectmanagement/prodetail";
	}

	// 先查单个----》再修改
	/*---------------start-----------------*/
	/**
	 * 编辑
	 * 
	 * @param pro
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editpro", method = RequestMethod.GET)
	public String editpro(ProjectInfo pro, ProjectImg proimg,
			HttpServletRequest request, ProConfig proc, Model model)
			throws Exception {
		pro = (ProjectInfo) this.proInfoService.queryById(pro);
		model.addAttribute("prodetail", pro);
		List<String> conf1list = new ArrayList<String>();
		List<String> conf2list = new ArrayList<String>();
		List<String> conf3list = new ArrayList<String>();
		List<Object> img1list = new ArrayList<Object>();
		List<Object> img2list = new ArrayList<Object>();
		List<Object> img3list = new ArrayList<Object>();
		List<Object> img4list = new ArrayList<Object>();
		List<BaseInfo> list = this.proconfService.queryList(proc);
		if (!StringUtil.isNullOrBlank(list)) {
			for (int i = 0; i < list.size(); i++) {
				proc = (ProConfig) list.get(i);
				if (proc.getConf_type().equals("facilities")) {
					conf1list.add(proc.getConf_name());
				} else if (proc.getConf_type().equals("operate")) {
					conf2list.add(proc.getConf_name());
				} else if (proc.getConf_type().equals("workstep")) {
					conf3list.add(proc.getConf_name());
				} else {
					System.out.println("都不是");
				}
			}
		}
		List<BaseInfo> ImgMsg = this.proImgService.queryList(proimg);
		if (!StringUtil.isNullOrBlank(ImgMsg)) {
			for (int i = 0; i < ImgMsg.size(); i++) {
				proimg = (ProjectImg) ImgMsg.get(i);
				if (proimg.getImg_type().equals("indoor")) {
					img1list.add(proimg);
				} else if (proimg.getImg_type().equals("tile")) {
					img2list.add(proimg);
				} else if (proimg.getImg_type().equals("show")) {
					img3list.add(proimg);
				} else if (proimg.getImg_type().equals("envir")) {
					img4list.add(proimg);
				} else {
					System.out.println("都不是");
				}
			}
		}

		model.addAttribute("conf1", conf1list);
		model.addAttribute("conf2", conf2list);
		model.addAttribute("conf3", conf3list);
		model.addAttribute("img1", img1list);
		model.addAttribute("img2", img2list);
		model.addAttribute("img3", img3list);
		model.addAttribute("img4", img4list);
		return "projectmanagement/editpro";
	}

	/**
	 * 更新
	 */
	@ResponseBody
	@RequestMapping(value = "/updateproMsg", method = RequestMethod.POST)
	public void updateprodetail(ProjectInfo pro, ProConfig conf, Model model,
			HttpServletRequest request) throws Exception {
		// 获得登录用户
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String confi = request.getParameter("confi");
		String context = "";
		try {
			// 修改项目信息pojectinfo
			this.proInfoService.updatepro(pro);
			// 记录
			context = "修改了" + pro.getPro_name() + "的项目信息";
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);
		} catch (Exception e) {
			logger.info("信息更新出错！");

			e.printStackTrace();
		}
		// 如果配套设施不为空 修改配套信息
		if (!StringUtil.isNullOrBlank(confi)) {
			this.proconfService.deleteconfig(conf);
			String[] str = confi.split("\\|");
			for (int i = 0; i < str.length; i++) {
				String temp = str[i];
				String[] tmp = temp.split(",");
				conf = new ProConfig();
				conf.setConf_name(tmp[0]);
				conf.setConf_type(tmp[1]);
				conf.setConf_proid(pro.getPro_id());
				conf.setCreateUserId(userinfo.getUser_id());
				conf.setCreateUserName(userinfo.getUser_name());
				this.proconfService.add(conf);
			}
		}
	}

	/*---------------end----------------*/

	// -*********************************************增加所用到的方法**************************************

	/**
	 * 新增个人项目时回显公司项目的设施和图片（跟修改和查看单个大致相同）
	 * 
	 * @param pro
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rebackdata", method = RequestMethod.GET)
	public String huixian(ProjectInfo pro, ProjectImg proimg,
			HttpServletRequest request, ProConfig proc, Model model)
			throws Exception {
		String pro_id = request.getParameter("pro_id");
		pro = (ProjectInfo) this.proInfoService.queryById(pro);
		model.addAttribute("prodetail", pro);
		List<String> conf1list = new ArrayList<String>();
		List<String> conf2list = new ArrayList<String>();
		List<String> conf3list = new ArrayList<String>();
		List<Object> img1list = new ArrayList<Object>();
		List<Object> img2list = new ArrayList<Object>();
		List<Object> img3list = new ArrayList<Object>();
		List<Object> img4list = new ArrayList<Object>();
		proc.setConf_proid(pro_id);
		List<BaseInfo> list = this.proconfService.queryList(proc);
		if (!StringUtil.isNullOrBlank(list)) {
			for (int i = 0; i < list.size(); i++) {
				proc = (ProConfig) list.get(i);
				if (proc.getConf_type().equals("facilities")) {
					conf1list.add(proc.getConf_name());
				} else if (proc.getConf_type().equals("operate")) {
					conf2list.add(proc.getConf_name());
				} else if (proc.getConf_type().equals("workstep")) {
					conf3list.add(proc.getConf_name());
				} else {
					System.out.println("都不是");
				}
			}
		}
		proimg.setImg_proid(pro_id);
		List<BaseInfo> ImgMsg = this.proImgService.queryList(proimg);
		if (!StringUtil.isNullOrBlank(ImgMsg)) {
			for (int i = 0; i < ImgMsg.size(); i++) {
				proimg = (ProjectImg) ImgMsg.get(i);
				if (proimg.getImg_type().equals("indoor")) {
					img1list.add(proimg);
				} else if (proimg.getImg_type().equals("tile")) {
					img2list.add(proimg);
				} else if (proimg.getImg_type().equals("show")) {
					img3list.add(proimg);
				} else if (proimg.getImg_type().equals("envir")) {
					img4list.add(proimg);
				} else {
					System.out.println("都不是");
				}
			}
		}

		model.addAttribute("conf1", conf1list);
		model.addAttribute("conf2", conf2list);
		model.addAttribute("conf3", conf3list);
		model.addAttribute("img1", img1list);
		model.addAttribute("img2", img2list);
		model.addAttribute("img3", img3list);
		model.addAttribute("img4", img4list);
		return "projectmanagement/addpro";
	}

	/* 选择个人项目后 查询所有的公司项目 */
	@ResponseBody
	@RequestMapping(value = "/queryprotype", method = RequestMethod.POST)
	public Object querybytype(ProjectInfo pro, Model model,
			HttpServletRequest request) throws Exception {
		pro.setPro_type("1");
		List<BaseInfo> prolist = this.proInfoService.querytype(pro);
		if (prolist != null) {

			System.out.println("查到了数据");
		} else {
			System.out.println("没有查到了数据");

		}
		Map map = new HashMap();
		map.put("protypelist", prolist);
		return map;
	}

	/* 根据公司项目id查详情 */
	@ResponseBody
	@RequestMapping(value = "/queryprodetail", method = RequestMethod.POST)
	public Object querybyid(ProjectInfo pro, Model model,
			HttpServletRequest request) throws Exception {
		pro = (ProjectInfo) this.proInfoService.queryById(pro);

		Map map = new HashMap();
		map.put("prodetail", pro);
		return map;
	}

	/**
	 * 添加项目信息
	 * 
	 * @param pro
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addproMsg", method = RequestMethod.POST)
	public Object addproMsg(ProjectInfo pro, ProConfig conf, ProjectImg img,
			Model model, HttpServletRequest request) throws Exception {
		String confi = request.getParameter("confi");
		UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
				"loginUser");
		String context = "";
		try {
			pro.setCreateUserId(userinfo.getUser_id());
			pro.setCreateUserName(userinfo.getUser_name());
			pro.setPro_status("0");
			// 项目编号格式 日期+10000-99999之间的随机数
			Random random = new Random();
			int randomnum = random.nextInt(99999) + 10000;
			String datt = DateUtil.getDateString(new Date(), "yyyyMMdd");
			String after = datt + "" + randomnum;
			pro.setPro_no(after);
			this.proInfoService.addpro(pro);

			String Ptype = null;
			if (StringUtil.isExisted(pro.getPro_type(), "1")) {
				Ptype = "公司项目";
			} else if (StringUtil.isExisted(pro.getPro_type(), "0")) {
				Ptype = "个人项目";
			}
			context = "新增了一个" + pro.getPro_name() + Ptype;
			OperationLogUtil.writeLog(sysLogInfoService, context, userinfo);

			if (!StringUtil.isNullOrBlank(confi)) {
				String[] str = confi.split("\\|");
				for (int i = 0; i < str.length; i++) {
					String temp = str[i];
					String[] tmp = temp.split(",");
					conf = new ProConfig();
					conf.setConf_name(tmp[0]);
					conf.setConf_type(tmp[1]);
					conf.setConf_proid(pro.getPro_id());
					conf.setCreateUserId(userinfo.getUser_id());
					conf.setCreateUserName(userinfo.getUser_name());
					this.proconfService.add(conf);

				}
			}
			return pro;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("信息添加失败！");
			return null;
		}
	}

	// 一些共用的方法 ----删除配置信息、图片的方法（上次、显示、删除）***********************************
	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	@RequestMapping("upload")
	public void upload(HttpServletRequest request,
			HttpServletResponse response, ProjectImg proimg) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		List list = new ArrayList();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			Map map1 = new HashMap<String, String>();
			String str = it.next();
			String projectid = multipartRequest.getParameter("projectid");
			String type = multipartRequest.getParameter(str + "type");
			String remark = multipartRequest.getParameter(str + "remark");
			String filePath = FileUpload.uploadFile(map.get(str), request);
			String newPath = "../" + filePath;
			map1.put("filePath", newPath);
			map1.put("remark", remark);
			map1.put("type", type);
			UserInfo userinfo = (UserInfo) request.getSession().getAttribute(
					"loginUser");
			proimg.setCreateUserName(userinfo.getUser_name());
			proimg.setCreateUserId(userinfo.getUser_id());
			proimg.setImg_source(newPath);
			proimg.setImg_remark(remark);
			proimg.setImg_type(type);
			proimg.setImg_proid(projectid);
			try {
				this.proImgService.add(proimg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setContentType("text/html;charset=utf8");
			list.add(map1);
		}
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}

	/**
	 * 下载图片 预览图片
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("download")
	public void download(String fileName, HttpServletResponse response)
			throws IOException {
		String newpath = new String(fileName.getBytes("ISO-8859-1"), "utf-8");
		OutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ newpath);
			response.setContentType("image/jpeg; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(FileUpload.getFile(newpath)));
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 删除图片
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteimg", method = RequestMethod.GET)
	public void deleteconfig(ProjectImg proimg, Model model) {
		this.proImgService.deletebyimgid(proimg);
	}

	/**
	 * 删除某个类型的配置
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteconfig", method = RequestMethod.POST)
	public String deleteconfig(ProConfig conf, Model model) {
		this.proconfService.deleteconfig(conf);
		return null;
	}

}
