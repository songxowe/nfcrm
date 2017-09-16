package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class AttendInfo extends BaseInfo implements Serializable {
	/**
	 * 考勤信息
	 * 
	 * @author wangchaochao 2016-10-28
	 */
	private static final long serialVersionUID = 1L;

	private Integer atte_id;//主键
	private String employee_id;//员工id 作为唯一标识
	private String employee_no;// 员工编号
	private String employee_name;// 员工姓名
	private String punch_time;// 打卡时间
	private String status;
	
	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public Integer getAtte_id() {
		return atte_id;
	}

	public void setAtte_id(Integer atte_id) {
		this.atte_id = atte_id;
	}

	public String getEmployee_no() {
		return employee_no;
	}

	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getPunch_time() {
		return punch_time;
	}

	public void setPunch_time(String punch_time) {
		this.punch_time = punch_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AttendInfo [atte_id=" + atte_id + ", employee_id="
				+ employee_id + ", employee_no=" + employee_no
				+ ", employee_name=" + employee_name + ", punch_time="
				+ punch_time + ", status=" + status + "]";
	}


}
