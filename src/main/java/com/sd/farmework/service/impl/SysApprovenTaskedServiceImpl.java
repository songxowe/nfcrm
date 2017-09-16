package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.SysApprovenTaskedMapper;
import com.sd.farmework.service.SysApprovenTaskService;
import com.sd.farmework.service.SysApprovenTaskedService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class SysApprovenTaskedServiceImpl   extends BaseInfoServiceImpl  implements SysApprovenTaskedService {

	private static Logger logger = Logger.getLogger(SysApprovenTaskedServiceImpl.class);

	@Autowired
	private SysApprovenTaskedMapper baseMapper;

	public SysApprovenTaskedMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SysApprovenTaskedMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	 

	 

	 
}
