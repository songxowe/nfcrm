package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class UserRole extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String user_id;//主键
	private String user_name;//
	private String role_id;
	private String role_name;// 
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "UserRole [user_id=" + user_id + ", user_name=" + user_name
				+ ", role_id=" + role_id + ", role_name=" + role_name + "]";
	}
	
	
}
