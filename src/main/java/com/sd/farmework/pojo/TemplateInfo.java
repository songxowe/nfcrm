package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class TemplateInfo extends BaseInfo implements Serializable{
	/**
	 * 模板信息
	 * @author 
	 * 
	 */
	private static final long serialVersionUID =3L;
	
	private String t_id;//模板id
	private String template_name;//模板名称
	private String status;
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
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
		return "TemplateInfo [t_id=" + t_id + ", template_name="
				+ template_name + ", status=" + status + "]";
	}
	
}
