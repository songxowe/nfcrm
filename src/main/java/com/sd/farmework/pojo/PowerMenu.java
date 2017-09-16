package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

/**
 * 权限菜单
 * @author wangchaochao
 * 2016-10-28
 */
public class PowerMenu extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = -1689614204156489735L;
	
	private String funciton_id;
 	private String function_name;//中文菜单
	private String function_desc;//功能描述
	private String parent_function_id;//父功能编号
	private String remark;//备注
	private String function_seque;//功能顺序
	private String status;//是否启用
	private String create_person_id;
	private String create_person;
	private String create_time;
	private String last_update_id;
	private String last_update_person;
	private String last_update_time;
	private String function_url;
	private String isShow;
	private String function_style;
	
	 
	
 

	
	

	@Override
	public String toString() {
		return "PowerMenu [funciton_id=" + funciton_id + ", function_name="
				+ function_name + ", function_desc=" + function_desc
				+ ", parent_function_id=" + parent_function_id + ", remark="
				+ remark + ", function_seque=" + function_seque + ", status="
				+ status + ", create_person_id=" + create_person_id
				+ ", create_person=" + create_person + ", create_time="
				+ create_time + ", last_update_id=" + last_update_id
				+ ", last_update_person=" + last_update_person
				+ ", last_update_time=" + last_update_time + ", function_url="
				+ function_url + ", isShow=" + isShow + ", function_style="
				+ function_style + "]";
	}



	



	public String getFunction_style() {
		return function_style;
	}







	public void setFunction_style(String function_style) {
		this.function_style = function_style;
	}







	public String getIsShow() {
		return isShow;
	}



	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}



	public String getFunction_url() {
		return function_url;
	}



	public void setFunction_url(String function_url) {
		this.function_url = function_url;
	}



	public String getFunciton_id() {
		return funciton_id;
	}



	public void setFunciton_id(String funciton_id) {
		this.funciton_id = funciton_id;
	}



	public String getFunction_name() {
		return function_name;
	}



	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}



	public String getFunction_desc() {
		return function_desc;
	}



	public void setFunction_desc(String function_desc) {
		this.function_desc = function_desc;
	}



	public String getParent_function_id() {
		return parent_function_id;
	}



	public void setParent_function_id(String parent_function_id) {
		this.parent_function_id = parent_function_id;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getFunction_seque() {
		return function_seque;
	}



	public void setFunction_seque(String function_seque) {
		this.function_seque = function_seque;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getCreate_person_id() {
		return create_person_id;
	}



	public void setCreate_person_id(String create_person_id) {
		this.create_person_id = create_person_id;
	}



	public String getCreate_person() {
		return create_person;
	}



	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}



	public String getCreate_time() {
		return create_time;
	}



	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}



	public String getLast_update_id() {
		return last_update_id;
	}



	public void setLast_update_id(String last_update_id) {
		this.last_update_id = last_update_id;
	}



	public String getLast_update_person() {
		return last_update_person;
	}



	public void setLast_update_person(String last_update_person) {
		this.last_update_person = last_update_person;
	}



	public String getLast_update_time() {
		return last_update_time;
	}



	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	 

	
	
	 
	
	
}
