package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.mapper.SysApprovenFunctionRuleMapper;
import com.sd.farmework.service.SysApprovenFunctionRuleService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class SysApprovenFunctionRuleServiceImpl   extends BaseInfoServiceImpl  implements SysApprovenFunctionRuleService {

	private static Logger logger = Logger.getLogger(SysApprovenFunctionRuleServiceImpl.class);

	@Autowired
	private SysApprovenFunctionRuleMapper baseMapper;

	public SysApprovenFunctionRuleMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SysApprovenFunctionRuleMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	 
}
