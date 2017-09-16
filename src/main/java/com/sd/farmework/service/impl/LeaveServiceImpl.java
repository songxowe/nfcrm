package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.LeaveInfoMapper;
import com.sd.farmework.service.LeaveInfoService;

public class LeaveServiceImpl extends BaseInfoServiceImpl implements
		LeaveInfoService {

	@Autowired
	private LeaveInfoMapper baseMapper;

	public LeaveInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(LeaveInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public void inserttoleave(BaseInfo baseinfo) {
		this.baseMapper.inserttoleave(baseinfo);
	}

}
