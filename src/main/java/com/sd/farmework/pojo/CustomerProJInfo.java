package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class CustomerProJInfo extends BaseInfo{
	
	private String c_id;//主键
	private String pro_id;//
	private String customer_id;//客户id
	private String pro_name;//客户名称
	private String pro_type;//客户类型
	private String status;//状态
	
	
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_type() {
		return pro_type;
	}
	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerProJInfo [pro_id=" + pro_id + ", customer_id="
				+ customer_id + ", pro_name=" + pro_name + ", pro_type="
				+ pro_type + ", status=" + status + "]";
	}
	
}
