package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class DateDictionaryInfo extends BaseInfo implements Serializable{
	/**
	 * 数据字典
	 * @author
	 * 
	 */
	private static final long serialVersionUID =5L;
	
	private Integer d_id;//
	private String d_key;//
	private String d_value;//
	private String d_type;//
	private String create_person;
	private String function_type;//
	private String function_type_name;//
	
	private String d_id_string;//表示组合的id如(1,2,3)
	
	public String getD_id_string() {
		return d_id_string;
	}
	public void setD_id_string(String d_id_string) {
		this.d_id_string = d_id_string;
	}
	public Integer getD_id() {
		return d_id;
	}
	public void setD_id(Integer d_id) {
		this.d_id = d_id;
	}
	public String getD_key() {
		return d_key;
	}
	public void setD_key(String d_key) {
		this.d_key = d_key;
	}
	public String getD_value() {
		return d_value;
	}
	public void setD_value(String d_value) {
		this.d_value = d_value;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	public String getCreate_person() {
		return create_person;
	}
	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}
	public String getFunction_type() {
		return function_type;
	}
	public void setFunction_type(String function_type) {
		this.function_type = function_type;
	}
	public String getFunction_type_name() {
		return function_type_name;
	}
	public void setFunction_type_name(String function_type_name) {
		this.function_type_name = function_type_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DateDictionaryInfo [d_id=" + d_id + ", d_key=" + d_key
				+ ", d_value=" + d_value + ", d_type=" + d_type
				+ ", create_person=" + create_person + 
				", function_type=" + function_type
				+ ", function_type_name=" + function_type_name + "]";
	}
	
	
}
