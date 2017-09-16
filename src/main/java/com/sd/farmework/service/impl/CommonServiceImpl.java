package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.mapper.CommonMapper;
import com.sd.farmework.service.CommonService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class CommonServiceImpl   extends BaseInfoServiceImpl  implements CommonService {

	private static Logger logger = Logger.getLogger(CommonServiceImpl.class);

	@Autowired
	private CommonMapper baseMapper;

	public CommonMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(CommonMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	 

	 

	 
}
