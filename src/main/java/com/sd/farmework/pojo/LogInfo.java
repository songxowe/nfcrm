package com.sd.farmework.pojo;

import java.io.Serializable;
import java.util.Date;

import com.sd.farmework.common.BaseInfo;

public class LogInfo extends BaseInfo implements Serializable{
	/**
	 * 跟进日志信息
	 * @author wangchaochao 
	 * 2016-10-28
	 */
	private static final long serialVersionUID =1L;
	
	private Integer log_id;//日志ID
	private String follow_time;//跟进时间
	private String follow_pro;//跟进项目
	private String pro_id;//项目ID
	private String follow_desc;//跟进备注
	private String phone_record;//电话记录
	private String follow_img;//跟进截图
	private String repeat_follow;//再次跟进时间
	private String follow_scheme;//下次跟进方案
	public String getPro_id() {
		return pro_id;
	}
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getFollow_scheme() {
		return follow_scheme;
	}
	public void setFollow_scheme(String follow_scheme) {
		this.follow_scheme = follow_scheme;
	}
	public Integer getLog_id() {
		return log_id;
	}
	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}
	public String getFollow_time() {
		return follow_time;
	}
	public void setFollow_time(String follow_time) {
		this.follow_time = follow_time;
	}
	public String getFollow_pro() {
		return follow_pro;
	}
	public void setFollow_pro(String follow_pro) {
		this.follow_pro = follow_pro;
	}
	public String getFollow_desc() {
		return follow_desc;
	}
	public void setFollow_desc(String follow_desc) {
		this.follow_desc = follow_desc;
	}
	public String getPhone_record() {
		return phone_record;
	}
	public void setPhone_record(String phone_record) {
		this.phone_record = phone_record;
	}
	public String getFollow_img() {
		return follow_img;
	}
	public void setFollow_img(String follow_img) {
		this.follow_img = follow_img;
	}
	public String getRepeat_follow() {
		return repeat_follow;
	}
	public void setRepeat_follow(String repeat_follow) {
		this.repeat_follow = repeat_follow;
	}
	@Override
	public String toString() {
		return "LogInfo [log_id=" + log_id + ", follow_time=" + follow_time
				+ ", follow_pro=" + follow_pro + ", pro_id=" + pro_id
				+ ", follow_desc=" + follow_desc + ", phone_record="
				+ phone_record + ", follow_img=" + follow_img
				+ ", repeat_follow=" + repeat_follow + ", follow_scheme="
				+ follow_scheme + "]";
	}


	
	
}
