package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

/**
 * 角色新增
 * @author wangchaochao
 * 2016-11-2
 */
public class RoleInfo extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = 141892774617502630L;
	
	private String role_id;//角色id
 	private String role_name;//角色名称
	private String function_ids;//功能编号
	private String function_operate_ids;//功能操作菜单编号
	private String function_desc;//描述
	private String status;//是否启用
	private String isChecked;
	
	@Override
	public String toString() {
		return "RoleInfo [role_id=" + role_id + ", role_name=" + role_name
				+ ", function_ids=" + function_ids + ", function_operate_ids="
				+ function_operate_ids + ", function_desc=" + function_desc
				+ ", status=" + status + ", isChecked=" + isChecked + "]";
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getFunction_ids() {
		return function_ids;
	}
	public void setFunction_ids(String function_ids) {
		this.function_ids = function_ids;
	}
	public String getFunction_operate_ids() {
		return function_operate_ids;
	}
	public void setFunction_operate_ids(String function_operate_ids) {
		this.function_operate_ids = function_operate_ids;
	}
	public String getFunction_desc() {
		return function_desc;
	}
	public void setFunction_desc(String function_desc) {
		this.function_desc = function_desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
 

	
	


	
	 
	
	
}
