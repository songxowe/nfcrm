package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class ReportedInfo extends BaseInfo{
	
	private String reported_id;//编号
	private String t_id;
	private String reported_yw;//
	private String reported_time;//
	private String end_time;
	private String reported_no;//
	private String reported_customer;//
	private String remark;//
	private String pro_name;//
	private String company;//
	private String customer_id;//客户ID
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getReported_id() {
		return reported_id;
	}
	public void setReported_id(String reported_id) {
		this.reported_id = reported_id;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getReported_yw() {
		return reported_yw;
	}
	public void setReported_yw(String reported_yw) {
		this.reported_yw = reported_yw;
	}
	public String getReported_time() {
		return reported_time;
	}
	public void setReported_time(String reported_time) {
		this.reported_time = reported_time;
	}
	public String getReported_no() {
		return reported_no;
	}
	public void setReported_no(String reported_no) {
		this.reported_no = reported_no;
	}
	public String getReported_customer() {
		return reported_customer;
	}
	public void setReported_customer(String reported_customer) {
		this.reported_customer = reported_customer;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "ReportedInfo [reported_id=" + reported_id + ", t_id=" + t_id
				+ ", reported_yw=" + reported_yw + ", reported_time="
				+ reported_time + ", reported_no=" + reported_no
				+ ", reported_customer=" + reported_customer + ", remark="
				+ remark + ", pro_name=" + pro_name + ", company=" + company
				+ "]";
	}
	
}
