package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class SmSInfo extends BaseInfo implements Serializable{
	/**
	 * 信息发送
	 * @author  
	 * 2016-10-28
	 */
	private static final long serialVersionUID =15L;
	
	private String sms_id;//
	private String service_person;//
	private String service_no;//
	private String send_time;//
	private String customer_id;//
	private String customer_name;//
	private String topic;//
	private String context;//
	public String getSms_id() {
		return sms_id;
	}
	public void setSms_id(String sms_id) {
		this.sms_id = sms_id;
	}
	public String getService_person() {
		return service_person;
	}
	public void setService_person(String service_person) {
		this.service_person = service_person;
	}
	public String getService_no() {
		return service_no;
	}
	public void setService_no(String service_no) {
		this.service_no = service_no;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
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
		return "SmSInfo [sms_id=" + sms_id + ", service_person="
				+ service_person + ", service_no=" + service_no
				+ ", send_time=" + send_time + ", customer_id=" + customer_id
				+ ", customer_name=" + customer_name + ", topic=" + topic
				+ ", context=" + context + "]";
	}

}