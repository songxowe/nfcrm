package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.mapper.SysApprovenFunctionMapper;
import com.sd.farmework.service.SysApprovenFunctionService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class SysApprovenFunctionServiceImpl   extends BaseInfoServiceImpl  implements SysApprovenFunctionService {

	private static Logger logger = Logger.getLogger(SysApprovenFunctionServiceImpl.class);

	@Autowired
	private SysApprovenFunctionMapper baseMapper;

	public SysApprovenFunctionMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SysApprovenFunctionMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	 
}
