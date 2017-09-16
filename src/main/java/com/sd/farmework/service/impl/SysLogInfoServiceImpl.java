package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.SysLogInfoMapper;
import com.sd.farmework.service.SysLogInfoService;

public class SysLogInfoServiceImpl extends BaseInfoServiceImpl implements SysLogInfoService{

	@Autowired
	private SysLogInfoMapper baseMapper;

	public SysLogInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SysLogInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	

}
