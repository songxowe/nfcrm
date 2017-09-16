package com.sd.farmework.pojo;

import java.util.Date;

import com.sd.farmework.common.BaseInfo;

public class CustomerInfo extends BaseInfo{
	
	private String customer_id;//编号
	private String open_countdown;//开发倒计时
	private String customer_level;//客户等级
	private String customer_name;//客户名
	private String input_time;//录入时间
	private String customer_budget_min;//客户最小预算
	private String customer_budget_max;//客户最大预算
	private String customer_birthday;//客户生日
	private String customer_source;//客户来源

	private String phone;//电话号码
	private String weixin;//微信
	
	private String qq;//QQ号码
	private String industry;//从事行业
	private String homeperson;//家庭人员
	private String purpose;//客户意向
	private String region;//所在区域
	private String look_date;//带看日期
	private String recommend_person;//推荐人
	private String transactions_date;//成交日期
	private String pro_id;//项目id
	private String pro_name;//项目名称
	private String customer_type;//客户类别
	private String qq_registered;//QQ注册
	private String wx_registered;//微信注册
	private String home_address;//家庭地址
	private String unit_address;//公司地址
	private String recommend_count;//推荐人数
	private String recommend_customer;//推荐客户
	private String adscription;//归属人
	private String adscription_id;//归属人_id
	private String gift_count;//推荐人礼品发放数
	private String flag;//标识
	private String gift_date;//发放礼品
	
	private String oneString;//表示查询客户信息表示需要拼接查询的某个字段(字典表)
	
	public String getAdscription_id() {
		return adscription_id;
	}

	public void setAdscription_id(String adscription_id) {
		this.adscription_id = adscription_id;
	}

	public String getOneString() {
		return oneString;
	}

	public void setOneString(String oneString) {
		this.oneString = oneString;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getOpen_countdown() {
		return open_countdown;
	}

	public void setOpen_countdown(String open_countdown) {
		this.open_countdown = open_countdown;
	}

	public String getCustomer_level() {
		return customer_level;
	}

	public void setCustomer_level(String customer_level) {
		this.customer_level = customer_level;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getInput_time() {
		return input_time;
	}

	public void setInput_time(String input_time) {
		this.input_time = input_time;
	}

	public String getCustomer_budget_min() {
		return customer_budget_min;
	}

	public void setCustomer_budget_min(String customer_budget_min) {
		this.customer_budget_min = customer_budget_min;
	}

	public String getCustomer_budget_max() {
		return customer_budget_max;
	}

	public void setCustomer_budget_max(String customer_budget_max) {
		this.customer_budget_max = customer_budget_max;
	}

	public String getCustomer_birthday() {
		return customer_birthday;
	}

	public void setCustomer_birthday(String customer_birthday) {
		this.customer_birthday = customer_birthday;
	}

	public String getCustomer_source() {
		return customer_source;
	}

	public void setCustomer_source(String customer_source) {
		this.customer_source = customer_source;
	}


	public String getPro_id() {
		return pro_id;
	}

	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getHomeperson() {
		return homeperson;
	}

	public void setHomeperson(String homeperson) {
		this.homeperson = homeperson;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLook_date() {
		return look_date;
	}

	public void setLook_date(String look_date) {
		this.look_date = look_date;
	}

	public String getRecommend_person() {
		return recommend_person;
	}

	public void setRecommend_person(String recommend_person) {
		this.recommend_person = recommend_person;
	}

	public String getTransactions_date() {
		return transactions_date;
	}

	public void setTransactions_date(String transactions_date) {
		this.transactions_date = transactions_date;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getQq_registered() {
		return qq_registered;
	}

	public void setQq_registered(String qq_registered) {
		this.qq_registered = qq_registered;
	}

	public String getWx_registered() {
		return wx_registered;
	}

	public void setWx_registered(String wx_registered) {
		this.wx_registered = wx_registered;
	}

	public String getHome_address() {
		return home_address;
	}

	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}

	public String getUnit_address() {
		return unit_address;
	}

	public void setUnit_address(String unit_address) {
		this.unit_address = unit_address;
	}

	public String getRecommend_count() {
		return recommend_count;
	}

	public void setRecommend_count(String recommend_count) {
		this.recommend_count = recommend_count;
	}

	public String getRecommend_customer() {
		return recommend_customer;
	}

	public void setRecommend_customer(String recommend_customer) {
		this.recommend_customer = recommend_customer;
	}

	public String getGift_count() {
		return gift_count;
	}

	public void setGift_count(String gift_count) {
		this.gift_count = gift_count;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getGift_date() {
		return gift_date;
	}

	public void setGift_date(String gift_date) {
		this.gift_date = gift_date;
	}

	public String getAdscription() {
		return adscription;
	}

	public void setAdscription(String adscription) {
		this.adscription = adscription;
	}

	@Override
	public String toString() {
		return "CustomerInfo [customer_id=" + customer_id + ", open_countdown="
				+ open_countdown + ", customer_level=" + customer_level
				+ ", customer_name=" + customer_name + ", input_time="
				+ input_time + ", customer_budget_min=" + customer_budget_min
				+ ", customer_budget_max=" + customer_budget_max
				+ ", customer_birthday=" + customer_birthday
				+ ", customer_source=" + customer_source + ", phone=" + phone
				+ ", weixin=" + weixin + ", qq=" + qq + ", industry="
				+ industry + ", homeperson=" + homeperson + ", purpose="
				+ purpose + ", region=" + region + ", look_date=" + look_date
				+ ", recommend_person=" + recommend_person
				+ ", transactions_date=" + transactions_date + ", pro_id="
				+ pro_id + ", pro_name=" + pro_name + ", customer_type="
				+ customer_type + ", qq_registered=" + qq_registered
				+ ", wx_registered=" + wx_registered + ", home_address="
				+ home_address + ", unit_address=" + unit_address
				+ ", recommend_count=" + recommend_count
				+ ", recommend_customer=" + recommend_customer
				+ ", adscription=" + adscription + ", adscription_id="
				+ adscription_id + ", gift_count=" + gift_count + ", flag="
				+ flag + ", gift_date=" + gift_date + ", oneString="
				+ oneString + "]";
	}

}
