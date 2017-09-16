package com.sd.farmework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.ProConfigMapper;
import com.sd.farmework.service.ProConfigService;

public class ProConfigServiceImpl extends BaseInfoServiceImpl implements ProConfigService{

	@Autowired
	private ProConfigMapper baseMapper;

	public ProConfigMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(ProConfigMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public void deleteconfig(BaseInfo baseinfo) {
		this.baseMapper.deleteconfig(baseinfo);
	}
	
}
