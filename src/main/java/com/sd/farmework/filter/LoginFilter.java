package com.sd.farmework.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sd.farmework.pojo.UserInfo;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        //System.out.println(path);
        
        // 从session里取员工工号信息
        UserInfo empId = (UserInfo) session.getAttribute("loginUser");
         
       String [] paths = {"admin/userLogin.do"};
      
        //创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < paths.length; i++) {

            if (path.indexOf(paths[i]) > -1) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        } 
        
        // 登陆页面无需过滤
        if(path.indexOf("/admin/login.do") > -1||path.equals("/dreamcardservice/")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (empId == null || "".equals(empId)) {
            // 跳转到登陆页面
        	
            servletResponse.sendRedirect(servletRequest.getContextPath()+"/admin/login.do");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

}