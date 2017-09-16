package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.mapper.ReportedInfoMapper;
import com.sd.farmework.pojo.CustomerInfo;
import com.sd.farmework.pojo.ReportedInfo;
import com.sd.farmework.service.ReportedInfoService;

public class ReportedInfoServiceImpl extends BaseInfoServiceImpl implements ReportedInfoService{

	@Autowired
	private ReportedInfoMapper baseMapper;

	public ReportedInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ReportedInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
	}

