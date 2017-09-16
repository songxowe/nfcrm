package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class AttendDetailInfo extends BaseInfo implements Serializable {
	/**
	 * 考勤详情信息
	 * 
	 * @author wangchaochao 2016-10-28
	 */
	private static final long serialVersionUID = 2L;

	private Integer att_detail_id;//主键
	private String employee_id;//员工id 作为唯一标识
	private String employee_no;// 员工编号
	private String employee_name;// 员工名称
	private String sign_time;// 签到时间
	private String sign_back_time;// 签退时间
	private String num;// 天数
	private String lacknum;// 缺勤天数
	private String status;
	
	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public Integer getAtt_detail_id() {
		return att_detail_id;
	}

	public void setAtt_detail_id(Integer att_detail_id) {
		this.att_detail_id = att_detail_id;
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

	public String getSign_time() {
		return sign_time;
	}

	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}

	public String getSign_back_time() {
		return sign_back_time;
	}

	public void setSign_back_time(String sign_back_time) {
		this.sign_back_time = sign_back_time;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public String getLacknum() {
		return lacknum;
	}

	public void setLacknum(String lacknum) {
		this.lacknum = lacknum;
	}

	@Override
	public String toString() {
		return "AttendDetailInfo [att_detail_id=" + att_detail_id
				+ ", employee_id=" + employee_id + ", employee_no="
				+ employee_no + ", employee_name=" + employee_name
				+ ", sign_time=" + sign_time + ", sign_back_time="
				+ sign_back_time + ", num=" + num + ", lacknum=" + lacknum
				+ ", status=" + status + ", getEmployee_id()="
				+ getEmployee_id() + ", getAtt_detail_id()="
				+ getAtt_detail_id() + ", getEmployee_no()=" + getEmployee_no()
				+ ", getEmployee_name()=" + getEmployee_name()
				+ ", getSign_time()=" + getSign_time()
				+ ", getSign_back_time()=" + getSign_back_time()
				+ ", getNum()=" + getNum() + ", getStatus()=" + getStatus()
				+ ", getLacknum()=" + getLacknum() + ", getStartRecod()="
				+ getStartRecod() + ", getCurrPage()=" + getCurrPage()
				+ ", getPageSize()=" + getPageSize() + ", getPageCount()="
				+ getPageCount() + ", getTotalCount()=" + getTotalCount()
				+ ", getCreateTime()=" + getCreateTime()
				+ ", getCreateUserId()=" + getCreateUserId()
				+ ", getCreateUserName()=" + getCreateUserName()
				+ ", getLastUpdateTime()=" + getLastUpdateTime()
				+ ", getLastUpdateUserId()=" + getLastUpdateUserId()
				+ ", getLastUpdateUserName()=" + getLastUpdateUserName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}


}
