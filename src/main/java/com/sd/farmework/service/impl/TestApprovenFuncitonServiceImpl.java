package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.TestApprovenFuncitonMapper;
import com.sd.farmework.pojo.TestApprovenFunciton;
import com.sd.farmework.service.TestApprovenFuncitonService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class TestApprovenFuncitonServiceImpl   extends BaseInfoServiceImpl  implements TestApprovenFuncitonService {

	private static Logger logger = Logger.getLogger(TestApprovenFuncitonServiceImpl.class);

	@Autowired
	private TestApprovenFuncitonMapper baseMapper;

	public TestApprovenFuncitonMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(TestApprovenFuncitonMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	 
}
