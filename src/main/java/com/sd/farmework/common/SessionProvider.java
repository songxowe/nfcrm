package com.sd.farmework.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * Session提供者
  * @ClassName: SessionProvider
  * @Description: TODO
  * @author 王超超
  * @date 2016-11-17 下午03:45:23
  *
 */
public interface SessionProvider {

	public Serializable getAttribute(HttpServletRequest request, String name);

	public void setAttribute(HttpServletRequest request,String name, Serializable value);

	public Serializable getSessionId(HttpServletRequest request);

	public void logout(HttpServletRequest request );
	
}
