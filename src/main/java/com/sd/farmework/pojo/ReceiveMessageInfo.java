package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class ReceiveMessageInfo extends BaseInfo implements Serializable{
	/**
	 * 考勤信息
	 * @author wangchaochao 
	 * 2016-10-28
	 */
	private static final long serialVersionUID =8L;
	
	private String receive_id;//
	private String pos_name;//
	private String employee;//
	private String employee_no;
	private String recipient;
	private String recipient_no;
	private String send_time;//
	private String status;
	private String context;
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipient_no() {
		return recipient_no;
	}
	public void setRecipient_no(String recipient_no) {
		this.recipient_no = recipient_no;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ReceiveMessageInfo [receive_id=" + receive_id + ", pos_name="
				+ pos_name + ", employee=" + employee + ", employee_no="
				+ employee_no + ", recipient=" + recipient + ", recipient_no="
				+ recipient_no + ", send_time=" + send_time + ", status="
				+ status + ", context=" + context + "]";
	}

}