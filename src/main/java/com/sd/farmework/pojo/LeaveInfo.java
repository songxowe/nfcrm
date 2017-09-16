package com.sd.farmework.pojo;

import com.sd.farmework.common.BaseInfo;

public class LeaveInfo extends BaseInfo {
	private String leave_id;// 主键id
	private String employee_name;// 请假或者调休人
	private String employee_no;// 请假或者调休人员工编号
	private String leave_start_time;// 请假开始时间
	private String leave_end_time;// 请假结束时间
	private String leave_reason;// 请假理由
	private String status;// 请假为1，调休为2
	private String step;// 通过为2，不通过为为3，审核中为1

	public String getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_no() {
		return employee_no;
	}

	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}

	public String getLeave_start_time() {
		return leave_start_time;
	}

	public void setLeave_start_time(String leave_start_time) {
		this.leave_start_time = leave_start_time;
	}

	public String getLeave_end_time() {
		return leave_end_time;
	}

	public void setLeave_end_time(String leave_end_time) {
		this.leave_end_time = leave_end_time;
	}

	public String getLeave_reason() {
		return leave_reason;
	}

	public void setLeave_reason(String leave_reason) {
		this.leave_reason = leave_reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "LeaveInfo [leave_id=" + leave_id + ", employee_name="
				+ employee_name + ", employee_no=" + employee_no
				+ ", leave_start_time=" + leave_start_time
				+ ", leave_end_time=" + leave_end_time + ", leave_reason="
				+ leave_reason + ", status=" + status + ", step=" + step + "]";
	}

}
