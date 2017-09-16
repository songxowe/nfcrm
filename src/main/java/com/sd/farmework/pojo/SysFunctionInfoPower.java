package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

/**
 * 权限菜单及操作按钮
 * @author wangchaochao
 * 2016-11-23
 */
public class SysFunctionInfoPower extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = -1689614204156489735L;
	
	private String funcitonId;
 	private String functionName;//中文菜单
	private String functionDesc;//功能描述
	private String parentFunctionId;//父功能编号
	private String remark;//备注
	private String functionSeque;//功能顺序
	private String status;//是否启用
	private String functionUrl;
	private String functionStyle;
	@Override
	public String toString() {
		return "SysFunctionInfoPower [funcitonId=" + funcitonId
				+ ", functionName=" + functionName + ", functionDesc="
				+ functionDesc + ", parentFunctionId=" + parentFunctionId
				+ ", remark=" + remark + ", functionSeque=" + functionSeque
				+ ", status=" + status + ", functionUrl=" + functionUrl
				+ ", functionStyle=" + functionStyle + "]";
	}
	public String getFuncitonId() {
		return funcitonId;
	}
	public void setFuncitonId(String funcitonId) {
		this.funcitonId = funcitonId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionDesc() {
		return functionDesc;
	}
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}
	public String getParentFunctionId() {
		return parentFunctionId;
	}
	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFunctionSeque() {
		return functionSeque;
	}
	public void setFunctionSeque(String functionSeque) {
		this.functionSeque = functionSeque;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFunctionUrl() {
		return functionUrl;
	}
	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	public String getFunctionStyle() {
		return functionStyle;
	}
	public void setFunctionStyle(String functionStyle) {
		this.functionStyle = functionStyle;
	}
	
	 
	
 

	
	

	
}
