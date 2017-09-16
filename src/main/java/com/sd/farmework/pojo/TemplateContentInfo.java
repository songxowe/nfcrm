package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class TemplateContentInfo extends BaseInfo implements Serializable{
	/**
	 * 模板信息
	 * @author 
	 * 
	 */
	private static final long serialVersionUID =6L;
	
	private String t_content_id;
	private String t_id;//模板id
	private String template_name;//模板名称
	private String key_name;
	private String key_source_id;
	private String value;
	private String value_source_id;
	private String seque;
	private String status;
	public String getT_content_id() {
		return t_content_id;
	}
	public void setT_content_id(String t_content_id) {
		this.t_content_id = t_content_id;
	}
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
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	public String getKey_source_id() {
		return key_source_id;
	}
	public void setKey_source_id(String key_source_id) {
		this.key_source_id = key_source_id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue_source_id() {
		return value_source_id;
	}
	public void setValue_source_id(String value_source_id) {
		this.value_source_id = value_source_id;
	}
	public String getSeque() {
		return seque;
	}
	public void setSeque(String seque) {
		this.seque = seque;
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
		return "TemplateContentInfo [t_content_id=" + t_content_id + ", t_id="
				+ t_id + ", template_name=" + template_name + ", key_name="
				+ key_name + ", key_source_id=" + key_source_id + ", value="
				+ value + ", value_source_id=" + value_source_id + ", seque="
				+ seque + ", status=" + status + "]";
	}
	
}
