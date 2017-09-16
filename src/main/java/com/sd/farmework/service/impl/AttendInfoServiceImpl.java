package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.AttendInfoMapper;
import com.sd.farmework.pojo.AttendDetailInfo;
import com.sd.farmework.pojo.AttendInfo;
import com.sd.farmework.service.AttendInfoService;

public class AttendInfoServiceImpl extends BaseInfoServiceImpl implements
		AttendInfoService {

	@Autowired
	private AttendInfoMapper baseMapper;

	public AttendInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(AttendInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public List<BaseInfo> selectDayAttend(AttendInfo attendobj) {
		return baseMapper.selectDayAttend(attendobj);
	}

	public List<BaseInfo> selectMonthAttend(AttendInfo attendobj) {
		return baseMapper.selectMonthAttend(attendobj);
	}

	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		return baseMapper.queryList(obj);
	}

	public List<BaseInfo> queryall(BaseInfo baseinfo) {
		return baseMapper.queryall(baseinfo);
	}

	public void inserttodetail(BaseInfo baseInfo) {
		baseMapper.inserttodetail(baseInfo);
	}

	public void insert(BaseInfo baseInfo) {
		baseMapper.insert(baseInfo);
	}

	public int attendnumer(BaseInfo baseInfo) {
		return baseMapper.attendnumer(baseInfo);
	}

	public int attendrecord(BaseInfo baseInfo) {
		return baseMapper.attendrecord(baseInfo);

	}

	public List<BaseInfo> queryallById(BaseInfo baseInfo) {
		return baseMapper.queryallById(baseInfo);
	}

	public BaseInfo querytorequest(BaseInfo baseinfo) {
		return baseMapper.querytorequest(baseinfo);
	}

	public void updatepunchtime(BaseInfo baseinfo) {
		this.baseMapper.updatepunchtime(baseinfo);
	}

	public int queryatttendcount(BaseInfo baseinfo) {
		return this.baseMapper.queryatttendcount(baseinfo);
	}

	public int selectdetailcount(BaseInfo baseinfo) {
		return this.baseMapper.selectdetailcount(baseinfo);
	}

	@SuppressWarnings("rawtypes")
	public List selectAll(AttendInfo attendobj) {
		return baseMapper.selectAll(attendobj);
	}

	@SuppressWarnings("rawtypes")
	public List selectTodayAllAttend(AttendDetailInfo attendobj) {
		return baseMapper.selectTodayAllAttend(attendobj);
	}

	public void insertNoSign(BaseInfo baseInfo) {
		baseMapper.insertNoSign(baseInfo);
	}

	@Override
	public List<BaseInfo> selectNoDayAttend(AttendInfo attendobj) {
		return baseMapper.selectNoDayAttend(attendobj);
	}

	@Override
	public int queryNoCount(BaseInfo baseinfo) {
		return baseMapper.queryNoCount(baseinfo);
	}

}
