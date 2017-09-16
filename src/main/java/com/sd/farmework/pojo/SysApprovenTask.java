package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class SysApprovenTask extends BaseInfo implements Serializable{
	/**
	 * 考勤详情信息
	 * @author wangchaochao 
	 * 2016-10-28
	 */
	private static final long serialVersionUID =2L;
	
	private Integer taskId;//任务ID
	private String taskName;//任务名称
	private String approvenPersonId;//审批人规则ID
	private String approvenRuleId;//审批规则ID
	private String approvenRuleName;//规则名称
	private String approvenFunctionId;//审批功能编号
	private String approvenFunctionName;//审批功能名称
	private String approvenUserId;//审批人ID
	private String approvenUserName;//审批人姓名
	private String ruleOrder;//审批人顺序
	private String ruleOrderOld;//原始序号
	private String approvenSendTime;//审批发起时间
	private String approvenEndTime;//审批结束时间
	private String approvenResult;//审批结果
	private String approvenStatus;//当前状态
	private String sourceTable;//数据来源表名称
	private String sourceTablePkColumnName;//数据来源表主键名称
	private String sourceTablePkColumnValue;//数据来源表主键对应数据
	private String approvenRemark;//批注
	
	 
	public String getRuleOrderOld() {
		return ruleOrderOld;
	}
	public void setRuleOrderOld(String ruleOrderOld) {
		this.ruleOrderOld = ruleOrderOld;
	}
	public String getApprovenRemark() {
		return approvenRemark;
	}
	public void setApprovenRemark(String approvenRemark) {
		this.approvenRemark = approvenRemark;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getApprovenPersonId() {
		return approvenPersonId;
	}
	public void setApprovenPersonId(String approvenPersonId) {
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
	public void setRuleOrder(String ruleOrder) {
		this.ruleOrder = ruleOrder;
	}
	public String getApprovenSendTime() {
		return approvenSendTime;
	}
	public void setApprovenSendTime(String approvenSendTime) {
		this.approvenSendTime = approvenSendTime;
	}
	public String getApprovenEndTime() {
		return approvenEndTime;
	}
	public void setApprovenEndTime(String approvenEndTime) {
		this.approvenEndTime = approvenEndTime;
	}

	public String getApprovenResult() {
		return approvenResult;
	}
	public void setApprovenResult(String approvenResult) {
		this.approvenResult = approvenResult;
	}
	public String getApprovenStatus() {
		return approvenStatus;
	}
	public void setApprovenStatus(String approvenStatus) {
		this.approvenStatus = approvenStatus;
	}
	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getSourceTablePkColumnName() {
		return sourceTablePkColumnName;
	}
	public void setSourceTablePkColumnName(String sourceTablePkColumnName) {
		this.sourceTablePkColumnName = sourceTablePkColumnName;
	}
	public String getSourceTablePkColumnValue() {
		return sourceTablePkColumnValue;
	}
	public void setSourceTablePkColumnValue(String sourceTablePkColumnValue) {
		this.sourceTablePkColumnValue = sourceTablePkColumnValue;
	}
 
 
	
}
