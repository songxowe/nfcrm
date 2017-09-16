package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class EmployeeSalary extends BaseInfo{
	private String rolesalary_id;//主键
	private String user_id;//角色id
	private String user_name;//角色名称
	private String basic_salary;//基本工资
	private String subsidy;//
	private String commission;//
	private String integral_reward;
	private String take_look;
	private String bill_award;
	private String attend;
	private String other;
	private String count;
	private String status;//客户类型
	public String getRolesalary_id() {
		return rolesalary_id;
	}
	public void setRolesalary_id(String rolesalary_id) {
		this.rolesalary_id = rolesalary_id;
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
	public String getBasic_salary() {
		return basic_salary;
	}
	public void setBasic_salary(String basic_salary) {
		this.basic_salary = basic_salary;
	}
	public String getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getIntegral_reward() {
		return integral_reward;
	}
	public void setIntegral_reward(String integral_reward) {
		this.integral_reward = integral_reward;
	}
	public String getTake_look() {
		return take_look;
	}
	public void setTake_look(String take_look) {
		this.take_look = take_look;
	}
	public String getBill_award() {
		return bill_award;
	}
	public void setBill_award(String bill_award) {
		this.bill_award = bill_award;
	}
	public String getAttend() {
		return attend;
	}
	public void setAttend(String attend) {
		this.attend = attend;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "EmployeeSalary [rolesalary_id=" + rolesalary_id + ", user_id="
				+ user_id + ", user_name=" + user_name + ", basic_salary="
				+ basic_salary + ", subsidy=" + subsidy + ", commission="
				+ commission + ", integral_reward=" + integral_reward
				+ ", take_look=" + take_look + ", bill_award=" + bill_award
				+ ", attend=" + attend + ", other=" + other + ", status="
				+ status + "]";
	}
	
}
