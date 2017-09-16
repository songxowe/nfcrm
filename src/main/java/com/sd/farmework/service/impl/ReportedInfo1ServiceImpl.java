package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.ReportedInfo1Mapper;
import com.sd.farmework.service.ReportedInfo1Service;


public class ReportedInfo1ServiceImpl extends BaseInfoServiceImpl implements ReportedInfo1Service{

	@Autowired
	private ReportedInfo1Mapper baseMapper;

	public ReportedInfo1Mapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ReportedInfo1Mapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
}
