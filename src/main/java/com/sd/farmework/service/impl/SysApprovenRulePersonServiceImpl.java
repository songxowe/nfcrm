package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.mapper.SysApprovenRulePersonMapper;
import com.sd.farmework.service.SysApprovenRulePersonService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class SysApprovenRulePersonServiceImpl   extends BaseInfoServiceImpl  implements SysApprovenRulePersonService {

	private static Logger logger = Logger.getLogger(SysApprovenRulePersonServiceImpl.class);

	@Autowired
	private SysApprovenRulePersonMapper baseMapper;

	public SysApprovenRulePersonMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(SysApprovenRulePersonMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	 
}
