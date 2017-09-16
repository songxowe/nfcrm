package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.SysFunctionInfoPowerMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.SysFunctionInfoPowerService;
import com.sd.farmework.service.UserInfoService;
/**
 * 权限菜单以及操作按钮
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class SysFunctionInfoPowerServiceImpl   extends BaseInfoServiceImpl  implements SysFunctionInfoPowerService {

	private static Logger logger = Logger.getLogger(SysFunctionInfoPowerServiceImpl.class);

	@Autowired
	private SysFunctionInfoPowerMapper baseMapper;

	public SysFunctionInfoPowerMapper getBaseMapper() {
		return baseMapper;
	}
	public void setBaseMapper(SysFunctionInfoPowerMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
	
	
	
	
	



}
