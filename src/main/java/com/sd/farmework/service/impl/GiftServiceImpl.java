package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.GiftMapper;
import com.sd.farmework.mapper.LeaveInfoMapper;
import com.sd.farmework.service.GiftService;
import com.sd.farmework.service.LeaveInfoService;

public class GiftServiceImpl extends BaseInfoServiceImpl implements GiftService{

	@Autowired
	private GiftMapper baseMapper;

	public GiftMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(GiftMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public void insertall(BaseInfo baseinfo) {
		this.baseMapper.insertall(baseinfo);
	}
	
}
