package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

/**
 * 用户角色关系映射
 * @author wangchaochao
 * 2016-11-10
 */
public class UserAndRoles extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = 141892774617502630L;
	
	private String r_id;//角色id
 	private String user_id;//用户id
	private String role_id;//角色id
	private String status;//是否启用
	
	@Override
	public String toString() {
		return "UserAndRoles [r_id=" + r_id + ", user_id=" + user_id
				+ ", role_id=" + role_id + ", status=" + status + "]";
	}
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	


	
	 
	
	
}
