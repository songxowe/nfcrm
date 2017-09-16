package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.AttendDetailInfoMapper;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.service.AttendDetailInfoService;

public class AttendDetailInfoServiceImpl extends BaseInfoServiceImpl implements
		AttendDetailInfoService {

	@Autowired
	private AttendDetailInfoMapper baseMapper;

	public AttendDetailInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(AttendDetailInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public List<BaseInfo> selectMonthAttend(AttendDetailInfo attendobj) {
		// TODO Auto-generated method stub
		return baseMapper.selectMonthAttend(attendobj);
	}

	@Override
	public List<BaseInfo> selectMonthAttendDetail(AttendDetailInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.selectMonthAttendDetail(obj);
	}

	@Override
	public int queryCountDetail(AttendDetailInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryCountDetail(obj);
	}

	/**
	 * 根据员工编号查询当月签到时间，工资设置做准备
	 * 
	 * @param attend
	 * @return
	 */
	@Override
	public List querySignTimeByNo(AttendDetailInfo attend) {
		return baseMapper.querySignTimeByNo(attend);
	}

}
