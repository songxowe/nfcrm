package com.sd.farmework.pojo;

import java.io.Serializable;
import java.util.Date;

import com.sd.farmework.common.BaseInfo;

public class SysLogInfo extends BaseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5078935762170185298L;
	
	private String log_id;
	private String operate_person_id;
	private String operate_person_name;
	private String funciton_url;
	private String function_name;
	private String create_time;
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getOperate_person_id() {
		return operate_person_id;
	}
	public void setOperate_person_id(String operate_person_id) {
		this.operate_person_id = operate_person_id;
	}
	public String getOperate_person_name() {
		return operate_person_name;
	}
	public void setOperate_person_name(String operate_person_name) {
		this.operate_person_name = operate_person_name;
	}
	public String getFunciton_url() {
		return funciton_url;
	}
	public void setFunciton_url(String funciton_url) {
		this.funciton_url = funciton_url;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	
}
