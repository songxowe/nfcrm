package com.sd.farmework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sd.farmework.common.util.ExcelUtil;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.service.CustomerListService;

/**
 * 导表接口
 * 
 * @author xsj
 * 
 */
@Controller
@RequestMapping(value = "/exportExcelFile")
public class ExportExcelFileController {
	@Autowired
	CustomerListService customerInfoService;

	@RequestMapping(value = "/exportFile")
	public String exportFile(ServletResponse response) throws Exception {
		List<CustomerInfo> querynopreList = customerInfoService.querynopre();

		String outPutPath = "d:/export/";
		Long fileName = System.currentTimeMillis();
		if (querynopreList != null && querynopreList.size() != 0) {

			List<String> topList = new ArrayList<String>();
			topList.add("客户姓名:");
			topList.add("客户等级");
			topList.add("客户类别");
			topList.add("手机号码:");
			topList.add("客户主推项目");
			topList.add("客户来源");
			topList.add("推荐人姓名");
			topList.add("归属人");
			topList.add("客户预算");
			topList.add("客户生日");
			List<List<String>> allDataList = new ArrayList<List<String>>();
			allDataList.add(topList);
			for (int i = 0; i < querynopreList.size(); i++) {
				List<String> dataList = new ArrayList<String>();
				dataList.add(querynopreList.get(i).getCustomer_name());
				dataList.add(querynopreList.get(i).getCustomer_level());
				if (querynopreList.get(i).getCustomer_type().equals("0")) {
					dataList.add("私有客户");
				} else if (querynopreList.get(i).getCustomer_type().equals("1")) {
					dataList.add("公共客户");
				} else if (querynopreList.get(i).getCustomer_type().equals("2")) {
					dataList.add("成交客户");
				} else {
					dataList.add("带看客户");
				}
				dataList.add(querynopreList.get(i).getPhone());
				dataList.add(querynopreList.get(i).getPro_name());
				dataList.add(querynopreList.get(i).getCustomer_source());
				dataList.add(querynopreList.get(i).getRecommend_customer());
				dataList.add(querynopreList.get(i).getAdscription());
				dataList.add(querynopreList.get(i).getCustomer_budget_min()
						+ "-" + querynopreList.get(i).getCustomer_budget_max());
				dataList.add(querynopreList.get(i).getCustomer_birthday());
				allDataList.add(dataList);
			}
			String fileType = "xls";
			boolean f = ExcelUtil.CreateXslFile(outPutPath, fileName + "",
					fileType, allDataList);

			PrintWriter out = null;
			try {
				out = response.getWriter();
				if (f) {
					out.write("t");
				} else {

					out.write("f");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				out.close();
			}

		}
		return null;

	}
}
