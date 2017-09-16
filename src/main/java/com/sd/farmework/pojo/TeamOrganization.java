package com.sd.farmework.pojo;

import java.io.Serializable;

import com.sd.farmework.common.BaseInfo;

/**
 * 团队机构
 * @author wangchaochao
 * 2016-10-28
 */
public class TeamOrganization extends BaseInfo implements Serializable {
	
	private static final long serialVersionUID = -1689614204156489735L;
	
	private String team_id;
 	private String team_name;//团队名称
	private String team_desc;//描述
	private String parent_function_id;//父功能编号
	private String remark;//备注
	private String function_seque;
	private String status;//是否启用
	private String node_level;//节点层级
	
	
	@Override
	public String toString() {
		return "TeamOrganization [team_id=" + team_id + ", team_name="
				+ team_name + ", team_desc=" + team_desc
				+ ", parent_function_id=" + parent_function_id + ", remark="
				+ remark + ", function_seque=" + function_seque + ", status="
				+ status + ", node_level=" + node_level + "]";
	}

	public String getNode_level() {
		return node_level;
	}

	public void setNode_level(String node_level) {
		this.node_level = node_level;
	}

	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getTeam_desc() {
		return team_desc;
	}
	public void setTeam_desc(String team_desc) {
		this.team_desc = team_desc;
	}
	public String getParent_function_id() {
		return parent_function_id;
	}
	public void setParent_function_id(String parent_function_id) {
		this.parent_function_id = parent_function_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFunction_seque() {
		return function_seque;
	}
	public void setFunction_seque(String function_seque) {
		this.function_seque = function_seque;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
