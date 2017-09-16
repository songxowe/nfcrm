package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class SysApprovenFunction extends BaseInfo implements Serializable{
	/**
	 * 审批规则表
	 * @author yangyang 
	 * 2016-11-18
	 */
	private static final long serialVersionUID =2L;
	
 
 
	private String approvenFunctionId;//审批功能主键
	private String approvenFunctionName;//审批功能名称
	 
	private String remark;//审批功能描述

	public String getApprovenFunctionId() {
		return approvenFunctionId;
	}

	public void setApprovenFunctionId(String approvenFunctionId) {
		this.approvenFunctionId = approvenFunctionId;
	}

	public String getApprovenFunctionName() {
		return approvenFunctionName;
	}

	public void setApprovenFunctionName(String approvenFunctionName) {
		this.approvenFunctionName = approvenFunctionName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SysApprovenFunction [approvenFunctionId=" + approvenFunctionId
				+ ", approvenFunctionName=" + approvenFunctionName
				+ ", remark=" + remark + "]";
	}

 
}
