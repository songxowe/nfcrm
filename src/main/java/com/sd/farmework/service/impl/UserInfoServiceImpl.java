package com.sd.farmework.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.pojo.UserInfo;
import com.sd.farmework.service.UserInfoService;
/**
 * 添加用户
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class UserInfoServiceImpl   extends BaseInfoServiceImpl  implements UserInfoService {

	private static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private UserInfoMapper baseMapper;

	public UserInfoMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(UserInfoMapper baseMapper) {
		this.baseMapper = baseMapper;
	}


	public void updatepwd(UserInfo user) {
		this.baseMapper.updatepwd(user);
	}

	public BaseInfo queryall(BaseInfo obj) throws Exception {
		// TODO Auto-generated method stub
		return this.baseMapper.queryall(obj);
	}

	@Override
	public UserInfo queryByUserName(UserInfo obj) {
		// TODO Auto-generated method stub
		return baseMapper.queryByUserName(obj);
	}
}
