package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class SysApprovenRulePerson extends BaseInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID =2L;
	
	private Integer approvenPersonId;//
	private String approvenRuleId;//规则ID
	private String approvenRuleName;//规则名称
	private String approvenFunctionId;//审批功能编号
	private String approvenFunctionName;//审批功能名称
	private String approvenUserId;//审批人ID
	private String approvenUserName;//审批人姓名
	private String ruleOrder;//审批人顺序
	private String  ruleType;//规则类型
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleExp() {
		return ruleExp;
	}
	public void setRuleExp(String ruleExp) {
		this.ruleExp = ruleExp;
	}
	private String ruleExp;//员工编号
	public Integer getApprovenPersonId() {
		return approvenPersonId;
	}
	public void setApprovenPersonId(Integer approvenPersonId) {
		this.approvenPersonId = approvenPersonId;
	}
	public String getApprovenRuleId() {
		return approvenRuleId;
	}
	public void setApprovenRuleId(String approvenRuleId) {
		this.approvenRuleId = approvenRuleId;
	}
	public String getApprovenRuleName() {
		return approvenRuleName;
	}
	public void setApprovenRuleName(String approvenRuleName) {
		this.approvenRuleName = approvenRuleName;
	}
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
	
	public String getRuleOrder() {
		return ruleOrder;
	}
	public void setRuleOrder(String ruleOrder) {
		this.ruleOrder = ruleOrder;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getApprovenUserId() {
		return approvenUserId;
	}
	public void setApprovenUserId(String approvenUserId) {
		this.approvenUserId = approvenUserId;
	}
	public String getApprovenUserName() {
		return approvenUserName;
	}
	public void setApprovenUserName(String approvenUserName) {
		this.approvenUserName = approvenUserName;
	}
	@Override
	public String toString() {
		return "SysApprovenRulePerson [approvenPersonId=" + approvenPersonId
				+ ", approvenRuleId=" + approvenRuleId + ", approvenRuleName="
				+ approvenRuleName + ", approvenFunctionId="
				+ approvenFunctionId + ", approvenFunctionName="
				+ approvenFunctionName + ", approvenUserId=" + approvenUserId
				+ ", approvenUserName=" + approvenUserName + ", ruleOrder="
				+ ruleOrder + "]";
	}
	 
	
	
}
