package com.sd.farmework.controller;

import java.io.IOException;
import java.io.OutputStream;

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

import com.sd.farmework.common.MD5Util;
import com.sd.farmework.common.MakeCertPic;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.UserInfoService;

/**
 * 用户登录
 * 
 * @author 王超超 2016-10-26
 * 
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/sys")
public class CodeController {
	private static Logger logger = Logger.getLogger(CodeController.class);

	/**
	 * 测试服务是否启动成功
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */

	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public String getCode(HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		OutputStream out = response.getOutputStream();

		String str = MakeCertPic.getCertPic(0, 0, out);

		session.setAttribute("code", str);

		response.setContentType("image/jpeg; charset=utf-8");

		out.write(str.getBytes());
		out.close();

		return null;
	}

}
