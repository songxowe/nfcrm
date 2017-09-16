package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.FunctionInfoPowerMapper;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.service.FunctionInfoPowerService;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.UserInfoService;
/**
 * 操作权限
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class FunctionInfoPowerServiceImpl   extends BaseInfoServiceImpl  implements FunctionInfoPowerService {

	private static Logger logger = Logger.getLogger(FunctionInfoPowerServiceImpl.class);

	@Autowired
	private FunctionInfoPowerMapper baseMapper;

	public FunctionInfoPowerMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(FunctionInfoPowerMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	
	
	
	
	
	



}
