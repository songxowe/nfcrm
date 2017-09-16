package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

/**
 * 用户信息
 * @author wangchaochao
 * 2016-10-28
 */
public class UserInfo extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = -1689614204156489735L;
	
	private String user_id;//用户编号
 	private String user_name;//姓名
 	private String employee_id;//员工编号
	private String user_no;//编号
	private String account_no;//账号
	private String user_pwd;//密码
	private String employ_time;//录入时间
	private String user_group;//所属分组
	private String remark;//备注
	private String status;//权限状态

	private String create_time;//创建时间
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


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}


	public String getCreate_person() {
		return create_person;
	}


	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}


	public String getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}


	public String getUpdate_person() {
		return update_person;
	}


	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}


	private String create_person;//创建人
	private String update_time ;//修改时间
	private String update_person;//修改人
	@Override
	public String toString() {
		return "UserInfo [user_id=" + user_id + ", user_name=" + user_name
				+ ", empyolee_id=" + employee_id + ", user_no=" + user_no
				+ ", account_no=" + account_no + ", user_pwd=" + user_pwd
				+ ", employ_time=" + employ_time + ", user_group=" + user_group
				+ ", remark=" + remark + ", status=" + status
				+ ", create_time=" + create_time + ", create_person="
				+ create_person + ", update_time=" + update_time
				+ ", update_person=" + update_person + "]";
	}
	 
}
