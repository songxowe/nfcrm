package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class ProjectImg extends BaseInfo{
	private String img_id;
	private String img_type;
	private String img_source;
	private String img_remark;
	private String img_proid;
	private String status;

	public String getImg_id() {
		return img_id;
	}

	public void setImg_id(String img_id) {
		this.img_id = img_id;
	}

	public String getImg_type() {
		return img_type;
	}

	public void setImg_type(String img_type) {
		this.img_type = img_type;
	}

	public String getImg_source() {
		return img_source;
	}

	public void setImg_source(String img_source) {
		this.img_source = img_source;
	}

	public String getImg_remark() {
		return img_remark;
	}

	public void setImg_remark(String img_remark) {
		this.img_remark = img_remark;
	}

	public String getImg_proid() {
		return img_proid;
	}

	public void setImg_proid(String img_proid) {
		this.img_proid = img_proid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProjectImg [img_id=" + img_id + ", img_type=" + img_type
				+ ", img_source=" + img_source + ", img_remark=" + img_remark
				+ ", img_proid=" + img_proid + ", status=" + status
				+ ", getImg_id()=" + getImg_id() + ", getImg_type()="
				+ getImg_type() + ", getImg_source()=" + getImg_source()
				+ ", getImg_remark()=" + getImg_remark() + ", getImg_proid()="
				+ getImg_proid() + ", getStatus()=" + getStatus()
				+ ", getStartRecod()=" + getStartRecod() + ", getCurrPage()="
				+ getCurrPage() + ", getPageSize()=" + getPageSize()
				+ ", getPageCount()=" + getPageCount() + ", getTotalCount()="
				+ getTotalCount() + ", getCreateTime()=" + getCreateTime()
				+ ", getCreateUserId()=" + getCreateUserId()
				+ ", getCreateUserName()=" + getCreateUserName()
				+ ", getLastUpdateTime()=" + getLastUpdateTime()
				+ ", getLastUpdateUserId()=" + getLastUpdateUserId()
				+ ", getLastUpdateUserName()=" + getLastUpdateUserName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
