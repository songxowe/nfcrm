package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class GiftApply extends BaseInfo {
	private String gift_id;
	private String customer_name;
	private String input_time;
	private String pro_name;
	private String adscription;
	private String phone;
	private String weixin;
	private String qq;
	private String industry;
	private String homeperson;
	private String recommend_count;
	private String gift;
	private String status;
	private String description;
	private String step ;
	private String customer_id;

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getGift_id() {
		return gift_id;
	}

	public void setGift_id(String gift_id) {
		this.gift_id = gift_id;
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

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getAdscription() {
		return adscription;
	}

	public void setAdscription(String adscription) {
		this.adscription = adscription;
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

	public String getRecommend_count() {
		return recommend_count;
	}

	public void setRecommend_count(String recommend_count) {
		this.recommend_count = recommend_count;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "GiftApply [gift_id=" + gift_id + ", customer_name="
				+ customer_name + ", input_time=" + input_time + ", pro_name="
				+ pro_name + ", adscription=" + adscription + ", phone="
				+ phone + ", weixin=" + weixin + ", qq=" + qq + ", industry="
				+ industry + ", homeperson=" + homeperson
				+ ", recommend_count=" + recommend_count + ", gift=" + gift
				+ ", status=" + status + ", description=" + description
				+ ", step=" + step + ", customer_id=" + customer_id + "]";
	}

	
}
