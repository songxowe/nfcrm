package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class RolesalaryFormula extends BaseInfo{
	private String rolesalary_id;//主键
	private String role_id;//角色id
	private String role_name;//角色名称
	private String basic_salary;//基本工资
	private String subsidy;//补贴
	private String first_monad;//提成第一单
	private String second_monad;//第二单
	private String third_monad;//第三单
	private String fourth_monad;//第四单
	private String fifth_monad;//第五单
	private String sixth_monad;//第六单
	private String seventh_monad;//第七单
	private String eighth_monad;//第八单
	private String ninth_monad;//第九单
	private String tenth_monad;//第十单
	private String eleventh_monad;//第十一单
	private String eleven_more;//十一单以上
	public String getEleven_more() {
		return eleven_more;
	}
	public void setEleven_more(String eleven_more) {
		this.eleven_more = eleven_more;
	}
	private String integral_reward_first;//第一积分等级（0~15分）
	private String integral_reward_second;//第二积分等级（15分以上）
	private String take_look_num;//带看数量
	private String take_look_amount;//带看金额
	private String take_look_singlegroup;//单组金额
	private String bill_award_first;//开单第一等级（0~50W元）
	private String bill_award_third;//开单第二等级（51~100W元）
	private String bill_award_second;//开单第三等级（101~200W元）
	private String bill_award_fourth;//开单第四等级（201~300W元）
	private String bill_award_fifth;//开单第五等级（301~400W元）
	private String bill_award_sixth;//开单第六等级（401~500W元）
	private String status;//客户类型
	@Override
	public String toString() {
		return "RolesalaryFormula [rolesalary_id=" + rolesalary_id
				+ ", role_id=" + role_id + ", role_name=" + role_name
				+ ", basic_salary=" + basic_salary + ", subsidy=" + subsidy
				+ ", first_monad=" + first_monad + ", second_monad="
				+ second_monad + ", third_monad=" + third_monad
				+ ", fourth_monad=" + fourth_monad + ", fifth_monad="
				+ fifth_monad + ", sixth_monad=" + sixth_monad
				+ ", seventh_monad=" + seventh_monad + ", eighth_monad="
				+ eighth_monad + ", ninth_monad=" + ninth_monad
				+ ", tenth_monad=" + tenth_monad + ", eleventh_monad="
				+ eleventh_monad + ", integral_reward_first="
				+ integral_reward_first + ", integral_reward_second="
				+ integral_reward_second + ", take_look_num=" + take_look_num
				+ ", take_look_amount=" + take_look_amount
				+ ", take_look_singlegroup=" + take_look_singlegroup
				+ ", bill_award_first=" + bill_award_first
				+ ", bill_award_third=" + bill_award_third
				+ ", bill_award_second=" + bill_award_second
				+ ", bill_award_fourth=" + bill_award_fourth
				+ ", bill_award_fifth=" + bill_award_fifth
				+ ", bill_award_sixth=" + bill_award_sixth + ", status="
				+ status + "]";
	}
	public String getRolesalary_id() {
		return rolesalary_id;
	}
	public void setRolesalary_id(String rolesalary_id) {
		this.rolesalary_id = rolesalary_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getBasic_salary() {
		return basic_salary;
	}
	public void setBasic_salary(String basic_salary) {
		this.basic_salary = basic_salary;
	}
	public String getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}
	public String getFirst_monad() {
		return first_monad;
	}
	public void setFirst_monad(String first_monad) {
		this.first_monad = first_monad;
	}
	public String getSecond_monad() {
		return second_monad;
	}
	public void setSecond_monad(String second_monad) {
		this.second_monad = second_monad;
	}
	public String getThird_monad() {
		return third_monad;
	}
	public void setThird_monad(String third_monad) {
		this.third_monad = third_monad;
	}
	public String getFourth_monad() {
		return fourth_monad;
	}
	public void setFourth_monad(String fourth_monad) {
		this.fourth_monad = fourth_monad;
	}
	public String getFifth_monad() {
		return fifth_monad;
	}
	public void setFifth_monad(String fifth_monad) {
		this.fifth_monad = fifth_monad;
	}
	public String getSixth_monad() {
		return sixth_monad;
	}
	public void setSixth_monad(String sixth_monad) {
		this.sixth_monad = sixth_monad;
	}
	public String getSeventh_monad() {
		return seventh_monad;
	}
	public void setSeventh_monad(String seventh_monad) {
		this.seventh_monad = seventh_monad;
	}
	public String getEighth_monad() {
		return eighth_monad;
	}
	public void setEighth_monad(String eighth_monad) {
		this.eighth_monad = eighth_monad;
	}
	public String getNinth_monad() {
		return ninth_monad;
	}
	public void setNinth_monad(String ninth_monad) {
		this.ninth_monad = ninth_monad;
	}
	public String getTenth_monad() {
		return tenth_monad;
	}
	public void setTenth_monad(String tenth_monad) {
		this.tenth_monad = tenth_monad;
	}
	public String getEleventh_monad() {
		return eleventh_monad;
	}
	public void setEleventh_monad(String eleventh_monad) {
		this.eleventh_monad = eleventh_monad;
	}
	public String getIntegral_reward_first() {
		return integral_reward_first;
	}
	public void setIntegral_reward_first(String integral_reward_first) {
		this.integral_reward_first = integral_reward_first;
	}
	public String getIntegral_reward_second() {
		return integral_reward_second;
	}
	public void setIntegral_reward_second(String integral_reward_second) {
		this.integral_reward_second = integral_reward_second;
	}
	public String getTake_look_num() {
		return take_look_num;
	}
	public void setTake_look_num(String take_look_num) {
		this.take_look_num = take_look_num;
	}
	public String getTake_look_amount() {
		return take_look_amount;
	}
	public void setTake_look_amount(String take_look_amount) {
		this.take_look_amount = take_look_amount;
	}
	public String getTake_look_singlegroup() {
		return take_look_singlegroup;
	}
	public void setTake_look_singlegroup(String take_look_singlegroup) {
		this.take_look_singlegroup = take_look_singlegroup;
	}
	public String getBill_award_first() {
		return bill_award_first;
	}
	public void setBill_award_first(String bill_award_first) {
		this.bill_award_first = bill_award_first;
	}
	public String getBill_award_third() {
		return bill_award_third;
	}
	public void setBill_award_third(String bill_award_third) {
		this.bill_award_third = bill_award_third;
	}
	public String getBill_award_second() {
		return bill_award_second;
	}
	public void setBill_award_second(String bill_award_second) {
		this.bill_award_second = bill_award_second;
	}
	public String getBill_award_fourth() {
		return bill_award_fourth;
	}
	public void setBill_award_fourth(String bill_award_fourth) {
		this.bill_award_fourth = bill_award_fourth;
	}
	public String getBill_award_fifth() {
		return bill_award_fifth;
	}
	public void setBill_award_fifth(String bill_award_fifth) {
		this.bill_award_fifth = bill_award_fifth;
	}
	public String getBill_award_sixth() {
		return bill_award_sixth;
	}
	public void setBill_award_sixth(String bill_award_sixth) {
		this.bill_award_sixth = bill_award_sixth;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
