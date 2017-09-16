package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class SysApprovenFunctionRule extends BaseInfo implements Serializable{
	/**
	 * 审批规则表
	 * @author yangyang 
	 * 2016-11-18
	 */
	private static final long serialVersionUID =2L;
	
 
	private Integer approvenRuleId;//规则编号
	private String approvenRuleName;//规则名
	private String approvenFunctionId;//功能ID
	private String approvenFunctionName;//功能名字
	private String ruleIsOrderly;//规则序号
	private String approvenRemark;//员工编号

	public String getApprovenFunctionName() {
		return approvenFunctionName;
	}
	public void setApprovenFunctionName(String approvenFunctionName) {
		this.approvenFunctionName = approvenFunctionName;
	}
	public Integer getApprovenRuleId() {
		return approvenRuleId;
	}
	public void setApprovenRuleId(Integer approvenRuleId) {
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
	public String getRuleIsOrderly() {
		return ruleIsOrderly;
	}
	public void setRuleIsOrderly(String ruleIsOrderly) {
		this.ruleIsOrderly = ruleIsOrderly;
	}
	public String getApprovenRemark() {
		return approvenRemark;
	}
	public void setApprovenRemark(String approvenRemark) {
		this.approvenRemark = approvenRemark;
	}
	@Override
	public String toString() {
		return "SysApprovenFunctionRule [approvenRuleId=" + approvenRuleId
				+ ", approvenRuleName=" + approvenRuleName
				+ ", approvenFunctionId=" + approvenFunctionId
				+ ", ruleIsOrderly=" + ruleIsOrderly + ", approvenRemark="
				+ approvenRemark + "]";
	}
	 
	
}
