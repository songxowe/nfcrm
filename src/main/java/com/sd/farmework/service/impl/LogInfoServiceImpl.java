package com.sd.farmework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.LogInfoMapper;
import com.sd.farmework.pojo.LogInfo;
import com.sd.farmework.service.LogInfoService;

public class LogInfoServiceImpl extends BaseInfoServiceImpl implements
		LogInfoService {
	@Autowired
	private LogInfoMapper baseMapper;

	public LogInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(LogInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public List queryLonInfo(LogInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryLonInfo(obj);
	}

	@Override
	public List queryByProId(String pro_id) {
		// TODO Auto-generated method stub
		return baseMapper.queryByProId(pro_id);
	}

	@Override
	public BaseInfo queryTime(LogInfo loginfo) {
		return this.baseMapper.queryTime(loginfo);
	}

	@Override
	public List queryRFtime(LogInfo loginfo) {
		// TODO Auto-generated method stub
		return this.baseMapper.queryRFtime(loginfo);
	}


}
