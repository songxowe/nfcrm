package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class EmployeeInfo extends BaseInfo{
	
	private String level_id;//编号
	private String customer_level;//姓名
	private String remark;//别名
	private String job_date;//入职日期
	private String birthday;//出生日期
	private String education;//学历
	private String tel;//电话号码
	private String qq_no;//QQ号码
	private String wx_no;//微信号码
	private String interview_time;//面试时间
	
	private String interview_job;//面试岗位
	private String probation_job;//试用岗位
	private String probation_time;//试用时间
	private String official_job;//转正岗位
	private String official_time;//转正时间
	private String resignation_time;//离职日期
	private String work_time;//在职时间
	private String status;//状态
	
	private String ancestral_home;//祖籍
	private String address;//现住址
	private String work_experience;//工作经历
	private String hobbies;//爱好
	private String work_direction;//工作意向
	private String electronic_resume_url;//电子简历
	private String electronic_photshop_url;//电子照片
	
	private String oneString;//通过拼接sql查询数据
	
	
	public String getOneString() {
		return oneString;
	}
	public void setOneString(String oneString) {
		this.oneString = oneString;
	}
	public String getLevel_id() {
		return level_id;
	}
	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}
	public String getCustomer_level() {
		return customer_level;
	}
	public void setCustomer_level(String customer_level) {
		this.customer_level = customer_level;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJob_date() {
		return job_date;
	}
	public void setJob_date(String job_date) {
		this.job_date = job_date;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq_no() {
		return qq_no;
	}
	public void setQq_no(String qq_no) {
		this.qq_no = qq_no;
	}
	public String getWx_no() {
		return wx_no;
	}
	public void setWx_no(String wx_no) {
		this.wx_no = wx_no;
	}
	public String getInterview_time() {
		return interview_time;
	}
	public void setInterview_time(String interview_time) {
		this.interview_time = interview_time;
	}
	public String getInterview_job() {
		return interview_job;
	}
	public void setInterview_job(String interview_job) {
		this.interview_job = interview_job;
	}
	public String getProbation_job() {
		return probation_job;
	}
	public void setProbation_job(String probation_job) {
		this.probation_job = probation_job;
	}
	public String getProbation_time() {
		return probation_time;
	}
	public void setProbation_time(String probation_time) {
		this.probation_time = probation_time;
	}
	public String getOfficial_job() {
		return official_job;
	}
	public void setOfficial_job(String official_job) {
		this.official_job = official_job;
	}
	public String getOfficial_time() {
		return official_time;
	}
	public void setOfficial_time(String official_time) {
		this.official_time = official_time;
	}
	public String getResignation_time() {
		return resignation_time;
	}
	public void setResignation_time(String resignation_time) {
		this.resignation_time = resignation_time;
	}
	public String getWork_time() {
		return work_time;
	}
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAncestral_home() {
		return ancestral_home;
	}
	public void setAncestral_home(String ancestral_home) {
		this.ancestral_home = ancestral_home;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWork_experience() {
		return work_experience;
	}
	public void setWork_experience(String work_experience) {
		this.work_experience = work_experience;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getWork_direction() {
		return work_direction;
	}
	public void setWork_direction(String work_direction) {
		this.work_direction = work_direction;
	}
	public String getElectronic_resume_url() {
		return electronic_resume_url;
	}
	public void setElectronic_resume_url(String electronic_resume_url) {
		this.electronic_resume_url = electronic_resume_url;
	}
	public String getElectronic_photshop_url() {
		return electronic_photshop_url;
	}
	public void setElectronic_photshop_url(String electronic_photshop_url) {
		this.electronic_photshop_url = electronic_photshop_url;
	}
	@Override
	public String toString() {
		return "EmployeeInfo [level_id=" + level_id + ", customer_level="
				+ customer_level + ", remark=" + remark + ", job_date="
				+ job_date + ", birthday=" + birthday + ", education="
				+ education + ", tel=" + tel + ", qq_no=" + qq_no + ", wx_no="
				+ wx_no + ", interview_time=" + interview_time
				+ ", interview_job=" + interview_job + ", probation_job="
				+ probation_job + ", probation_time=" + probation_time
				+ ", official_job=" + official_job + ", official_time="
				+ official_time + ", resignation_time=" + resignation_time
				+ ", work_time=" + work_time + ", status=" + status
				+ ", ancestral_home=" + ancestral_home + ", address=" + address
				+ ", work_experience=" + work_experience + ", hobbies="
				+ hobbies + ", work_direction=" + work_direction
				+ ", electronic_resume_url=" + electronic_resume_url
				+ ", electronic_photshop_url=" + electronic_photshop_url + "]";
	}
	
}
