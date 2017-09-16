package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.UserInfoService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class PowerServiceImpl   extends BaseInfoServiceImpl  implements PowerService {

	private static Logger logger = Logger.getLogger(PowerServiceImpl.class);

	@Autowired
	private PowerMapper baseMapper;

	public PowerMapper getBaseMapper() {
		return baseMapper;
	}
	public void setBaseMapper(PowerMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
	@Override
	public List<BaseInfo> queryList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryList(obj);
	}
	@Override
	public List<BaseInfo> queryAllList(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return baseMapper.queryAllList(obj);
	}

	
	
	



}
