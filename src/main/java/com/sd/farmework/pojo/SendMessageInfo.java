package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class SendMessageInfo extends BaseInfo implements Serializable{
	/**
	 * 信息发送
	 * @author  
	 * 2016-10-28
	 */
	private static final long serialVersionUID =11L;
	
	private String send_id;//
	private String send_person;//
	private String recipient_id;//
	private String recipient;//
	private String title;//
	private String context;
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getSend_person() {
		return send_person;
	}
	public void setSend_person(String send_person) {
		this.send_person = send_person;
	}
	public String getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		return "SendMessageInfo [send_id=" + send_id + ", send_person="
				+ send_person + ", recipient_id=" + recipient_id
				+ ", recipient=" + recipient + ", title=" + title
				+ ", context=" + context + "]";
	}

}