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

public class WxFilter implements Filter {

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
        
         
      
      
       System.out.println(path);
       if(path.indexOf("weixin/vipawardValidata") > -1) {
       	System.out.println("code==="+request.getParameter("code"));
       	chain.doFilter(request, response);
       	return ;
       }
   	return ;

    }

    public void destroy() {
        // TODO Auto-generated method stub

    }

}