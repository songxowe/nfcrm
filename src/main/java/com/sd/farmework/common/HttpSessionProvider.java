package com.sd.farmework.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * HttpSession提供类
 * 
 * @ClassName: HttpSessionProvider
 * @Description: TODO
 * @author 王超超
 * @date 2016-11-17 下午03:45:35
 * 
 */
public class HttpSessionProvider implements SessionProvider {

	/**
	 * 获取session对象
	 * @param request,name
	 * @Description: TODO
	 * @author 王超超
	 * @date 2016-11-17 下午03:45:35
	 * 
	 */
	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Serializable) session.getAttribute(name);
		} else {
			return null;
		}
	}

	
	/**
	 * 设置session对象
	 * @param request,name
	 * @Description: TODO
	 * @author 王超超
	 * @date 2016-11-17 下午03:45:35
	 * 
	 */
	public void setAttribute(HttpServletRequest request, String name,
			Serializable value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

	/**
	 * 获取session ID
	 * @param request
	 * @Description: TODO
	 * @author 王超超
	 * @date 2016-11-17 下午03:45:35
	 * 
	 */
	public Serializable getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Serializable) session.getId();
		} else {
			return null;
		}
	}

	/**
	 * 销毁session
	 * @param request
	 * @Description: TODO
	 * @author 王超超
	 * @date 2016-11-17 下午03:45:35
	 * 
	 */
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
	
	/**
	 * 移除指定session对象
	 * @param request
	 * @Description: TODO
	 * @author 王超超
	 * @date 2016-11-17 下午03:45:35
	 * 
	 */
	public void removeAttribute(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(key);
		}
	}

}
