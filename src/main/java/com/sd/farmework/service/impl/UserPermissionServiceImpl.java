package com.sd.farmework.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.farmework.common.BaseInfo;
import com.sd.farmework.mapper.PowerMapper;
import com.sd.farmework.mapper.RoleMapper;
import com.sd.farmework.mapper.UserInfoMapper;
import com.sd.farmework.mapper.UserPermissionMapper;
import com.sd.farmework.service.PowerService;
import com.sd.farmework.service.RoleService;
import com.sd.farmework.service.UserInfoService;
import com.sd.farmework.service.UserPermissionService;
/**
 * 新增角色
 * @author wangchaochao
 * 2016-10-28
 */
@Service
public class UserPermissionServiceImpl   extends BaseInfoServiceImpl  implements UserPermissionService {

	private static Logger logger = Logger.getLogger(UserPermissionServiceImpl.class);

	@Autowired
	private UserPermissionMapper baseMapper;

	public UserPermissionMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(UserPermissionMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	

	

	
	
	



}
