package com.sd.farmework.service;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.pojo.AttendInfo;

public interface AttendInfoService extends BaseInfoService {
	List<BaseInfo> queryall(BaseInfo baseinfo);

	void inserttodetail(BaseInfo baseInfo);

	void insert(BaseInfo baseInfo);

	int attendnumer(BaseInfo baseInfo);

	int attendrecord(BaseInfo baseInfo);

	List<BaseInfo> queryallById(BaseInfo baseInfo);

	BaseInfo querytorequest(BaseInfo baseinfo);

	List<BaseInfo> selectDayAttend(AttendInfo attendobj);

	// 查询未出勤的的方法
	List<BaseInfo> selectNoDayAttend(AttendInfo attendobj);

	List<BaseInfo> selectMonthAttend(AttendInfo attendobj);

	void updatepunchtime(BaseInfo baseinfo);

	int queryatttendcount(BaseInfo baseinfo);

	int selectdetailcount(BaseInfo baseinfo);

	/**
	 * 查询未出勤的行数
	 * 
	 * @param baseinfo
	 * @return
	 */
	int queryNoCount(BaseInfo baseinfo);

	@SuppressWarnings("rawtypes")
	List selectAll(AttendInfo attendobj);

	@SuppressWarnings("rawtypes")
	List selectTodayAllAttend(AttendDetailInfo attendobj);

	void insertNoSign(BaseInfo baseInfo);
}
