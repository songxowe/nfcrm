package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class UserAdmin extends BaseInfo implements Serializable {
	/**
	 * 用户信息
	 * @author wangchaochao 
	 * 2016-10-28
	 */
	private static final long serialVersionUID = 1484604668262254249L;
	private String user_id;
	private String user_name;
	private String employee_id;
	private String user_no;
	private String account_no;
	private String user_pwd;
	private String employ_time;
	private String user_group;
	private String remark;
	private String status;
	private String parentId;
	private String parentName;
	private String groupName;

	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

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

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getEmploy_time() {
		return employ_time;
	}

	public void setEmploy_time(String employ_time) {
		this.employ_time = employ_time;
	}

	public String getUser_group() {
		return user_group;
	}

	public void setUser_group(String user_group) {
		this.user_group = user_group;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAdmin [user_id=" + user_id + ", user_name=" + user_name
				+ ", employee_id=" + employee_id + ", user_no=" + user_no
				+ ", account_no=" + account_no + ", user_pwd=" + user_pwd
				+ ", employ_time=" + employ_time + ", user_group=" + user_group
				+ ", remark=" + remark + ", status=" + status + ", parentId="
				+ parentId + ", parentName=" + parentName + "]";
	}
	
}
