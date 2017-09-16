package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class ProConfig extends BaseInfo{
	private String conf_id;
	private String conf_type;
	private String conf_name;
	private String conf_proid;
	private String status;

	public String getConf_id() {
		return conf_id;
	}

	public void setConf_id(String conf_id) {
		this.conf_id = conf_id;
	}

	public String getConf_type() {
		return conf_type;
	}

	public void setConf_type(String conf_type) {
		this.conf_type = conf_type;
	}

	public String getConf_name() {
		return conf_name;
	}

	public void setConf_name(String conf_name) {
		this.conf_name = conf_name;
	}

	public String getConf_proid() {
		return conf_proid;
	}

	public void setConf_proid(String conf_proid) {
		this.conf_proid = conf_proid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProConfig [conf_id=" + conf_id + ", conf_type=" + conf_type
				+ ", conf_name=" + conf_name + ", conf_proid=" + conf_proid
				+ ", status=" + status + ", getConf_id()=" + getConf_id()
				+ ", getConf_type()=" + getConf_type() + ", getConf_name()="
				+ getConf_name() + ", getConf_proid()=" + getConf_proid()
				+ ", getStatus()=" + getStatus() + ", getStartRecod()="
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
