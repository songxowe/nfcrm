package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.SmSInfoMapper;
import com.sd.farmework.service.SmSInfoService;

public class SmSInfoServiceImpl extends BaseInfoServiceImpl implements SmSInfoService{

	@Autowired
	private SmSInfoMapper baseMapper;

	public SmSInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SmSInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
}
