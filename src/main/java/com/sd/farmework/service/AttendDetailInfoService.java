package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.AttendDetailInfo;

public interface AttendDetailInfoService extends BaseInfoService {

	/**
	 * 查询当月考勤
	 * 
	 * @param attendobj
	 * @return
	 */
	List<BaseInfo> selectMonthAttend(AttendDetailInfo attendobj);

	/**
	 * 根据员工id查询当月考勤详情
	 * 
	 * @param obj
	 * @return
	 */
	List<BaseInfo> selectMonthAttendDetail(AttendDetailInfo obj);

	/**
	 * 根据员工id查询当月考勤详情总条数
	 * 
	 * @param obj
	 * @return
	 */
	int queryCountDetail(AttendDetailInfo obj);

	/**
	 * 根据员工编号查询当月签到时间，工资设置做准备
	 * 
	 * @param attend
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List querySignTimeByNo(AttendDetailInfo attend);

}
