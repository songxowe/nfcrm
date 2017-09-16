package com.sd.farmework.mapper;

import java.util.List;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.common.BaseMapper;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.pojo.AttendInfo;

/**
 * 用户管理 查询当日考勤 chenxianglin
 */
public interface AttendInfoMapper extends BaseMapper {

	/**
	 * 更新主表时间
	 * */
	void updatepunchtime(BaseInfo baseinfo);

	/**
	 * 插入子表数据
	 * */
	void inserttodetail(BaseInfo baseInfo);

	/**
	 * 插入主表数据
	 * */
	void insert(BaseInfo baseInfo);

	/**
	 * 查询子表中当天数据条数
	 * */
	int attendnumer(BaseInfo baseInfo);

	/**
	 * 查询主表中的数据
	 * */
	int attendrecord(BaseInfo baseInfo);

	/**
	 * 根据ID查询主表中当条数据
	 * */
	BaseInfo querytorequest(BaseInfo baseinfo);

	// 以上是签到，退签所用到的方法
	// -----------------------------------
	/**
	 * 查询主表的行数
	 * */
	int queryatttendcount(BaseInfo baseinfo);

	/**
	 * 查询总表所有数据
	 * */
	List<BaseInfo> queryall(BaseInfo baseinfo);

	// 以上为查询考勤
	// ----------------------------------

	/**
	 * 
	 * 根据ID查询出子表中改ID的数据
	 * */
	List<BaseInfo> queryallById(BaseInfo baseInfo);

	/**
	 * 查询出子表中员工考勤总数
	 * */
	int selectdetailcount(BaseInfo baseinfo);

	/**
	 * 查询未出勤的行数
	 * 
	 * @param baseinfo
	 * @return
	 */
	int queryNoCount(BaseInfo baseinfo);

	// 以上为查询单个人所有考勤详情
	// ------------------------------------------------------------
	List<BaseInfo> selectDayAttend(AttendInfo attendobj);

	List<BaseInfo> selectMonthAttend(AttendInfo attendobj);

	@SuppressWarnings("rawtypes")
	List selectAll(AttendInfo attendobj);

	@SuppressWarnings("rawtypes")
	List selectTodayAllAttend(AttendDetailInfo attendobj);

	void insertNoSign(BaseInfo baseInfo);

	List<BaseInfo> selectNoDayAttend(AttendInfo attendobj);

}
