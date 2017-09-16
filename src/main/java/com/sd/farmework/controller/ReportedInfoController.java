package com.sd.farmework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sd.farmework.pojo.ReportedInfo;
import com.sd.farmework.pojo.RoleInfo;
import com.sd.farmework.service.CustomerListService;
import com.sd.farmework.service.ReportedInfoService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/reporte")
public class ReportedInfoController {
	private static Logger logger = Logger
			.getLogger(ReportedInfoController.class);
	@Autowired
	private ReportedInfoService reportedInfoService;
	@Autowired
	private CustomerListService customerInfoService;

	/**
	 * 报备列表
	 * 
	 * @param reporte
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reportedInfoList")
	public String reportedInfoList(ReportedInfo obj, Model model,
			HttpServletRequest request) {
		try {
			List list = reportedInfoService.queryList(obj);

			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					ReportedInfo reportedInfo = (ReportedInfo) list.get(i);
					String remark = reportedInfo.getRemark();
					if (remark.length() >= 11) {
						StringBuffer updateRemark = new StringBuffer();
						updateRemark.append(remark.substring(0, 3));
						updateRemark.append("-");
						updateRemark.append(remark.substring(7));
						reportedInfo.setRemark(updateRemark.toString());
					}
				}
			}
			model.addAttribute("list", list);
			RoleInfo sessionRole = (RoleInfo) request.getSession()
					.getAttribute("roleInfo");
			String function_id = request.getParameter("function_id");
			logger.info("权限信息：" + sessionRole.toString() + "\t 功能id："
					+ function_id);
			model.addAttribute("sessionRole", sessionRole);
			model.addAttribute("function_id", function_id);
			obj.setTotalCount(reportedInfoService.queryCount(obj));
			model.addAttribute("totalCount", obj.getPageCount());
			model.addAttribute("currPage", obj.getCurrPage());
			model.addAttribute("obj", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "informationreported/reportedlist";
	}

}
