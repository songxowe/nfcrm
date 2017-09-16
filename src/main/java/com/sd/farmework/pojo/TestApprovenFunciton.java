package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

public class TestApprovenFunciton extends BaseInfo implements Serializable{
	/**
	 * 审批流测试类
	 * @author wangchaochao 
	 * 2016-10-28
	 */
	private static final long serialVersionUID =2L;
	
	private String testId;//
	private String testName;//员工编号
	private String testRemark;//签到时间
	//参考数据库备注
	private String step;//签退时间
	 
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestRemark() {
		return testRemark;
	}
	public void setTestRemark(String testRemark) {
		this.testRemark = testRemark;
	}
	public String getStep() {
		return step;
	}
	@Override
	public String toString() {
		return "TestApprovenFunciton [testId=" + testId + ", testName="
				+ testName + ", testRemark=" + testRemark + ", step=" + step
				+ "]";
	}
	public void setStep(String step) {
		this.step = step;
	}
	 
	
	
	
}
